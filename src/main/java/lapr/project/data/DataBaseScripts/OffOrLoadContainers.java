package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.model.Port;
import lapr.project.shared.exceptions.ContainersInsideCargoManifestListSizeException;
import lapr.project.shared.exceptions.FacilityNotFoundException;
import lapr.project.utils.mappers.dto.PortDTO;

import java.sql.*;
import java.time.LocalDate;

public class OffOrLoadContainers {

    private DatabaseConnection databaseConnection = null;
    private int countContainers = 0;

    /**
     * Constructor.
     */
    public OffOrLoadContainers() {
        //Empty constructor
    }

    //US205 AND US208 METHODS FOR BOTH USER STORIES

    /**
     * Gets the facility.
     *
     * @param mmsi the ship MMSI
     * @return the facility
     * @throws SQLException
     */
    private String getFacility(int mmsi) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "Select f.FacilityID from FACILITY f\n" +
                "inner join POSITIONALMESSAGE pm\n" +
                "on ABS(ABS(pm.LONGITUDE) + ABS(pm.LATITUDE)) - ABS(ABS(f.LATITUDE)+ ABS(f.LONGITUDE)) >0\n" +
                "where pm.MMSI = " + mmsi + "\n" +
                "and f.FACILITYID = (Select cm.FACILITYID from CargoManifest cm\n" +
                "where cm.vehicleId = (Select s.VEHICLEID from Ship s\n" +
                " where s.MMSI = " + mmsi + "))";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getString("FACILITYID");
                } else {
                    return null;
                }
            }
        }
    }

    /**
     * Gets the number of containers of a cargo manifest (type) of a facility.
     *
     * @param facilityId the facility ID
     * @param mmsi       the ship MMSI
     * @param type       the cargo manifest type (load or offload)
     * @return the number of containers of a cargo manifest of a facility
     * @throws SQLException
     */
    private int countContainerByCargo(String facilityId, int mmsi, int type) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select count(c.CONTAINERID) CountContainers  from CONTAINER c\n" +
                "inner join CARGOMANIFESTCONTAINER cmc\n" +
                "on cmc.CONTAINERID = c.CONTAINERID\n" +
                "where cmc.CARGOMANIFESTID = (select cm.CARGOMANIFESTID from CargoManifest cm\n" +
                "    where cm.CARGOMANIFESTTYPE = " + type + "\n" +
                "    and cm.idTrip = (Select t.IDTRIP from TRIP t\n" +
                "        inner join Ship s\n" +
                "        on t.VEHICLEID = s.VEHICLEID\n" +
                "            where s.MMSI = " + mmsi + ")\n" +
                "    and cm.FACILITYID = " + facilityId + ")";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return (countContainers += resultSet.getInt("COUNTCONTAINERS"));
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * Gets all the containers of a cargo manifest type.
     *
     * @param facilityId the facility ID
     * @param mmsi       the ship MMSI
     * @param j          the count
     * @param type       the cargo manifest type
     * @return all the containers of a cargo manifest type
     * @throws SQLException
     */
    private String getContainerByCargoManifest(String facilityId, int mmsi, int j, int type) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        StringBuilder stringBuilder = new StringBuilder();

        String sqlCommand = "select * from CONTAINER c\n" +
                "inner join CARGOMANIFESTCONTAINER cmc\n" +
                "on cmc.CONTAINERID = c.CONTAINERID\n" +
                "where cmc.CARGOMANIFESTID = (select cm.CARGOMANIFESTID from CargoManifest cm\n" +
                "    where cm.CARGOMANIFESTTYPE = " + type + "\n" +
                "    and cm.idTrip = (Select t.IDTRIP from TRIP t\n" +
                "        inner join Ship s\n" +
                "        on t.VEHICLEID = s.VEHICLEID\n" +
                "            where s.MMSI = " + mmsi + ")\n" +
                "    and cm.FACILITYID = " + facilityId + ")";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                for (int i = 0; i < j; i++) {
                    resultSet.next();
                }
                if (resultSet.next()) {

                    String identification = resultSet.getString("CONTAINERID");
                    int payload = resultSet.getInt("PAYLOAD");

                    String typeC;

                    if (verifyContainerType(databaseConnection, identification)) typeC = "Refrigerated";
                    else typeC = "Not Refrigerated";

                    stringBuilder.append("\nContainers Information: ").append("\nIdentification: ").append(identification).append("\nType: ").append(typeC).append("\n").append(getContainerPosition(mmsi)).append("\nPayload: ").append(payload).append("\n");

                    try {
                        Port port = DataBaseUtils.getPort(getFacility(mmsi), databaseConnection);

                        PortDTO portDTO = new PortDTO(port.getIdentification(), port.getName(), port.getContinent(), port.getCountry(), port.getLocation());

                        stringBuilder.append("\n" + portDTO);

                    } catch (Exception e) {
                        //EMPTY
                    }

                    return stringBuilder.toString();

                } else return null;
            }
        }
    }

    /**
     * Gets the container position.
     *
     * @param mmsi the ship MMSI
     * @return the container position
     * @throws SQLException
     */
    private String getContainerPosition(int mmsi) throws SQLException {

        Connection connection = databaseConnection.getConnection();
        StringBuilder stringBuilder = new StringBuilder();

        String sqlCommand = "select * from POSITIONALMESSAGE WHERE MMSI = " + mmsi + "\n" +
                "order by 1 DESC";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    double longitude = resultSet.getDouble(4);
                    double latitude = resultSet.getDouble(5);

                    if (longitude < -180) longitude += 180;
                    if (latitude < -90) latitude += 90;

                    stringBuilder.append("Longitude : ").append(longitude).append("\n").append("Latitude : ").append(latitude);
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Gets the containers to offload per cargo manifest.
     *
     * @param mmsi the ship MMSI
     * @param type the cargo manifest type
     * @return the containers to offload per cargo manifest
     * @throws FacilityNotFoundException
     * @throws ContainersInsideCargoManifestListSizeException
     */
    public String getContainersPerCargoOffLoad(int mmsi, int type) throws FacilityNotFoundException, ContainersInsideCargoManifestListSizeException {

        String facilityId = null;
        int k = 0;

        try {
            facilityId = getFacility(mmsi);
        } catch (SQLException e) {
            throw new FacilityNotFoundException();
        }

        try {
            k = countContainerByCargo(facilityId, mmsi, type);
        } catch (SQLException e) {
            throw new ContainersInsideCargoManifestListSizeException();
        }

        if (k == 0) {
            return null;
        }
        int count2 = 0;

        StringBuilder c = new StringBuilder();

        c.append("Facility ID ").append(facilityId).append(":\n");

        while (k != 0) {
            try {
                c.append(getContainerByCargoManifest(facilityId, mmsi, count2, type));
            } catch (SQLException e) {
                throw new ContainersInsideCargoManifestListSizeException();
            }
            count2++;
            k--;
        }

        if (c.toString() == null) throw new ContainersInsideCargoManifestListSizeException();

        return c.toString();
    }

    /**
     * Gets the result of the containers to be offloaded.
     *
     * @param databaseConnection the database connection
     * @param mmsi               the ship MMSI
     * @param type               the cargo manifest type
     * @return the result of the containers to be offloaded
     * @throws FacilityNotFoundException
     * @throws ContainersInsideCargoManifestListSizeException
     */
    public String getResultOffLoaded(DatabaseConnection databaseConnection, int mmsi, int type) throws FacilityNotFoundException, ContainersInsideCargoManifestListSizeException {
        this.databaseConnection = databaseConnection;

        return getContainersPerCargoOffLoad(mmsi, type);
    }


    //LoadContainers ------------------------------------------------------------

    /**
     * Gets the containers to be loaded.
     *
     * @param databaseConnection the database connection
     * @param type               the cargo manifest type
     * @param mmsi               the ship MMSI
     * @return the containers to be loaded
     * @throws SQLException
     */
    public String getContainersToLoad(DatabaseConnection databaseConnection, String type, int mmsi) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        StringBuilder stringBuilder = new StringBuilder();

        LocalDate ld = LocalDate.now();
        String todayDate = ld.getYear() + "-" + ld.getMonthValue() + "-" + ld.getDayOfMonth();

        LocalDate cargoManifestNearestDate = getDate(databaseConnection, type, ld);

        String sqlCommand = "select c.CONTAINERID,c.PAYLOAD\n" +
                "from CONTAINER c\n" +
                "         inner join CARGOMANIFESTCONTAINER cmc\n" +
                "                    on cmc.CONTAINERID = c.CONTAINERID\n" +
                "where cmc.CARGOMANIFESTID in (select cm.CARGOMANIFESTID\n" +
                "                              from CARGOMANIFEST cm\n" +
                "                              where cm.CARGOMANIFESTTYPE = '" + type + "'\n" +
                "                                and cm.IDTRIP in (select t.IDTRIP\n" +
                "                                                 FROM TRIP t\n" +
                "                                                          inner join VEHICLE v\n" +
                "                                                                     on t.VEHICLEID = v.VEHICLEID\n" +
                "                                                 where v.VEHICLEID = (select s.VEHICLEID\n" +
                "                                                                      from SHIP s\n" +
                "                                                                      where s.MMSI = '" + mmsi + "') and t.STARTDATE < '" + todayDate + "' and t.ENDDATE > '" + todayDate + "')\n" +
                "    and cm.CARGOMANIFESTDATE = '" + cargoManifestNearestDate + "')";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    boolean refrigerated = verifyContainerType(databaseConnection, resultSet.getString(1));

                    if (refrigerated) {
                        stringBuilder.append("Container ID: " + resultSet.getString(1)).append("; Load: ").append(resultSet.getString(2)).append("; Type: Refrigerated\n");
                    } else {
                        stringBuilder.append("Container ID: " + resultSet.getString(1)).append("; Load: ").append(resultSet.getString(2)).append("; Type: Not refrigerated\n");
                    }
                }

                try {
                    Port port = DataBaseUtils.getPort(getFacility(mmsi), databaseConnection);

                    PortDTO portDTO = new PortDTO(port.getIdentification(), port.getName(), port.getContinent(), port.getCountry(), port.getLocation());

                    stringBuilder.append("\n" + portDTO);

                } catch (Exception e) {
                    //EMPTY
                }

                return stringBuilder.toString();
            }
        }
    }

    /**
     * Gets the cargo manifest dates by type in descending order.
     *
     * @param databaseConnection the database connection
     * @param type               the cargo manifest type
     * @param ld                 the local date
     * @return the cargo manifest dates by type in descending order
     * @throws SQLException
     */
    public LocalDate getDate(DatabaseConnection databaseConnection, String type, LocalDate ld) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select CARGOMANIFESTDATE from CARGOMANIFEST\n" +
                "where CARGOMANIFESTTYPE = '" + type + "'\n" +
                "ORDER BY 1 DESC";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Timestamp timestamp = (Timestamp) resultSet.getObject(1);
                    LocalDate iteration = timestamp.toLocalDateTime().toLocalDate();

                    if (ld.isBefore(iteration)) {
                        return iteration;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Verifies the container type (normal or refrigerated).
     *
     * @param databaseConnection the database connection
     * @param idContainer        the container ID
     * @return true if it is refrigerated, false if it isn't
     * @throws SQLException
     */
    public boolean verifyContainerType(DatabaseConnection databaseConnection, String idContainer) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from CONTAINER\n" +
                "inner join REFRIGERATORCONTAINER R on CONTAINER.CONTAINERID = R.CONTAINERID\n" +
                "where R.CONTAINERID = '" + idContainer + "'";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the result of the containers to be loaded.
     *
     * @param databaseConnection the database connection
     * @param mmsi               the ship MMSI
     * @param type               the cargo manifest type
     * @return the result of the containers to be loaded
     * @throws SQLException
     */
    public String getResultLoaded(DatabaseConnection databaseConnection, int mmsi, String type) throws SQLException {
        this.databaseConnection = databaseConnection;

        return getContainersToLoad(databaseConnection, type, mmsi);
    }
}
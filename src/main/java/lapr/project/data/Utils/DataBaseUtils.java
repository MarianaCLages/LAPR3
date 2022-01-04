package lapr.project.data.Utils;

import lapr.project.controller.App;
import lapr.project.data.CargoManifest;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipStoreData;
import lapr.project.model.*;
import lapr.project.shared.exceptions.InvalidCargoManifestException;
import lapr.project.shared.exceptions.InvalidContainerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DataBaseUtils {

    private DataBaseUtils() {
        //EMPTY
    }

    private static final ShipStoreData shipStoreData = App.getInstance().getCompany().getShipStoreData();

    public static Port getPort(String facilityID, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "SELECT FACILITYID,\n" +
                "       LONGITUDE,\n" +
                "       LATITUDE,\n" +
                "       CAPACITY,\n" +
                "       CONTINENT,\n" +
                "       C2.ALPHA2CODE,\n" +
                "       C2.ALPHA3CODE,\n" +
                "       C2.NAME,\n" +
                "       CAPITAL,\n" +
                "       POPULATION\n" +
                "FROM FACILITY\n" +
                "         inner join COUNTRY C2 on FACILITY.ALPHA2CODE = C2.ALPHA2CODE\n" +
                "WHERE FACILITYID = '" + facilityID + "'";



        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {


                if (resultSet.next()) {

                    //String continentID = getContinentID(resultSet.getString("ALPHA3CODE"), databaseConnection);

                    String identification = resultSet.getString("FACILITYID");
                    String name = resultSet.getString("NAME");
                    Country country = new Country(resultSet.getString("NAME"), resultSet.getString("ALPHA2CODE").toCharArray(), resultSet.getString("ALPHA3CODE").toCharArray(), resultSet.getDouble("POPULATION"), Continent.valueOfName(resultSet.getString("CONTINENT")));
                    double longitude = resultSet.getDouble("LONGITUDE");
                    double latitude = resultSet.getDouble("LATITUDE");

                    if (latitude < -90) latitude += 90;
                    if (longitude < -180) longitude += 180;


                    return new Port(identification, name, null, country, new FacilityLocation(longitude, latitude), 0);

                } else return null;


            }
        }

    }


    public static String getContinentID(String countryID, DatabaseConnection databaseConnection) throws SQLException {

        String sqlCommand = "SELECT CONTINENTID FROM COUNTRY WHERE ALPHA3CODE = '" + countryID + "'";

        return executeQueryAndReturnString(sqlCommand, databaseConnection);


    }

    public static String getContinent(String continentID, DatabaseConnection databaseConnection) throws SQLException {


        String sqlCommand = "SELECT CONTINENTID FROM COUNTRY WHERE ALPHA3CODE = '" + continentID + "'";

        return executeQueryAndReturnString(sqlCommand, databaseConnection);

    }

    public static int countCargoManifestByShip(int mmsi, DatabaseConnection databaseConnection) throws SQLException {

        String sqlCommand = "select count(*) COUNTCARGOMANIFESTS from CARGOMANIFEST cm\n" +
                "inner join SHIP s\n" +
                "on cm.VEHICLEID = s.VEHICLEID\n" +
                "where s.MMSI = " + mmsi;

        return executeQueryAndReturnInteger(sqlCommand, databaseConnection);
    }


    public static String executeQueryAndReturnString(String sqlCommand, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {


                if (resultSet.next()) {

                    return resultSet.getString(1);

                } else return null;


            }
        }
    }

    public static int executeQueryAndReturnInteger(String sqlCommand, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {


                if (resultSet.next()) {

                    return resultSet.getInt(1);

                } else return 0;


            }
        }
    }

    public static CargoManifest getCargoManifestByMmsi(int mmsi, int j, Ship s, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from CARGOMANIFEST cm\n" +
                "inner join SHIP s\n" +
                "on cm.VEHICLEID = s.VEHICLEID\n" +
                "where s.MMSI = " + mmsi;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                for (int i = 1; i < j; i++) {
                    resultSet.next();
                }
                if (resultSet.next()) {

                    String cargoManifestId = resultSet.getString("CARGOMANIFESTID");
                    Port p = null;

                    return new CargoManifest(cargoManifestId, p, s, true);
                }
            }
            return null;
        }
    }


    public static int countContainerByCargoManifest(String id, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "Select count(*) COUNTCONTAINERS from CARGOMANIFESTCONTAINER cmc\n" +
                "where cmc.CargoManifestId =" + id;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("COUNTCONTAINERS");
                } else {
                    return 0;
                }
            }
        }
    }

    public static CargoManifest getCargoManifestByID(String id, Ship s, DatabaseConnection databaseConnection) throws SQLException {


        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "SELECT * from CargoManifest where CARGOMANIFESTID =" + id;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    String idCargo = resultSet.getString("CARGOMANIFESTID");

                    return new CargoManifest(idCargo, null, s, true);
                } else {
                    return null;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public static Container getContainerByCargo(String id, int j, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "SELECT  cmc.CONTAINERID from CARGOMANIFESTCONTAINER cmc\n" +
                "where cmc.CARGOMANIFESTID =" + id;


        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                for (int i = 1; i < j; i++) {
                    resultSet.next();
                }

                if (resultSet.next()) {


                    String identification = resultSet.getString("CONTAINERID");
                    return new Container(identification, 0, 0, 0, "iso");
                }
            }
            return null;
        }
    }

    public static Ship getShipByMmsi(int mmsi, DatabaseConnection databaseConnection) {
        return (Ship) shipStoreData.getElement(databaseConnection, mmsi);
    }

    public static Map<Port, Map<Port, Double>> getSeaDist(DatabaseConnection databaseConnection) {
        Connection connection = databaseConnection.getConnection();
        Map<Port, Map<Port, Double>> seaDists = new HashMap<>();
        String sqlCommand = "Select * from SEADISTANCE";
        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    sqlCommand = "SELECT ID,\n" +
                            "       PORT.FACILITYID,\n" +
                            "       DOCKINGAREA,\n" +
                            "       F.LONGITUDE   as FacilityLongitude,\n" +
                            "       F.LATITUDE    as FacilityLatitude,\n" +
                            "       f.NAME        as FacilitiName,\n" +
                            "       CAPACITY,\n" +
                            "       c2.ALPHA2CODE as ALPHA2CODE,\n" +
                            "       c2.ALPHA3CODE as ALPHA3CODE,\n" +
                            "       CONTINENT,\n" +
                            "       C2.NAME       as CountryName,\n" +
                            "       CAPITAL,\n" +
                            "       POPULATION\n" +
                            "from PORT\n" +
                            "         join FACILITY F on F.FACILITYID = PORT.FACILITYID\n" +
                            "         join COUNTRY C2 on F.ALPHA2CODE = C2.ALPHA2CODE\n" +
                            "where F.FACILITYID like ('" + resultSet.getString(1) + "')";

                    try (PreparedStatement getPrepared1Statement = connection.prepareStatement(sqlCommand)) {
                        try (ResultSet resultSet1 = getPrepared1Statement.executeQuery()) {
                            if (resultSet1.next()) {
                                Country country = new Country(resultSet1.getString(11), resultSet1.getString(8).toCharArray(), resultSet1.getString(9).toCharArray(), resultSet1.getDouble(13), Continent.valueOfName(resultSet1.getString(10)));

                                Port port = new Port(resultSet1.getString(1), resultSet1.getString(6), resultSet1.getString(10), country, new FacilityLocation(resultSet1.getDouble(4), resultSet1.getDouble(5)), resultSet1.getInt(7));


                                if (!seaDists.containsKey(port)) {
                                    seaDists.put(port, new HashMap<>());
                                }

                                sqlCommand = "SELECT ID,\n" +
                                        "       PORT.FACILITYID,\n" +
                                        "       DOCKINGAREA,\n" +
                                        "       F.LONGITUDE   as FacilityLongitude,\n" +
                                        "       F.LATITUDE    as FacilityLatitude,\n" +
                                        "       f.NAME        as FacilitiName,\n" +
                                        "       CAPACITY,\n" +
                                        "       c2.ALPHA2CODE as ALPHA2CODE,\n" +
                                        "       c2.ALPHA3CODE as ALPHA3CODE,\n" +
                                        "       CONTINENT,\n" +
                                        "       C2.NAME       as CountryName,\n" +
                                        "       CAPITAL,\n" +
                                        "       POPULATION\n" +
                                        "from PORT\n" +
                                        "         join FACILITY F on F.FACILITYID = PORT.FACILITYID\n" +
                                        "         join COUNTRY C2 on F.ALPHA2CODE = C2.ALPHA2CODE\n" +
                                        "where F.FACILITYID like ('" + resultSet.getString(2) + "')";


                                try (PreparedStatement getPrepared2Statement = connection.prepareStatement(sqlCommand)) {
                                    try (ResultSet resultSet2 = getPrepared2Statement.executeQuery()) {

                                        if (resultSet2.next()) {
                                            Country country1 = new Country(resultSet2.getString(11), resultSet2.getString(8).toCharArray(), resultSet2.getString(9).toCharArray(), resultSet2.getDouble(13), Continent.valueOfName(resultSet2.getString(10)));

                                            Port port1 = new Port(resultSet2.getString(1), resultSet2.getString(6), resultSet2.getString(10), country1, new FacilityLocation(resultSet2.getDouble(4), resultSet2.getDouble(5)), resultSet2.getInt(7));
                                            seaDists.get(port).put(port1, resultSet.getDouble(3));

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyMap();
        }
        return seaDists;
    }


    public static Map<City, LinkedList<City>> getBorders(DatabaseConnection databaseConnection) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        HashMap<City, LinkedList<City>> borders = new HashMap<>();
        String sqlCommand = "Select * from BORDER";
        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                resultSet.next();
                do {
                    sqlCommand = "Select c2.ALPHA3CODE,\n" +
                            "       C2.ALPHA2CODE,\n" +
                            "       C2.NAME,\n" +
                            "       C2.CONTINENT,\n" +
                            "       C2.POPULATION,\n" +
                            "       C3.NAME,\n" +
                            "       C3.LATITUDE,\n" +
                            "       C3.LONGITUDE\n" +
                            "from COUNTRY C2\n" +
                            "         inner join CITY C3 on C2.ALPHA2CODE = C3.COUNTRYALPHA2CODE and C2.ALPHA3CODE = C3.COUNTRYALPHA3CODE\n" +
                            "where C2.ALPHA3CODE like '" + resultSet.getString(2) + "'";
                    try (PreparedStatement getPrepared1Statement = connection.prepareStatement(sqlCommand)) {
                        try (ResultSet resultSet1 = getPrepared1Statement.executeQuery()) {
                            resultSet1.next();
                            Country country = new Country(resultSet1.getString(3), resultSet1.getString(1).toCharArray(), resultSet1.getString(2).toCharArray(), resultSet1.getDouble(5), Continent.valueOfName(resultSet1.getString(4)));
                            City capital = new City(resultSet1.getString(6), resultSet1.getDouble(7), resultSet1.getDouble(8), country);
                            if (!borders.containsKey(capital)) {
                                borders.put(capital, new LinkedList<>());
                            }
                            sqlCommand = "Select c2.ALPHA3CODE,\n" +
                                    "       C2.ALPHA2CODE,\n" +
                                    "       C2.NAME,\n" +
                                    "       C2.CONTINENT,\n" +
                                    "       C2.POPULATION,\n" +
                                    "       C3.NAME,\n" +
                                    "       C3.LATITUDE,\n" +
                                    "       C3.LONGITUDE\n" +
                                    "from COUNTRY C2\n" +
                                    "         inner join CITY C3 on C2.ALPHA2CODE = C3.COUNTRYALPHA2CODE and C2.ALPHA3CODE = C3.COUNTRYALPHA3CODE\n" +
                                    "where C2.ALPHA3CODE like '" + resultSet.getString(4) + "'";
                            try (PreparedStatement getPrepared2Statement = connection.prepareStatement(sqlCommand)) {
                                try (ResultSet resultSet2 = getPrepared2Statement.executeQuery()) {
                                    resultSet2.next();
                                    Country country1 = new Country(resultSet2.getString(3), resultSet2.getString(1).toCharArray(), resultSet2.getString(2).toCharArray(), resultSet2.getDouble(5), Continent.valueOfName(resultSet2.getString(4)));
                                    City capital1 = new City(resultSet2.getString(6), resultSet2.getDouble(7), resultSet2.getDouble(8), country1);
                                    borders.get(capital).add(capital1);
                                }
                            }
                        }
                    }


                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyMap();
        }
        return borders;
    }


    public static Ship getMmsiByCargoManifest(DatabaseConnection databaseConnection, String cargoManifestId) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select MMSI from SHIP s\n" +
                "inner join CARGOMANIFEST cm on s.VEHICLEID = cm.VEHICLEID\n" +
                "where cm.CARGOMANIFESTID = " + cargoManifestId;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    int shipMmsi = resultSet.getInt("MMSI");

                    return getShipByMmsi(shipMmsi, databaseConnection);
                }
            }
        }
        return null;
    }


    public static boolean createCargoManifestContainer(DatabaseConnection databaseConnection, String cargoManifestID, String containerID, int xPos, int yPos, int zPos) throws SQLException, InvalidCargoManifestException, InvalidContainerException {
        Connection connection = databaseConnection.getConnection();

        try {

            if (!verifyCargoManifest(cargoManifestID, databaseConnection))
                throw new InvalidCargoManifestException();

        } catch (SQLException e) {
            throw new InvalidCargoManifestException();
        }

        try {

            if (!verifyContainer(containerID, databaseConnection))
                throw new InvalidContainerException();

        } catch (SQLException e) {
            throw new InvalidContainerException();
        }

        String sqlCommand = "insert into CARGOMANIFESTCONTAINER values ('" + cargoManifestID + "','" + containerID + "'," + xPos + "," + yPos + "," + zPos + ")";

        try (PreparedStatement saveCargoManifestContainerPreparedStatement = connection.prepareStatement(sqlCommand)) {
            saveCargoManifestContainerPreparedStatement.executeUpdate();
            return true;
        }

    }

    private static boolean verifyContainer(String containerID, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select COUNT(*)\n" +
                "from CONTAINER\n" +
                "where CONTAINERID = " + containerID;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    int count = resultSet.getInt(1);

                    if (count == 1) return true;
                    else return false;

                }
            }
        }

        return false;

    }

    private static boolean verifyCargoManifest(String cargoManifestID, DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select COUNT(*)\n" +
                "from CARGOMANIFEST\n" +
                "where CARGOMANIFESTID = " + cargoManifestID;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    int count = resultSet.getInt(1);

                    if (count == 1) return true;
                    else return false;

                }
            }
        }

        return false;


    }


}


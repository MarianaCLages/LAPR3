package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.CargoManifest;
import lapr.project.model.Container;
import lapr.project.model.Facility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OffOrLoadContainers {

    private DatabaseConnection databaseConnection = null;
    private List<CargoManifest> cargoManifests = new ArrayList<>();
    private int countCargos = 0;
    private int countContainers = 0;

    public OffOrLoadContainers() {
        //Empty constructor
    }

    private String getFacility(int mmsi){
        Connection connection = databaseConnection.getConnection();


        String sqlCommand = "Select  f.FACILITYID from FACILITY f\n" +
                "inner join POSITIONALMESSAGE pm\n" +
                "on ABS(ABS(pm.LONGITUDE) + ABS(pm.LATITUDE)) - ABS(ABS(f.LATITUDE)+ ABS(f.LONGITUDE)) >0\n" +
                "where pm.MMSI = "+mmsi+"\n" +
                "and f.FACILITYID = (Select cm.FACILITYID from CargoManifest cm\n" +
                "    where cm.vehicleId = (Select s.VEHICLEID from Ship s\n" +
                "        where s.MMSI = "+mmsi+")\n" +
                "    and cm.CargoManifestDate > pm.BASEDATETIME FETCH FIRST 1 ROW  ONLY )\n" +
                "order by f.FACILITYID Desc\n" +
                "FETCH first 1 ROW ONLY";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getString("FACILITYID");
                } else {
                    return null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private int countContainerByCargo(String facilityId, int mmsi, int type) {
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
                    return countContainers += resultSet.getInt("COUNTCONTAINERS");
                } else {
                    return 0;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    private Container getContainerByCargoManifest(String facilityId, int mmsi, int j, int type) {
        Connection connection = databaseConnection.getConnection();

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
                    int tare = resultSet.getInt("TARE");
                    int gross = resultSet.getInt("GROSS");
                    String isoCode = resultSet.getString("ISOCODE");


                    return new Container(identification, payload, tare, gross, isoCode);

                } else return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    public boolean getContainersPerCargoOffLoad( int mmsi, int type) {

        String facilityId = getFacility(mmsi);
        int k = countContainerByCargo(facilityId, mmsi, type);
        if(k == 0){return  false;}
        int count2 = 0;

        while (k != 0) {
            Container c = getContainerByCargoManifest(facilityId, mmsi, count2, type);
            if (c != null) {
                System.out.println(c);
            }
            count2++;
            k--;
        }
        return true;
    }

    public boolean getResult(DatabaseConnection databaseConnection,  int mmsi, int type) {
        this.databaseConnection = databaseConnection;

        return getContainersPerCargoOffLoad( mmsi, type);
    }
}

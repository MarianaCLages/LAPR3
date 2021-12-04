package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.CargoManifest;
import lapr.project.model.Container;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {


    private DatabaseConnection databaseConnection = null;
    private List<Container> containerList = new ArrayList<>();
    private List<CargoManifest> cargoManifests = new ArrayList<>();
    private int countCargos = 0;
    private int countContainers = 0;


    public Test(){}

    private int countContainerByCargo(String facilityId, int mmsi) {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select count(c.CONTAINERID) CountContainers from CONTAINER c\n" +
                "inner join CARGOMANIFESTCONTAINER cmc\n" +
                "on cmc.CONTAINERID = c.CONTAINERID\n" +
                "where cmc.CARGOMANIFESTID = (select cm.CARGOMANIFESTID from CargoManifest cm\n" +
                "    where cm.CARGOMANIFESTTYPE = 1\n" +
                "    and cm.idTrip = (Select t.IDTRIP from TRIP t\n" +
                "        inner join Ship s\n" +
                "        on t.VEHICLEID = s.VEHICLEID\n" +
                "            where s.MMSI = "+mmsi+"\n" +
                "        and t.IDTRIP = (Select ft.idTrip from FacilityTrip ft\n" +
                "            inner join Facility f\n" +
                "            on ft.FACILITYID = f.FACILITYID\n" +
                "            where f.facilityId = "+facilityId+")))\n";

        try (PreparedStatement getPreparedStatment = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatment.executeQuery()) {

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

    private Container getContainerByCargoManifest(String facilityId,int mmsi,int j) {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from CONTAINER c\n" +
                "inner join CARGOMANIFESTCONTAINER cmc\n" +
                "on cmc.CONTAINERID = c.CONTAINERID\n" +
                "where cmc.CARGOMANIFESTID = (select cm.CARGOMANIFESTID from CargoManifest cm\n" +
                "    where cm.CARGOMANIFESTTYPE = 1\n" +
                "    and cm.idTrip = (Select t.IDTRIP from TRIP t\n" +
                "        inner join Ship s\n" +
                "        on t.VEHICLEID = s.VEHICLEID\n" +
                "            where s.MMSI = "+mmsi+"\n" +
                "        and t.IDTRIP = (Select ft.idTrip from FacilityTrip ft\n" +
                "            inner join Facility f\n" +
                "            on ft.FACILITYID = f.FACILITYID\n" +
                "            where f.facilityId = "+facilityId+")))";

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


    public List<Container> getContainersPerCargoOffLoad(String facilityId, int mmsi) {


        int k = countContainerByCargo(facilityId, mmsi); // está a dar 0, ver motivo
        int count2 = 0;




        while (k != 0) {
                Container c = getContainerByCargoManifest(facilityId,mmsi,count2);
                containerList.add(c);
                count2++;
                k--;
        }


        return containerList;
    }

    public void wtv(DatabaseConnection databaseConnection,String facilityId, int mmsi) {
        this.databaseConnection = databaseConnection;

        countContainers = countContainerByCargo(facilityId, mmsi);

        System.out.println(countContainers);
        containerList = getContainersPerCargoOffLoad(facilityId,mmsi);

        for(Container c : containerList){
            System.out.println(c);
        }

    }

}

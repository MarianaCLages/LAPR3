package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OffLoadedContainers {

    public OffLoadedContainers() {
        //Empty constructor.
    }

    private DatabaseConnection databaseConnection = null;
    private List<Container> containerList = new ArrayList<>();
    private List<CargoManifest> cargoManifests = new ArrayList<>();
    private int countCargos = 0;
    private int countContainers = 0;

    private int countCargoManifestByFacility(Facility f, Ship s) {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select count(cm.CARGOMANIFESTID) CountCargo from CARGOMANIFEST cm\n" +
                "    where cm.CARGOMANIFESTTYPE = 1\n" +
                "    and cm.IDTRIP = (select t.IDTRIP from TRIP t\n" +
                "        inner join FacilityTrip ft\n" +
                "        on t.IDTRIP = ft.IDTRIP\n" +
                "        where ft.facilityId = (select f.FACILITYID from FACILITY f\n" +
                "            where f.FACILITYID = " + "14635" + "))\n" +
                "    and cm.IDTRIP  = (select t.IDTRIP from TRIP t\n" +
                "        inner join VEHICLE v\n" +
                "        on t.VEHICLEID = v.VEHICLEID\n" +
                "        where v.VEHICLEID = (select s.VEHICLEID from SHIP s\n" +
                "        where s.MMSI =" + 366976870 + "))";

        try (PreparedStatement getPreparedStatment = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatment.executeQuery()) {

                if (resultSet.next()) {
                    return countCargos += resultSet.getInt("COUNTCARGO");
                } else {
                    return 0;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public List<Container> getContainersPerCargoOffLoad(Facility f, Ship s) {
        int j = countCargoManifestByFacility(f, s);
        int k = countContainerByCargo(); // está a dar 0, ver motivo
        int count = 0;
        int count2 = 0;

        System.out.println(k);
        System.out.println(j);
        while (j != 0) {
            CargoManifest cargoManifest = getCargoManifestByShipTrip(s, f, count);


           while (k != 0) {
                Container c = getContainerByCargoManifest(count2);
                containerList.add(c);
                count2++;
                k--;
            }
            count2 = 0;
            k = countContainerByCargo();
            count++;
            j--;
        }
        return containerList;
    }

    private CargoManifest getCargoManifestByShipTrip(Ship s, Facility f, int j) {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select cm.CARGOMANIFESTID, cm.CARGOMANIFESTDATE from CARGOMANIFEST cm\n" +
                "    where cm.CARGOMANIFESTTYPE = 1\n" +
                "    and cm.IDTRIP = (select t.IDTRIP from TRIP t\n" +
                "        inner join FacilityTrip ft\n" +
                "        on t.IDTRIP = ft.IDTRIP\n" +
                "        where ft.facilityId = (select f.FACILITYID from FACILITY f\n" +
                "            where f.FACILITYID =" + "14635" + "))\n" +
                "    and cm.IDTRIP  = (select t.IDTRIP from TRIP t\n" +
                "        inner join VEHICLE v\n" +
                "        on t.VEHICLEID = v.VEHICLEID\n" +
                "        where v.VEHICLEID = (select s.VEHICLEID from SHIP s\n" +
                "        where s.MMSI = " + 366976870 + "))";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                for (int i = 0; i < j; i++) {
                    resultSet.next();
                }
                if (resultSet.next()) {

                    Port p = (Port) f;
                    String identification = resultSet.getString("CARGOMANIFESTID");
                    Date data = resultSet.getDate("CARGOMANIFESTDATE");

                    return new CargoManifest(identification, p, data);

                } else return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private Container getContainerByCargoManifest(int j) {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from CONTAINER c\n" +
                "    inner join CARGOMANIFESTCONTAINER cmc\n" +
                "        on c.CONTAINERID = cmc.CONTAINERID\n" +
                "        where cmc.CARGOMANIFESTID =26";

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

    private int countContainerByCargo() {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select count(c.CONTAINERID) COUNTCONTAINERS from CONTAINER c\n" +
                "inner join CARGOMANIFESTCONTAINER cmc\n" +
                "on cmc.CONTAINERID = c.CONTAINERID\n" +
                "where cmc.CARGOMANIFESTID = (select cm.CARGOMANIFESTID from CARGOMANIFEST cm\n" +
                "where cm.CARGOMANIFESTTYPE = 1\n" +
                "    and cm.IDTRIP = (select  t.IDTRIP FROM TRIP t\n" +
                "        inner join VEHICLE v\n" +
                "        on t.VEHICLEID = v.VEHICLEID\n" +
                "        where v.VEHICLEID =(select  s.VEHICLEID from SHIP s\n" +
                "            where s.MMSI = 366976870))\n" +
                "    and cm.IDTRIP = (select t.IDTRIP from TRIP t\n" +
                "        inner join FACILITYTRIP ft\n" +
                "        on ft.IDTRIP = t.IDTRIP\n" +
                "        where ft.FACILITYID = (select f.FACILITYID from FACILITY f\n" +
                "            where f.FACILITYID = '14635')))";

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

    public void wtv(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

        containerList = getContainersPerCargoOffLoad(null,null);

        for (Container c : containerList) {
            System.out.println(c);
        }
    }
}

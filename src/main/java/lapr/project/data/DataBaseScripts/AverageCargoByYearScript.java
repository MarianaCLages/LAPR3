package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AverageCargoByYearScript {

    public AverageCargoByYearScript() {
        //Empty constructor
    }

    private DatabaseConnection databaseConnection = null;
    private int sumContainers = 0;

    public int getCargoManifestsOfATripFromDataBase(int mmsi, int date, int j) {
        String sqlCommand = "select cm.CARGOMANIFESTID from CARGOMANIFEST cm\n" +
                "                                   inner join TRIP T on T.IDTRIP = cm.IDTRIP and T.VEHICLEID = cm.VEHICLEID and Extract(YEAR from CARGOMANIFESTDATE) = " + date +
                "where t.VEHICLEID = (select v.VEHICLEID from VEHICLE v inner join Ship S on v.vehicleId = S.vehicleId\n" +
                "                     where s.MMSI = " + mmsi + ")";

        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                for (int i = 0; i < j; i++) {
                    resultSet.next();
                }

                if (resultSet.next()) {
                    return resultSet.getInt("CARGOMANIFESTID");

                } else return 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    private int getCargoManifestOfATripSize(int mmsi, int date) {
        String sqlCommand = "select COUNT(*) COUNT_MANIFESTS from CARGOMANIFEST cm\n" +
                "                                   inner join TRIP T on T.IDTRIP = cm.IDTRIP and T.VEHICLEID = cm.VEHICLEID and Extract(YEAR from CARGOMANIFESTDATE) = " + date +
                "where t.VEHICLEID = (select v.VEHICLEID from VEHICLE v inner join Ship S on v.vehicleId = S.vehicleId\n" +
                "                     where s.MMSI = " + mmsi + ")";

        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("COUNT_MANIFESTS");

                } else {
                    return 0;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    private void getContainersCargoManifestOfATripSize(int cargoManifestID) {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select count(c.CONTAINERID) COUNT_CONTAINERS\n" +
                "from CONTAINER c\n" +
                "         inner join CARGOMANIFESTCONTAINER cm on cm.CONTAINERID = c.CONTAINERID\n" +
                "where cm.CARGOMANIFESTID = " + cargoManifestID;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    sumContainers += resultSet.getInt("COUNT_CONTAINERS");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getNumberOfContainersPerTrip(int mmsi, int date) {
        int j = getCargoManifestOfATripSize(mmsi, date);
        int cargoManifestID = 0;

        int count = 0;
        int aux = j;

        while (j != 0) {
            cargoManifestID = getCargoManifestsOfATripFromDataBase(mmsi, date, count);
            getContainersCargoManifestOfATripSize(cargoManifestID);

            count++;
            j--;
        }

        return sumContainers / aux;
    }

    public int numberOfContainers(DatabaseConnection databaseConnection, int mmsi, int date) {
        this.databaseConnection = databaseConnection;

        return getNumberOfContainersPerTrip(mmsi, date);
    }
}

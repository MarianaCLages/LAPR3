package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.NoCargoManifestInThatDateException;
import lapr.project.shared.exceptions.NoCargoManifestsWereFoundInThatTripException;
import lapr.project.shared.exceptions.NoContainersInsideThatTripException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AverageCargoByYearScript {

    public AverageCargoByYearScript() {
        //Empty constructor
    }

    //US207

    private DatabaseConnection databaseConnection = null;

    public int getCargoManifestsOfATripFromDataBase(int mmsi, int date, int j) throws SQLException {
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
        }
    }

    private int getCargoManifestOfATripSize(int mmsi, int date) throws SQLException {
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
        }
    }

    private int getContainersCargoManifestOfATripSize(int cargoManifestID) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select count(c.CONTAINERID) COUNT_CONTAINERS\n" +
                "from CONTAINER c\n" +
                "         inner join CARGOMANIFESTCONTAINER cm on cm.CONTAINERID = c.CONTAINERID\n" +
                "where cm.CARGOMANIFESTID = " + cargoManifestID;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return (resultSet.getInt("COUNT_CONTAINERS"));
                }

                return 0;

            }
        }
    }

    public double getNumberOfContainersPerTrip(int mmsi, int date) throws NoContainersInsideThatTripException, NoCargoManifestInThatDateException, NoCargoManifestsWereFoundInThatTripException {

        int j = 0;

        try {
            j = getCargoManifestOfATripSize(mmsi, date);
        } catch (SQLException e) {
            throw new NoCargoManifestsWereFoundInThatTripException();
        }

        int cargoManifestID = 0;

        int sumContainers = 0;

        int count = 0;
        int aux = j;

        while (j != 0) {

            try {
                cargoManifestID = getCargoManifestsOfATripFromDataBase(mmsi, date, count);
            } catch (SQLException e) {
                throw new NoCargoManifestInThatDateException();
            }

            try {
                sumContainers += getContainersCargoManifestOfATripSize(cargoManifestID);
            } catch (SQLException e) {
                throw new NoContainersInsideThatTripException();
            }

            count++;
            j--;
        }

        if (aux == 0) {
            throw new NoCargoManifestsWereFoundInThatTripException();
        }

        return (sumContainers / aux);
    }

    public String numberOfContainers(DatabaseConnection databaseConnection, int mmsi, int date) throws SQLException, NoCargoManifestInThatDateException, NoCargoManifestsWereFoundInThatTripException, NoContainersInsideThatTripException {
        this.databaseConnection = databaseConnection;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nNumber of cargo manifests transported in the given year: ").append(getCargoManifestOfATripSize(mmsi, date)).append("\n").append("Average number of containers per manifest: ").append(getNumberOfContainersPerTrip(mmsi, date));

        return stringBuilder.toString();
    }
}

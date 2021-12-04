package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OccupancyRateOfAGivenShip {

    private DatabaseConnection databaseConnection = null;

    public OccupancyRateOfAGivenShip() {

    }

    public double occupancyRateInAShipGivenACargoManifestID(DatabaseConnection databaseConnection, int mmsi, int cargoManifestID) {

        this.databaseConnection = databaseConnection;

        return occupancyRate(mmsi, cargoManifestID);
    }

    public double occupancyRateInAShipGivenACargoManifestDate(DatabaseConnection databaseConnection,int mmsi, String date) {

        this.databaseConnection = databaseConnection;

        int cargoManifestID = getCargoManifestID(date);

        return occupancyRate(mmsi, cargoManifestID);
    }

    public double occupancyRate(int mmsi, int cargoManifestID) {

        int inc = 0;
        int iterator = getContainersInsideCargoManifestListSize(cargoManifestID);

        double sum = (getShipCargoCapacity(mmsi) * 1000);
        double containersGross = 0;

        while (iterator != 0) {
            containersGross += getContainerGross(cargoManifestID, inc);

            inc++;
            iterator--;
        }

        return ((containersGross / sum) * 100);

    }

    public int getCargoManifestID(String date) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from CARGOMANIFEST where CARGOMANIFESTDATE = " + date;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getInt("CARGOMANIFESTID");

                } else return 0;

            }
        } catch (SQLException throwables) {
            System.out.println("Nop Container Size");
            return 0;
        }

    }

    public int getContainersInsideCargoManifestListSize(int cargoManifestID) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select COUNT(*) COUNT_SIZE\n" +
                "from CONTAINER c\n" +
                "         inner join CARGOMANIFESTCONTAINER cm on c.CONTAINERID = cm.CONTAINERID\n" +
                "where cm.CARGOMANIFESTID = " + cargoManifestID;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getInt("COUNT_SIZE");

                } else return 0;

            }
        } catch (SQLException throwables) {
            System.out.println("Nop Container Size");
            return 0;
        }
    }

    public int getShipCargoCapacity(int mmsi) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from SHIP where mmsi = " + mmsi;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    int number = resultSet.getInt("CAPACITY");

                    return resultSet.getInt("CAPACITY");

                } else return 0;

            }
        } catch (SQLException throwables) {
            System.out.println("Nop Ship");
            return 0;
        }

    }

    public int getContainerGross(int cargoManifestID, int inc) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select GROSS CONTAINER_GROSS\n" +
                "from CONTAINER c\n" +
                "         inner join CARGOMANIFESTCONTAINER cm on c.CONTAINERID = cm.CONTAINERID\n" +
                "where cm.CARGOMANIFESTID = " + cargoManifestID;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                for (int i = 0; i < inc; i++) {
                    resultSet.next();
                }

                if (resultSet.next()) {

                    return resultSet.getInt("CONTAINER_GROSS");

                } else return 0;

            }
        } catch (SQLException throwables) {
            System.out.println("Nop Container");
            return 0;
        }
    }

}
package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OccupancyRateOfAGivenShip {

    private DatabaseConnection databaseConnection = null;

    public OccupancyRateOfAGivenShip() {
        // empty
    }

    public double occupancyRateInAShipGivenACargoManifestID(DatabaseConnection databaseConnection, int mmsi, String cargoManifestID) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException {
        this.databaseConnection = databaseConnection;

        verifyIntegrity(databaseConnection, mmsi, cargoManifestID);

        return occupancyRate(mmsi, cargoManifestID);
    }

    public double occupancyRateInAShipGivenACargoManifestDate(DatabaseConnection databaseConnection, int mmsi, String date) throws ContainersInsideCargoManifestListSizeException, ShipCargoCapacityException, ContainerGrossException, CargoManifestIDException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException {
        this.databaseConnection = databaseConnection;

        String cargoManifestID = null;

        try {
            cargoManifestID = getCargoManifestID(date);
        } catch (SQLException ex4) {
            throw new CargoManifestIDException();
        }

        verifyIntegrity(databaseConnection, mmsi, cargoManifestID);

        return occupancyRate(mmsi, cargoManifestID);
    }

    private void verifyIntegrity(DatabaseConnection databaseConnection, int mmsi, String cargoManifestID) throws CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException {

        String shipVehicleID = null;
        String cargoManifestAssociatedVehicleID = null;

        try {
            shipVehicleID = getShipVehicleID(databaseConnection, mmsi);
            cargoManifestAssociatedVehicleID = getAssociatedVehicleID(databaseConnection, cargoManifestID);

        } catch (SQLException exception) {
            throw new VehicleIDNotValidException();
        }

        if (!shipVehicleID.equals(cargoManifestAssociatedVehicleID))
            throw new CargoManifestDoesntBelongToThatShipException();

    }

    public double occupancyRate(int mmsi, String cargoManifestID) throws ContainersInsideCargoManifestListSizeException, ContainerGrossException, ShipCargoCapacityException, IllegalArgumentException {

        int inc = 0;
        int iterator = 0;
        double sum = 0;

        try {
            iterator = getContainersInsideCargoManifestListSize(cargoManifestID);
        } catch (SQLException ex1) {
            throw new ContainersInsideCargoManifestListSizeException();
        }

        try {
            sum = (getShipCargoCapacity(databaseConnection, mmsi) * 1000);
            if(sum == 0) throw new SQLException();
        } catch (SQLException ex2) {
            throw new ShipCargoCapacityException();
        }

        double containersGross = 0;

        while (iterator != 0) {

            try {
                containersGross += getContainerGross(cargoManifestID, inc);
            } catch (SQLException ex3) {
                throw new ContainerGrossException();
            }

            inc++;
            iterator--;
        }

        if(sum==0) throw new IllegalArgumentException("The ship that you selected doesn't carry cargo manifests");

        return ((containersGross / sum) * 100);

    }

    public String getCargoManifestID(String date) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from CARGOMANIFEST where CARGOMANIFESTDATE = TO_DATE('" + date + "','YYYY-MM-DD HH24:MI:SS')";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getString("CARGOMANIFESTID");

                } else return null;

            }
        }

    }

    public int getContainersInsideCargoManifestListSize(String cargoManifestID) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = " select COUNT(*) COUNT_SIZE\n" +
                "from CONTAINER c\n" +
                "         inner join CARGOMANIFESTCONTAINER cm on c.CONTAINERID = cm.CONTAINERID\n" +
                "where cm.CARGOMANIFESTID = '" + cargoManifestID + "'";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getInt("COUNT_SIZE");

                } else return 0;

            }
        }
    }

    public int getShipCargoCapacity(DatabaseConnection databaseConnection, int mmsi) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from SHIP where mmsi = '" + mmsi + "'";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSetCargoCapacity = getPreparedStatement.executeQuery()) {

                if (resultSetCargoCapacity.next()) {
                    return resultSetCargoCapacity.getInt("CAPACITY");

                } else return 0;

            }
        }

    }

    public int getContainerGross(String cargoManifestID, int inc) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select GROSS CONTAINER_GROSS\n" +
                "from CONTAINER c\n" +
                "         inner join CARGOMANIFESTCONTAINER cm on c.CONTAINERID = cm.CONTAINERID\n" +
                "where cm.CARGOMANIFESTID = '" + cargoManifestID + "'";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                for (int i = 0; i < inc; i++) {
                    resultSet.next();
                }

                if (resultSet.next()) {

                    return resultSet.getInt("CONTAINER_GROSS");

                } else return 0;

            }
        }
    }

    public String getShipVehicleID(DatabaseConnection databaseConnection, int mmsi) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select VEHICLEID FROM SHIP WHERE MMSI = '" + mmsi + "'";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getString("VEHICLEID");

                } else return null;

            }
        }

    }

    public String getAssociatedVehicleID(DatabaseConnection databaseConnection, String cargoManifestID) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select VEHICLEID FROM CARGOMANIFEST WHERE CARGOMANIFESTID = '" + cargoManifestID + "'";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getString("VEHICLEID");

                } else return null;

            }
        }

    }

}
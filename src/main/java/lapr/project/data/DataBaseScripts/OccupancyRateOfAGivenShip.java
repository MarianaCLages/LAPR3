package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OccupancyRateOfAGivenShip {

    private DatabaseConnection databaseConnection = null;

    /**
     * Constructor.
     */
    public OccupancyRateOfAGivenShip() {
        // Empty constructor
    }

    //US208 AND US209 METHODS USED BY BOTH USER STORIES

    /**
     * Gets the occupancy rate of a ship given a cargo manifest ID.
     *
     * @param databaseConnection the database connection
     * @param mmsi               the ship MMSI
     * @param cargoManifestID    the cargo manifest ID
     * @return the occupancy rate of a ship given a cargo manifest ID
     * @throws ShipCargoCapacityException
     * @throws ContainerGrossException
     * @throws ContainersInsideCargoManifestListSizeException
     * @throws CargoManifestDoesntBelongToThatShipException
     * @throws VehicleIDNotValidException
     * @throws IllegalArgumentException
     */
    public double occupancyRateInAShipGivenACargoManifestID(DatabaseConnection databaseConnection, int mmsi, String cargoManifestID) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException {
        this.databaseConnection = databaseConnection;

        verifyIntegrity(databaseConnection, mmsi, cargoManifestID);

        return occupancyRate(mmsi, cargoManifestID);
    }

    /**
     * Gets the occupancy rate of a ship given a cargo manifest date.
     *
     * @param databaseConnection the database connection
     * @param mmsi               the ship MMSI
     * @param date               the cargo manifest date
     * @return the occupancy rate of a ship given a cargo manifest date
     * @throws ContainersInsideCargoManifestListSizeException
     * @throws ShipCargoCapacityException
     * @throws ContainerGrossException
     * @throws CargoManifestIDException
     * @throws CargoManifestDoesntBelongToThatShipException
     * @throws VehicleIDNotValidException
     * @throws IllegalArgumentException
     */
    public double occupancyRateInAShipGivenACargoManifestDate(DatabaseConnection databaseConnection, int mmsi, String date) throws ContainersInsideCargoManifestListSizeException, ShipCargoCapacityException, ContainerGrossException, CargoManifestIDException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException {
        this.databaseConnection = databaseConnection;

        String cargoManifestID;

        try {
            cargoManifestID = getCargoManifestID(date);
        } catch (SQLException ex4) {
            throw new CargoManifestIDException();
        }

        verifyIntegrity(databaseConnection, mmsi, cargoManifestID);

        return occupancyRate(mmsi, cargoManifestID);
    }

    /**
     * Verifies the integrity.
     *
     * @param databaseConnection the database connection
     * @param mmsi               the ship MMSI
     * @param cargoManifestID    the cargo manifest ID
     * @throws CargoManifestDoesntBelongToThatShipException
     * @throws VehicleIDNotValidException
     */
    private void verifyIntegrity(DatabaseConnection databaseConnection, int mmsi, String cargoManifestID) throws CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException {

        String shipVehicleID;
        String cargoManifestAssociatedVehicleID;

        try {
            shipVehicleID = getShipVehicleID(databaseConnection, mmsi);
            cargoManifestAssociatedVehicleID = getAssociatedVehicleID(databaseConnection, cargoManifestID);

        } catch (SQLException exception) {
            throw new VehicleIDNotValidException();
        }

        if (!shipVehicleID.equals(cargoManifestAssociatedVehicleID))
            throw new CargoManifestDoesntBelongToThatShipException();

    }

    /**
     * Calculates the occupancy rate.
     *
     * @param mmsi            the ship MMSI
     * @param cargoManifestID the cargo manifest ID
     * @return the occupancy rate
     * @throws ContainersInsideCargoManifestListSizeException
     * @throws ContainerGrossException
     * @throws ShipCargoCapacityException
     * @throws IllegalArgumentException
     */
    public double occupancyRate(int mmsi, String cargoManifestID) throws ContainersInsideCargoManifestListSizeException, ContainerGrossException, ShipCargoCapacityException, IllegalArgumentException {

        int inc = 0;
        int iterator;
        double sum;

        try {
            iterator = getContainersInsideCargoManifestListSize(cargoManifestID);
        } catch (SQLException ex1) {
            throw new ContainersInsideCargoManifestListSizeException();
        }

        try {
            sum = (getShipCargoCapacity(databaseConnection, mmsi) * 1000);
            if (sum == 0) throw new SQLException();
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

        if (sum == 0) throw new IllegalArgumentException("The ship that you selected doesn't carry cargo manifests");

        return ((containersGross / sum) * 100);

    }

    /**
     * Gets all the cargo manifests of a specific date.
     *
     * @param date the date
     * @return all the cargo manifests of a specific date
     * @throws SQLException
     */
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

    /**
     * Gets the number of containers of a cargo manifest.
     *
     * @param cargoManifestID the cargo manifest ID
     * @return the number of containers of a cargo manifest
     * @throws SQLException
     */
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

    /**
     * Gets the ship's cargo capacity.
     *
     * @param databaseConnection the database connection
     * @param mmsi               the ship MMSI
     * @return the ship's cargo capacity
     * @throws SQLException
     */
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

    /**
     * Gets the container gross.
     *
     * @param cargoManifestID the cargo manifest ID
     * @param inc             the count
     * @return the container gross
     * @throws SQLException
     */
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

    /**
     * Gets the ship's vehicle ID.
     *
     * @param databaseConnection the database connection
     * @param mmsi               the ship MMSI
     * @return the ship's vehicle ID
     * @throws SQLException
     */
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

    /**
     * Gets the vehicle ID given a cargo manifest.
     *
     * @param databaseConnection the database connection
     * @param cargoManifestID    the cargo manifest ID
     * @return the vehicle ID given a cargo manifest
     * @throws SQLException
     */
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
package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CallAvgOccupancyRateThreshold;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.shared.exceptions.InvalidShipException;

import java.sql.SQLException;
import java.util.List;

public class AvgOccupancyRateWithThresholdPerVoyageController {

    private final DatabaseConnection connection;

    /**
     * Constructor.
     */
    public AvgOccupancyRateWithThresholdPerVoyageController() {
        this.connection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the ship voyages that had an average occupancy rate below a certain threshold.
     *
     * @param mmsi      the ship MMSI
     * @param begin     the begin date
     * @param end       the end date
     * @param threshold the threshold
     * @return the ship voyages that had an average occupancy rate below a certain threshold
     * @throws InvalidShipException
     */

    public String getAvgOccupancyRateThreshold(int mmsi, String begin, String end, int threshold) throws InvalidShipException {
        String result = CallAvgOccupancyRateThreshold.occupationRateFunction(connection, mmsi, begin, end, threshold);

        if (result.equals("Given the default Threshold, there are no trips that have an occupancy rate below the given default threshold!!")) {
            throw new IllegalArgumentException("\nGiven the default Threshold, there are no trips that have an occupancy rate below the given default threshold!!");
        }

        return result;
    }

    /**
     * Gets all the ships with trips.
     *
     * @return all the ships with trips
     * @throws SQLException
     */
    public List<String> getAllShipsWithTrip() throws SQLException {
        return DataBaseUtils.getAllShipsWithTrips(connection);
    }

    /**
     * Verifies if the ship exists.
     *
     * @param mmsi the ship MMSI
     * @return true if it exists, false if it doesn't
     * @throws SQLException
     */
    public boolean verifyShip(String mmsi) throws SQLException {
        return DataBaseUtils.verifyShip(mmsi, connection);
    }

}
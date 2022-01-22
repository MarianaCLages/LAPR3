package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CallAvgOccupancyRatePerManifest;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.shared.exceptions.InvalidShipException;

import java.sql.SQLException;
import java.util.List;

public class AvgOccupancyRatePerManifestController {

    private final DatabaseConnection connection;

    /**
     * Constructor.
     */
    public AvgOccupancyRatePerManifestController() {
        this.connection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the result values of the average occupancy rate per manifest of a given ship during a given period.
     *
     * @param mmsi  the ship MMSI
     * @param begin the begin date
     * @param end   the end date
     * @return a string with all the result values
     * @throws InvalidShipException
     */
    public StringBuilder getAvgOccupancyRatePerManifest(int mmsi, String begin, String end) throws InvalidShipException {
        StringBuilder sb = new StringBuilder();
        double average = CallAvgOccupancyRatePerManifest.occupationRateFunction(connection, mmsi, begin, end);

        return sb.append("Ship MMSI: ").append(mmsi).append("\nPeriod: ").append(begin).append(" - ").append(end).append("\nAverage occupancy rate per manifest: ").append(average).append("%\n");
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

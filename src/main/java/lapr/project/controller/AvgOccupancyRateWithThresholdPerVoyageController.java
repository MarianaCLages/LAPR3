package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CallAvgOccupancyRateThreshold;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.shared.exceptions.InvalidShipException;
import lapr.project.shared.exceptions.NoValidInformationInsideThatTripExeception;

import java.sql.SQLException;
import java.util.List;

public class AvgOccupancyRateWithThresholdPerVoyageController {

    private final DatabaseConnection connection;

    public AvgOccupancyRateWithThresholdPerVoyageController() {
        this.connection = App.getInstance().getDatabaseConnection();
    }

    public String getAvgOccupancyRatePerManifest(int mmsi, String begin, String end, int threshold) throws InvalidShipException {
        String result = CallAvgOccupancyRateThreshold.occupationRateFunction(connection, mmsi, begin, end, threshold);

        if (result.equals("Given the default Threshold, there are no trips that have an occupancy rate below the given default threshold!!")) {
            throw new IllegalArgumentException("\nGiven the default Threshold, there are no trips that have an occupancy rate below the given default threshold!!");
        }

        return result;
    }

    public List<String> getAllShipsWithTrip() throws SQLException {
        return DataBaseUtils.getAllShipsWithTrips(connection);
    }

    public boolean verifyShip(String mmsi) throws SQLException {
        return DataBaseUtils.verifyShip(mmsi, connection);
    }

}
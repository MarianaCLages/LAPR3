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

    public String getAvgOccupancyRatePerManifest(int mmsi, String begin, String end,int threshold) throws InvalidShipException {
        return CallAvgOccupancyRateThreshold.occupationRateFunction(connection, mmsi, begin, end,threshold);
    }

    public List<String> getAllShipsWithTrip() throws SQLException {
        return DataBaseUtils.getAllShipsWithTrips(connection);
    }

    public boolean verifyShip(String mmsi) throws SQLException {
        return DataBaseUtils.verifyShip(mmsi, connection);
    }

}
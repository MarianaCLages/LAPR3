package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CallAvgOccupancyRatePerManifest;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.shared.exceptions.InvalidShipException;

import java.sql.SQLException;
import java.util.List;

public class AvgOccupancyRatePerManifestController {

    private final DatabaseConnection connection;

    public AvgOccupancyRatePerManifestController() {
        this.connection = App.getInstance().getDatabaseConnection();
    }

    public StringBuilder getAvgOccupancyRatePerManifest(int mmsi, String begin, String end) throws InvalidShipException {
        StringBuilder sb = new StringBuilder();
        double average = CallAvgOccupancyRatePerManifest.occupationRateFunction(connection, mmsi, begin, end);

        return sb.append("Ship MMSI: ").append(mmsi).append("\nPeriod: ").append(begin).append(" - ").append(end).append("\nAverage occupancy rate per manifest: ").append(average).append("%\n");
    }

    public List<String> getAllShipsWithTrip() throws SQLException {
        return DataBaseUtils.getAllShipsWithTrips(connection);
    }

    public boolean verifyShip(String mmsi) throws SQLException {
        return DataBaseUtils.verifyShip(mmsi, connection);
    }

}

/*package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CallAvgOccupancyRatePerManifest;
import lapr.project.data.DatabaseConnection;

public class AvgOccupancyRatePerManifestController {

    private final DatabaseConnection connection;

    public AvgOccupancyRatePerManifestController() {
        this.connection = App.getInstance().getDatabaseConnection();
    }

    public StringBuilder getAvgOccupancyRatePerManifest(int mmsi, String begin, String end) {
        StringBuilder sb = new StringBuilder();
        double average = CallAvgOccupancyRatePerManifest.occupationRateFunction(connection, mmsi, begin, end);

        return sb.append("Ship MMSI: ").append(mmsi).append("\nPeriod: ").append(begin).append(" - ").append(end).append("\n Average occupancy rate per manifest: ").append(average).append("\n\n");
    }
}
*/
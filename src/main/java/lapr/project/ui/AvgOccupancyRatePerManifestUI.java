/*package lapr.project.ui;

import lapr.project.controller.AvgOccupancyRatePerManifestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AvgOccupancyRatePerManifestUI implements Runnable {

    private final AvgOccupancyRatePerManifestController controller;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AvgOccupancyRatePerManifestUI() {
        this.controller = new AvgOccupancyRatePerManifestController();
    }

    public void run() {
        int mmsi;

        String begin;
        String end;

        do {
            try {
                mmsi = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                mmsi = 0;
            }
        } while (mmsi == 0);

        do {
            try {
                begin = Utils.readLineFromConsole("Please enter the begin date (Format: yyyy-MM-dd HH:MM:SS):");
                LocalDateTime startTime = LocalDateTime.from(formatter.parse(begin));
            } catch (Exception e) {
                System.out.println("Please enter a valid date!");
                begin = null;
            }
        } while (begin == null);

        do {
            try {
                end = Utils.readLineFromConsole("Please enter the end date (Format: yyyy-MM-dd HH:MM:SS):");
                LocalDateTime endTime = LocalDateTime.from(formatter.parse(end));
            } catch (Exception e) {
                System.out.println("Please enter a valid date! (Format: yyyy-MM-dd HH:MM:SS)");
                end = null;
            }
        } while (end == null);

        try {
            controller.getAvgOccupancyRatePerManifest(mmsi, begin, end);
        } catch (Exception ex1) {
            System.out.println(ex1.getMessage());
        }
    }
}
 */

package lapr.project.ui;

import lapr.project.controller.AvgOccupancyRatePerManifestController;

import java.sql.SQLException;
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
        String option = null;

        try {
            do {
                try {
                    option = (String) Utils.showAndSelectOne(controller.getAllShipsWithTrip(), "\nAll Ships with trips, please choose one:\n");

                    if (option == null || option.trim().equals("")) {
                        throw new IllegalArgumentException("Invalid Ship! Please enter a valid option! (See the trip list above)");
                    }

                    if (!controller.verifyShip(option)) {
                        throw new IllegalArgumentException("Invalid Ship! Please enter a valid option! (See the trip list above)");
                    }

                } catch (SQLException ex1) {
                    System.out.println("Invalid Ship! Please enter a valid option! (Notice: enter a number that corresponds to the option, not a invalid character or a set of invalid characters nor a number that does not exist in the list!) \n");
                    option = null;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    option = null;
                }

            } while (option == null);

            mmsi = Integer.parseInt(option);

            do {
                try {
                    begin = Utils.readLineFromConsole("Please enter the begin date (Follow this format: yyyy-MM-dd HH:mm:ss!):");
                    LocalDateTime startTime = LocalDateTime.from(formatter.parse(begin));
                } catch (Exception e) {
                    System.out.println("Please enter a valid date! (Follow this format: yyyy-MM-dd HH:mm:ss!)");
                    begin = null;
                }
            } while (begin == null);

            do {
                try {
                    end = Utils.readLineFromConsole("Please enter the end date (Follow this format: yyyy-MM-dd HH:mm:ss!)");
                    LocalDateTime endTime = LocalDateTime.from(formatter.parse(end));
                } catch (Exception e) {
                    System.out.println("Please enter a valid date! (Follow this format: yyyy-MM-dd HH:mm:ss!)");
                    end = null;
                }
            } while (end == null);


            System.out.println("\n" + controller.getAvgOccupancyRatePerManifest(mmsi, begin, end));
            System.out.println("\nOperation Success!\n");
        } catch (Exception ex1) {
            System.out.println(ex1.getMessage());
            System.out.println("\nOperation Failed!\n");
        }
    }
}
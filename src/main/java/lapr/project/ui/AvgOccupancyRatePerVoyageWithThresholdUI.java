package lapr.project.ui;

import lapr.project.controller.AvgOccupancyRateWithThresholdPerVoyageController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AvgOccupancyRatePerVoyageWithThresholdUI implements Runnable {

    private final AvgOccupancyRateWithThresholdPerVoyageController controller;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AvgOccupancyRatePerVoyageWithThresholdUI() {
        this.controller = new AvgOccupancyRateWithThresholdPerVoyageController();
    }

    public void run() {
        int mmsi;

        String begin;
        String end;
        String option = null;
        String optionThreshold = null;
        int thresholdValue = 66;
        boolean thresholdFlag = false;
        boolean condition = false;
        boolean conditionChange = false;

        try {
            do {
                try {
                    option = (String) Utils.showAndSelectOne(controller.getAllShipsWithTrip(), "\nAll Ships with trips, please choose one:\n ");

                    if (option == null || option.trim().equals("")) {
                        throw new IllegalArgumentException("Invalid Ship! Please enter a valid MMSI! (See the trip list above)");
                    }

                    if (!controller.verifyShip(option)) {
                        throw new IllegalArgumentException("Invalid Ship! Please enter a valid MMSI! (See the trip list above)");
                    }

                } catch (SQLException ex1) {
                    System.out.println("Invalid Ship! Please enter a valid MMSI! (Notice: enter a number that corresponds to the option, not a invalid character or a set of invalid characters nor a number that does not exist in the list!) \n");
                    option = null;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    option = null;
                }

            } while (option == null);

            mmsi = Integer.parseInt(option);

            do {
                try {

                    optionThreshold = Utils.readLineFromConsole("Do you wish to enter a different threshold? (Note: The default value for the threshold is 66%!) - Yes?(y/Y) or No?(N/n)");

                    if (option == null || option.trim().equals("")) {
                        throw new IllegalArgumentException("Invalid option! Please enter a valid option! (See the note above!)");
                    }

                    if (optionThreshold.equals("y") || optionThreshold.equals("Y") || optionThreshold.equals("yes") || optionThreshold.equals("Yes")) {
                        condition = true;
                        conditionChange = true;
                    } else if (optionThreshold.equals("n") || optionThreshold.equals("N") || optionThreshold.equals("no") || optionThreshold.equals("No")) {
                        condition = true;
                        conditionChange = false;
                    } else {
                        throw new IllegalArgumentException("Invalid option! Please enter a valid option! (See the note above!)");
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    condition = false;
                }

            } while (condition == false);


            if (conditionChange) {
                do {
                    try {

                        thresholdValue = Utils.readIntegerFromConsole("Enter the desired value for the threshold! (Notice the value can not be negative nor above 100%!");
                        thresholdFlag = true;

                        if (thresholdValue < 0 || thresholdValue > 100) {
                            throw new IllegalArgumentException("Invalid threshold! Please enter a valid number! (See the note above!)");
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        thresholdFlag = false;
                    }

                } while (thresholdFlag == false);
            }

            do {
                try {
                    begin = Utils.readLineFromConsole("Please enter the begin date for the period (Follow this format: yyyy-MM-dd HH:mm:ss!):");
                    LocalDateTime startTime = LocalDateTime.from(formatter.parse(begin));
                } catch (Exception e) {
                    System.out.println("Please enter a valid date! (Follow this format: yyyy-MM-dd HH:mm:ss!)");
                    begin = null;
                }
            } while (begin == null);

            do {
                try {
                    end = Utils.readLineFromConsole("Please enter the end date for the period (Follow this format: yyyy-MM-dd HH:mm:ss!)");
                    LocalDateTime endTime = LocalDateTime.from(formatter.parse(end));
                } catch (Exception e) {
                    System.out.println("Please enter a valid date! (Follow this format: yyyy-MM-dd HH:mm:ss!)");
                    end = null;
                }
            } while (end == null);
            System.out.println("\n" + controller.getAvgOccupancyRateThreshold(mmsi, begin, end, thresholdValue));
            System.out.println("\nOperation Success!\n");

        } catch (Exception ex1) {
            System.out.println(ex1.getMessage());
            System.out.println("\nOperation Failed!\n");
        }
    }
}
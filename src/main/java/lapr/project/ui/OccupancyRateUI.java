package lapr.project.ui;

import lapr.project.controller.OccupancyRateController;

public class OccupancyRateUI implements Runnable {

    @Override
    public void run() {
        OccupancyRateController occupancyRateController = new OccupancyRateController();
        int mmsi = 0;

        do {
            try {
                mmsi = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                mmsi = 0;
            }
        } while (mmsi == 0);

        System.out.println();

        try {
            String str = occupancyRateController.occupancyRate(mmsi);

            if (str == null) {
                System.out.println("Operation Failed!");
            } else {
                System.out.println(str);
            }

        } catch (NullPointerException exception) {
            System.out.println("The ship introduced doesn't exist.");
        }
    }
}

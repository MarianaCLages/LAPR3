package lapr.project.ui;

import lapr.project.controller.OccupancyRateController;

public class OccupancyRateUI implements Runnable {

    @Override
    public void run() {
        OccupancyRateController occupancyRateController = new OccupancyRateController();
        int mmsi;

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
            double str = occupancyRateController.occupancyRate(mmsi);

            if (str == 0) {
                System.out.println("The Ship cannot carry containers");
            } else {
                System.out.println(str);
            }

        } catch (NullPointerException exception) {
            System.out.println("The ship introduced doesn't exist.");
        }
    }
}

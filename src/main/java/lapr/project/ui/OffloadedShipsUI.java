package lapr.project.ui;

import lapr.project.controller.OffLoadedShipsController;

public class OffloadedShipsUI implements Runnable {

    OffLoadedShipsController offLoadedShipsController = new OffLoadedShipsController();


    public void run() {

        int op;

        do {

            try {
                op = Utils.readIntegerFromConsole("Ship's MMSI?");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                op = 0;
            }
        } while (op == 0);

        boolean bool = offLoadedShipsController.offLoadedShips(op);

        if (bool) {
            System.out.println("Operation was a success!");
        } else {
            System.out.println("Operation failed!");
        }
    }
}

package lapr.project.ui;

import lapr.project.controller.OffloadedContainersController;

public class OffloadedContainersUI implements Runnable {

    OffloadedContainersController offLoadedShipsController = new OffloadedContainersController();


    public void run() {
        int op;


        do {
            try {
                op = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                op = 0;
            }
        } while (op == 0);

        boolean b = offLoadedShipsController.offLoadedShips(op);



        if (b) {
            System.out.println("");
        } else {
            System.out.println("Operation failed! Please, try again.");
        }
    }
}
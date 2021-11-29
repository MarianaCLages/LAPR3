package lapr.project.ui;

import lapr.project.controller.OffLoadedShipsController;

public class OffloadedShipsUI implements Runnable {

    OffLoadedShipsController offLoadedShipsController = new OffLoadedShipsController();


    public void run() {

        int op;
        boolean bool = false;

        do {

            try {
                op = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                op = 0;
            }
        } while (op == 0);

        try{
            System.out.println();
            bool = offLoadedShipsController.offLoadedShips(op);
            }catch (NullPointerException ex){
                System.out.println("The ship introduced doesn't exist.");
            }

        if (bool) {
            System.out.println("");
        } else {
            System.out.println("Operation failed!");
        }
    }
}

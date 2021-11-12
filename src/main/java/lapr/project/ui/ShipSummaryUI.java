package lapr.project.ui;

import lapr.project.controller.ShipSummaryController;

public class ShipSummaryUI implements Runnable {

    public ShipSummaryUI() {
        //Empty
    }

    private final ShipSummaryController shipSummaryController = new ShipSummaryController();

    @Override
    public void run() {

        int option;
        long mmsi;
        String imo;
        String callSign;

        String shipSummary = null;

        do {
            try {
                System.out.printf(" Option 1 : Get Ship Summary by MMSI \n Option 2 : Get Ship Summary by IMO \n Option 3 : Get Ship Summary by Call Sign\n");
                option = Utils.readIntegerFromConsole("Please choose one of the valid options:");
                if (option > 3) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid option!");
                option = 0;
            }
        } while (option == 0);

        switch (option) {
            case 1:
                do {
                    try {
                        mmsi = Utils.readIntegerFromConsole("Please enter the desired MMSI:");
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid MMSI!");
                        mmsi = 0;
                    }
                } while (mmsi == 0);
                try {
                    shipSummary = shipSummaryController.getShipSummaryByMMSI(mmsi);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            case 2:
                do {
                    try {
                        imo = Utils.readLineFromConsole("Please enter the desired Ship IMO:");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Please enter a valid IMO!");
                        imo = null;
                    }
                } while (imo == null);
                try {
                    shipSummary = shipSummaryController.getShipSummaryByIMO(imo);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            case 3:
                do {
                    try {
                        callSign = Utils.readLineFromConsole("Please enter the desired Ship call Sign:");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Please enter a valid call sign!");
                        callSign = null;
                    }
                } while (callSign == null);
                try {
                    shipSummary = shipSummaryController.getShipSummaryByCallSign(callSign);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            default:
                // code smell
                break;
        }

        if (shipSummary != null) System.out.println("\nSHIP'S SUMMARY : " + "\n" + shipSummary);

    }
}
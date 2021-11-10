package lapr.project.ui;

import lapr.project.controller.ShipSummaryControler;
import lapr.project.shared.exceptions.InvalidLineException;
import lapr.project.utils.Utils;

import java.io.FileNotFoundException;

public class ShipSummaryUI implements Runnable {

    public ShipSummaryUI() {
        //Empty
    }

    private final ShipSummaryControler shipSummaryControler = new ShipSummaryControler();

    @Override
    public void run() {

        int option;
        long mmsi;
        String imo;
        String callSign;

        String shipSummary = null;
        boolean readOptionBoolean = false;


        do {
            try {
                String readOption = Utils.readLineFromConsole("Do you wish to import a file (Y - filename/ N - no)");
                if (!readOption.equals("N")) {
                    readOptionBoolean = true;
                    shipSummaryControler.importShips(readOption);
                } else if (readOption.equals("N")) {
                    readOptionBoolean = true;
                } else if (!readOption.equals("Y") || !readOption.equals("N")) throw new IllegalArgumentException();
            } catch (IllegalArgumentException ex1) {
                System.out.println("Please enter a valid option! (Yes - FileName / No - N)");
            } catch (InvalidLineException | FileNotFoundException ex2) {
                System.out.println("Please enter a valid file (See the file names before entering one)");
            }
        } while (readOptionBoolean == false);


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
                shipSummary = shipSummaryControler.getShipSummaryByMMSI(mmsi);
                break;
            case 2:
                do {
                    try {
                        imo = Utils.readLineFromConsole("Please enter the desired Ship IMO:");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Please enter a valid IMO!");
                        imo = null;
                    }
                } while (imo == null);
                shipSummary = shipSummaryControler.getShipSummaryByIMO(imo);
                break;
            case 3:
                do {
                    try {
                        callSign = Utils.readLineFromConsole("Please enter the desired Ship call Sign:");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Please enter a valid call sign!");
                        callSign = null;
                    }
                } while (callSign == null);
                shipSummary = shipSummaryControler.getShipSummaryByCallSign(callSign);
                break;
            default:
                // code smell
                break;
        }

        if (shipSummary != null) System.out.println("SHIP'S SUMMARY : " + "\n" + shipSummary);

    }
}
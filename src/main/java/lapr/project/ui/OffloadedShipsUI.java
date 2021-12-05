package lapr.project.ui;

import lapr.project.controller.OffloadedShipsController;
import lapr.project.data.ConnectionFactory;
import lapr.project.data.DataBaseScripts.OffOrLoadContainers;
import lapr.project.data.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

public class OffloadedShipsUI implements Runnable {

    OffloadedShipsController offLoadedShipsController = new OffloadedShipsController();


    public void run() {
        int op;
        int decision;
        boolean bool = false;

        do {
            try {
                op = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                op = 0;
            }
        } while (op == 0);

        do {
            decision = Utils.readIntegerFromConsole("1.BDDAD | 2.Java");
        } while (decision != 1 && decision != 2);

        if (decision == 1) {

            String facilityId;
            DatabaseConnection databaseConnection = null;
            OffOrLoadContainers offOrLoadContainers = new OffOrLoadContainers();
            try {
                databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            facilityId = Utils.readLineFromConsole("Please enter the facility ID:");

            try {
                offOrLoadContainers.getResult(databaseConnection, op, "1");
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }

        } else {
            try {
                System.out.println();
                bool = offLoadedShipsController.offLoadedShips(op);
            } catch (NullPointerException ex) {
                System.out.println("The ship introduced doesn't exist.");
            }

            if (bool) {
                System.out.println("");
            } else {
                System.out.println("Operation failed! Please, try again.");
            }
        }
    }
}

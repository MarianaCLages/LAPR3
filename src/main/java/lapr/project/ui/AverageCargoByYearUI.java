package lapr.project.ui;

import lapr.project.controller.AverageCargoByYearController;
import lapr.project.shared.exceptions.*;

import java.sql.SQLException;

public class AverageCargoByYearUI implements Runnable {

    AverageCargoByYearController ctrl;

    public AverageCargoByYearUI() {
        this.ctrl = new AverageCargoByYearController();
    }

    @Override
    public void run() {
        int shipMmsi;
        int year;

        do {
            try {
                shipMmsi = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                shipMmsi = 0;
            }
        } while (shipMmsi == 0);

        do {
            try {
                year = Utils.readIntegerFromConsole("Please enter the year:");
            } catch (Exception ex) {
                System.out.println("Please enter a valid year!");
                year = 0;
            }

        } while (year == 0);

        try {
            System.out.println("\nAverage:");
            System.out.print(ctrl.averageCargoByYear(shipMmsi, year));
        } catch (ShipCargoCapacityException | ContainerGrossException | ContainersInsideCargoManifestListSizeException | CargoManifestIDException | CargoManifestDoesntBelongToThatShipException | VehicleIDNotValidException | IllegalArgumentException | SQLException exception) {
            System.out.println("The ship introduced doesn't exist.");
        }
    }
}

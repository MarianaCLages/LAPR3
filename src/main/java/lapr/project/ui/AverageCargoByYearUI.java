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
                System.out.println("Please enter a valid MMSI! (Digits: 9)");
                shipMmsi = 0;
            }
        } while (shipMmsi == 0);

        do {
            try {
                year = Utils.readIntegerFromConsole("Please enter the year:");

                if (year < 0) throw new IllegalArgumentException();

            } catch (Exception ex) {
                System.out.println("Please enter a valid year (No negatives values allowed)!");
                year = 0;
            }

        } while (year == 0);

        try {
            System.out.print(ctrl.averageCargoByYear(shipMmsi, year));
        } catch (ShipCargoCapacityException | NoContainersInsideThatTripException | NoCargoManifestsWereFoundInThatTripException | NoCargoManifestInThatDateException | SQLException | VehicleIDNotValidException | CargoManifestDoesntBelongToThatShipException | CargoManifestIDException | ContainersInsideCargoManifestListSizeException | ContainerGrossException e) {
            System.out.println(e.getMessage());
        }
    }
}

package lapr.project.ui;

import lapr.project.controller.LoadedContainersController;
import lapr.project.shared.exceptions.*;
import java.sql.SQLException;

public class LoadedContainersUI implements Runnable {

    LoadedContainersController ctrl;

    public LoadedContainersUI() {
        this.ctrl = new LoadedContainersController();
    }

    public void run() {
        int shipMmsi;
        String type = "2";

        do {
            try {
                shipMmsi = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                shipMmsi = 0;
            }
        } while (shipMmsi == 0);

        try {
            System.out.println("\nContainers to be loaded in the next port:");
            System.out.print(ctrl.getLoadContainers(shipMmsi, type));
        } catch (ShipCargoCapacityException | ContainerGrossException | ContainersInsideCargoManifestListSizeException | CargoManifestIDException | CargoManifestDoesntBelongToThatShipException | VehicleIDNotValidException | IllegalArgumentException | SQLException exception) {
            System.out.println("Operation failed! Please, try again.");
        }
    }
}


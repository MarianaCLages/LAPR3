package lapr.project.ui;

import lapr.project.controller.LoadedShipsController;
import lapr.project.shared.exceptions.*;
import java.sql.SQLException;

public class LoadedShipsUI implements Runnable {

    LoadedShipsController ctrl;

    public LoadedShipsUI() {
        this.ctrl = new LoadedShipsController();
    }

    public void run() {

        int shipMmsi;
        String facilityId;
        String type = "2";

        int op;


        do {
            try {
                shipMmsi = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                shipMmsi = 0;
            }
        } while (shipMmsi == 0);

        try {
            System.out.println("Ships to be loaded:");
            System.out.println(ctrl.getLoadedShips(shipMmsi, type));
        } catch (ShipCargoCapacityException | ContainerGrossException | ContainersInsideCargoManifestListSizeException | CargoManifestIDException | CargoManifestDoesntBelongToThatShipException | VehicleIDNotValidException | IllegalArgumentException | SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}


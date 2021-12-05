package lapr.project.ui;

import lapr.project.controller.OffloadedContainersController;
import lapr.project.shared.exceptions.ContainersInsideCargoManifestListSizeException;
import lapr.project.shared.exceptions.FacilityNotFoundException;

public class OffloadedContainersUI implements Runnable {

    OffloadedContainersController offLoadedShipsController = new OffloadedContainersController();

    public void run() {

        int mmsi;

        do {
            try {
                mmsi = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI (The MMSI must have 9 digits)!");
                mmsi = 0;
            }
        } while (mmsi == 0);

        try {
            System.out.println(offLoadedShipsController.offLoadedShips(mmsi));
        } catch (FacilityNotFoundException | ContainersInsideCargoManifestListSizeException e) {
            System.out.println(e.getMessage());
        }


    }
}
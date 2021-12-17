package lapr.project.ui;

import lapr.project.controller.Create3DMatrixController;
import lapr.project.shared.exceptions.MatrixFileException;

import java.io.IOException;
import java.sql.SQLException;

public class Create3DMatrixUI implements Runnable {

    Create3DMatrixController ctrl;

    public Create3DMatrixUI() {
        this.ctrl = new Create3DMatrixController();
    }

    public void run() {
        int shipMmsi;
        String cargoManifestId = null;

        do {
            try {
                shipMmsi = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                shipMmsi = 0;
            }
        } while (shipMmsi == 0);

        try {
            cargoManifestId = Utils.readLineFromConsole("Please enter the cargo manifest ID:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            ctrl.createMatrix(cargoManifestId, shipMmsi);
            System.out.println("Operation success!");
        } catch (MatrixFileException | SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
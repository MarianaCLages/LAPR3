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
        String cargoManifestId = null;

        try {
            cargoManifestId = Utils.readLineFromConsole("Please enter the cargo manifest ID:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            ctrl.createMatrix(cargoManifestId);
            System.out.println("Operation success!");
        } catch (MatrixFileException | SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
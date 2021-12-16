package lapr.project.ui;

import lapr.project.controller.Create3DMatrixController;
import lapr.project.shared.exceptions.MatrixFileException;

import java.sql.SQLException;

public class Create3DMatrixUI implements Runnable {

    Create3DMatrixController ctrl;

    public Create3DMatrixUI(){
        this.ctrl = new Create3DMatrixController();
    }

    public void run() {
        int shipMmsi;

        do {
            try {
                shipMmsi = Utils.readIntegerFromConsole("Please enter the ship's MMSI:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                shipMmsi = 0;
            }
        } while (shipMmsi == 0);

        try {
            ctrl.createMatrix(shipMmsi);
        } catch (MatrixFileException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
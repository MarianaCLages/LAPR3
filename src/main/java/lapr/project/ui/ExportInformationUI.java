package lapr.project.ui;

import lapr.project.controller.ExportInformationController;
import lapr.project.shared.exceptions.InvalidCargoManifestException;

import java.sql.SQLException;

public class ExportInformationUI implements Runnable {
    ExportInformationController ctrl;

    public ExportInformationUI() {
        this.ctrl = new ExportInformationController();
    }

    @Override

    public void run() {
        String cargoManifestId = null;

        try {
            cargoManifestId = Utils.readLineFromConsole("Please enter the cargo manifest ID:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            ctrl.export(cargoManifestId);
            System.out.println("Operation success!");
        } catch (SQLException | InvalidCargoManifestException e) {
            System.out.println(e.getMessage());
        }
    }
}

package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.shared.ContainerInformationExporter;
import lapr.project.shared.exceptions.InvalidCargoManifestException;

import java.sql.SQLException;

public class ExportInformationController {
    DatabaseConnection databaseConnection;

    public ExportInformationController() {
        databaseConnection = App.getInstance().getDatabaseConnection();
    }

    public boolean export(String cargoManifestId) throws SQLException, InvalidCargoManifestException {
        return ContainerInformationExporter.exportInformation(cargoManifestId, databaseConnection);
    }
}

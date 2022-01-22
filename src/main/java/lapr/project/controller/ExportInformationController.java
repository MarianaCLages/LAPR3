package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.shared.ContainerInformationExporter;
import lapr.project.shared.exceptions.InvalidCargoManifestException;

import java.sql.SQLException;

public class ExportInformationController {

    DatabaseConnection databaseConnection;

    /**
     * Constructor.
     */
    public ExportInformationController() {
        databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Exports the container information.
     *
     * @param cargoManifestId the cargo manifest ID
     * @return true if it succeeds, false if it doesn't
     * @throws SQLException
     * @throws InvalidCargoManifestException
     */
    public boolean export(String cargoManifestId) throws SQLException, InvalidCargoManifestException {
        return ContainerInformationExporter.exportInformation(cargoManifestId, databaseConnection);
    }
}

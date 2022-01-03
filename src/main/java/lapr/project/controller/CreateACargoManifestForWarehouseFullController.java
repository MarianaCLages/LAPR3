package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.shared.exceptions.InvalidCargoManifestException;
import lapr.project.shared.exceptions.InvalidContainerException;

import java.sql.SQLException;

public class CreateACargoManifestForWarehouseFullController {

    private final DatabaseConnection databaseConnection;

    /**
     * Constructor.
     */
    public CreateACargoManifestForWarehouseFullController() {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Creates a cargo manifest container (full warehouse).
     * @param cargoManifestId the cargo manifest ID
     * @param containerID the container ID
     * @param xPos the container's x position
     * @param yPos the container's y position
     * @param zPos the container's z position
     * @return true if it succeeds, false if it doesn't
     * @throws SQLException
     * @throws InvalidContainerException
     * @throws InvalidCargoManifestException
     */
    public boolean createCMC(String cargoManifestId, String containerID, int xPos, int yPos, int zPos) throws SQLException, InvalidContainerException, InvalidCargoManifestException {
        return DataBaseUtils.createCargoManifestContainer(databaseConnection, cargoManifestId, containerID, xPos, yPos, zPos);
    }
}

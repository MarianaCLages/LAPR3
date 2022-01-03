package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.shared.exceptions.InvalidCargoManifestException;
import lapr.project.shared.exceptions.InvalidContainerException;
import lapr.project.shared.exceptions.MatrixFileException;

import java.io.IOException;
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
     * Creates the 3D matrix.
     *
     * @param cargoManifestId the cargo manifest ID
     * @return true if it succeeds, false if it doesn't
     * @throws MatrixFileException
     * @throws SQLException
     * @throws IOException
     */
    public boolean createCMC(String cargoManifestId, String containerID, int xPos, int yPos, int zPos) throws SQLException, InvalidContainerException, InvalidCargoManifestException {
        return DataBaseUtils.createCargoManifestContainer(databaseConnection, cargoManifestId, containerID, xPos, yPos, zPos);
    }


}

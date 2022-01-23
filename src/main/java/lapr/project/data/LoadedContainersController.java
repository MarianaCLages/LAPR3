package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.data.DataBaseScripts.OffOrLoadContainers;
import lapr.project.data.DatabaseConnection;

import java.sql.SQLException;

public class LoadedContainersController {

    private final OffOrLoadContainers offOrLoadContainers;
    private final DatabaseConnection databaseConnection;

    /**
     * Constructor.
     */
    public LoadedContainersController() {
        this.offOrLoadContainers = new OffOrLoadContainers();
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the containers to be loaded.
     *
     * @param mmsi the ship's MMSI
     * @param type the ship's type (to be loaded)
     * @return the containers to be loaded
     * @throws SQLException in case a error occurs in the DB
     */
    public String getLoadContainers(int mmsi, String type) throws SQLException {
        return (offOrLoadContainers.getResultLoaded(databaseConnection, mmsi, type));
    }
}
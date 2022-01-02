package lapr.project.controller;

import lapr.project.data.DataBaseScripts.OffOrLoadContainers;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.ContainersInsideCargoManifestListSizeException;
import lapr.project.shared.exceptions.FacilityNotFoundException;

public class OffloadedContainersController {

    private final DatabaseConnection databaseConnection;
    private final OffOrLoadContainers offOrLoadContainers;

    /**
     * Constructor
     */
    public OffloadedContainersController() {
        databaseConnection = App.getInstance().getDatabaseConnection();
        offOrLoadContainers = new OffOrLoadContainers();
    }

    /**
     * Gets the ships to be offloaded in the nearest port.
     *
     * @param mmsi the ship's MMSI
     * @return the ships to be offloaded in the nearest port
     */
    public String offLoadedShips(int mmsi) throws FacilityNotFoundException, ContainersInsideCargoManifestListSizeException {
        return offOrLoadContainers.getResultOffLoaded(databaseConnection, mmsi, 1);
    }
}

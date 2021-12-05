package lapr.project.controller;

import lapr.project.data.DataBaseScripts.OffOrLoadContainers;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.*;

import java.sql.SQLException;

public class LoadedShipsController {

    private final OffOrLoadContainers offOrLoadContainers;
    private final DatabaseConnection databaseConnection;

    /**
     * Constructor.
     */
    public LoadedShipsController() {
        this.offOrLoadContainers = new OffOrLoadContainers();
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the containers to be loaded.
     *
     * @param mmsi the ship's MMSI
     * @param type the ship's type (to be loaded)
     * @return the containers to be loaded
     * @throws ShipCargoCapacityException
     * @throws ContainerGrossException
     * @throws ContainersInsideCargoManifestListSizeException
     * @throws CargoManifestIDException
     * @throws CargoManifestDoesntBelongToThatShipException
     * @throws VehicleIDNotValidException
     * @throws IllegalArgumentException
     * @throws SQLException
     */
    public String getLoadContainers(int mmsi, String type) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestIDException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException, SQLException {
        return (offOrLoadContainers.getResultLoaded(databaseConnection, mmsi, type));
    }
}
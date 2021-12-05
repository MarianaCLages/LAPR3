package lapr.project.controller;

import lapr.project.data.DataBaseScripts.OffOrLoadContainers;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.model.stores.PortStore;
import lapr.project.model.stores.ShipStore;

public class OffloadedShipsController {

    private final ShipStore shipStore;
    private final PortStore portStore;
    private final DatabaseConnection databaseConnection;
    private final OffOrLoadContainers offOrLoadContainers;

    /**
     * Constructor
     */
    public OffloadedShipsController() {
        Company company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
        portStore = company.getPortStore();
        databaseConnection = App.getInstance().getDatabaseConnection();
        offOrLoadContainers = new OffOrLoadContainers();
    }

    /**
     * Gets the ships to be offloaded in the nearest port.
     *
     * @param mmsi the ship's mmsi
     * @return the ships to be offloaded in the nearest port
     */
    public boolean offLoadedShips(int mmsi) {

        return offOrLoadContainers.getResult(databaseConnection,mmsi,1);
    }
}

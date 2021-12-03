package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.stores.PortStore;
import lapr.project.model.stores.ShipStore;

public class LoadedShipsController {

    private final ShipStore shipStore;
    private final PortStore portStore;

    /**
     * Constructor.
     */
    public LoadedShipsController() {
        Company company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
        this.portStore = company.getPortStore();
    }

    /**
     * Gets the ship store.
     *
     * @return the ship store
     */
    public ShipStore getShipStore() {
        return shipStore;
    }

    /**
     * Gets the ships to be loaded in the nearest port.
     *
     * @param mmsi the ship's MMSI
     * @return the ships to be loaded in the nearest port
     */
    public boolean loadedShips(int mmsi) {
        Ship ship = shipStore.getShipByMmsi(mmsi);

        ship.setBiggestPosition();
        Position position = ship.getBiggestPosition();

        Port port = portStore.getPortList().nearestNeighborPort(position);

        return ship.giveCargoLoadedSign(port);
    }
}

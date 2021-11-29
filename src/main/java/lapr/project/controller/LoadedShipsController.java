package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.stores.PortStore;
import lapr.project.model.stores.ShipStore;

public class LoadedShipsController {

    private final ShipStore shipStore;
    private final PortStore portStore;

    public LoadedShipsController() {
        Company company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
        this.portStore = company.getPortStore();
    }

    public boolean loadedShips(int mmsi) {
        Ship ship = shipStore.getShipByMmsi(mmsi);

        ship.setBiggestPosition();
        Position position = ship.getBiggestPosition();

        Port port = portStore.getPortList().nearestNeighborPort(position);

        return ship.giveCargoLoadedSign(port);
    }
}

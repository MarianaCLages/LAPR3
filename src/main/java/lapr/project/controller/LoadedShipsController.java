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
        try {
            Ship s = shipStore.getShipByMmsi(mmsi);

            s.setBiggestPosition();
            Position position = s.getBiggestPosition();

            Port port = portStore.getList().nearesNeighbor(position);

            return s.giveCargoLoadedSign(port);

        } catch (NullPointerException ex) {
            System.out.println("The ship introduced doesn't exist.");
            return false;
        }
    }
}

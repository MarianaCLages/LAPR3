package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.model.stores.PortStore;
import lapr.project.model.stores.ShipStore;

public class OffLoadedShipsController {

    private final ShipStore shipStore;
    private final PortStore portStore;


    public OffLoadedShipsController() {
        Company company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
        portStore = company.getPortStore();
    }

    public boolean offLoadedShips(int mmsi) {
        try {
            Ship ship = shipStore.getShipByMmsi(mmsi);

            ship.setBiggestPosition();
            Position position = ship.getBiggestPosition();

            Port port = portStore.getPortList().nearestNeighborPort(position);

            return ship.giveCargoOffLoadedSign(port);

        } catch (NullPointerException ex) {
            System.out.println("The ship introduced doesn't exist.");
            return false;
        }
    }
}

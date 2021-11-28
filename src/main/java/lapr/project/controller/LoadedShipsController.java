package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.model.stores.PortStore;
import lapr.project.model.stores.ShipStore;

public class LoadedShipsController {

    private Company company;
    private ShipStore shipStore;
    private PortStore portStore;


    public LoadedShipsController() {
        company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
        portStore = company.getPortStore();
    }

    public boolean loadedShips(int mmsi) {
        try {
            Ship s = shipStore.getShipByMmsi(mmsi);

            s.setBiggestPosition();
            Position pos = s.getBiggestPosition();

            Port p = portStore.getList().nearesNeighbor(pos);

            return s.giveCargoASignLoaded(p);

        } catch (NullPointerException ex) {
            System.out.println("The ship introduced doesn't exist. Please, try again.");
            return false;
        }
    }
}

package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

import java.time.LocalDateTime;
import java.util.List;

public class TopNShipsController {

    Company company;
    ShipStore shipStore;

    public TopNShipsController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    public List<Ship> getTopNShips(int n, String vesselType, LocalDateTime li, LocalDateTime lf) {
        List<Ship> lShip = shipStore.getTopN(n, vesselType, li, lf);

        if (lShip == null) {
            return null;
        } else {
            return lShip;
        }
    }
}

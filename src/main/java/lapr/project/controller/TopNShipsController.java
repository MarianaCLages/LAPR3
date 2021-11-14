package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

import java.time.LocalDateTime;
import java.util.List;

public class TopNShipsController {

    Company company;
    ShipStore shipStore;

    /**
     * Constructor.
     */
    public TopNShipsController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    /**
     * Gets the top-N ships in a period of time.
     *
     * @param n          the n value
     * @param vesselType the ship's vessel type
     * @param li         the initial date
     * @param lf         the final date
     * @return the top-N ships in a period of time
     */
    public List<Ship> getTopNShips(int n, String vesselType, LocalDateTime li, LocalDateTime lf) {
        return shipStore.getTopN(n, vesselType, li, lf);
    }

    /**
     * Gets the ship store.
     *
     * @return the ship store
     */
    public ShipStore getShipStore() {
        return shipStore;
    }
}

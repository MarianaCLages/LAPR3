package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.stores.ShipStore;

public class PairsOfShipsController {

    private final Company company;
    private final ShipStore shipStore;

    /**
     * Constructor.
     */
    public PairsOfShipsController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    /**
     * Gets the pairs of ships.
     */
    public String getPairs() {
        return shipStore.getPairsOfShipsString();
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
     * Gets the size of the MMSI AVL.
     *
     * @return the size of the MMSI AVL
     */
    public int getSize() {
        return shipStore.getShipByMmsiAVL().size();
    }
}
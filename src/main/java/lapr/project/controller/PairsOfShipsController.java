package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.stores.ShipStore;

public class PairsOfShipsController {

    private final Company company;
    private final ShipStore shipStore;

    public PairsOfShipsController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    public String getPairs() {
        return shipStore.getPairsOfShipsString();
    }

    public ShipStore getShipStore() {
        return shipStore;
    }

    public int getSize() {
        return shipStore.getPairsOfShipsSearchTree().size();
    }

}

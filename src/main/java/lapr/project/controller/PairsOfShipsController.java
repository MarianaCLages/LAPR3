package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.stores.ShipStore;

public class PairsOfShipsController {

    private Company company;
    private ShipStore shipStore;

    public PairsOfShipsController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    public String getPairs() {
        return shipStore.getPairsOfShipsString();
    }

}

package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

import java.util.Date;
import java.util.List;

public class FindAvailableShipsController {

    private final ShipStore shipStore;

    public FindAvailableShipsController(){

        Company company = App.getInstance().getCompany();
        shipStore = company.getShipStore();

    }

    public List<Ship> getAvailableShipsAtDate(Date pDate) {
        return shipStore.getAvailableShipsAtDate(pDate);
    }
}

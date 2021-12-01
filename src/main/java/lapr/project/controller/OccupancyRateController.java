package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

public class OccupancyRateController {

    private Company company;
    private ShipStore shipStore;

    public OccupancyRateController(){

        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    public String OccupancyRateController(int mmsi){

        Ship ship = shipStore.getShipByMmsi(mmsi);

        String s = ship.getOccupancyRate();

        return s;
    }
}

package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

public class AverageCargoByYearController {

    private final ShipStore shipStore;

    public AverageCargoByYearController(){
        Company company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
    }

    public String AverageCargoByYearController(int mmsi, int year){
        Ship ship = shipStore.getShipByMmsi(mmsi);

        return ship.writeCargoByYear(year);
    }
}

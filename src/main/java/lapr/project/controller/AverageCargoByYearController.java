package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;
import lapr.project.ui.AverageCargoByYearUI;

public class AverageCargoByYearController {

    private Company company;
    private ShipStore shipStore;

    public AverageCargoByYearController(){

        Company company = App.getInstance().getCompany();
        ShipStore shipStore = company.getShipStore();
    }

    public String AverageCargoByYearController(int mmsi, int year){


        Ship s = shipStore.getShipByMmsi(mmsi);
        String str = s.writeCargoByYear(year);

        return str;
    }
}

package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

public class SearchShipController {

    private Company cmpn;
    private ShipStore ss;

    public SearchShipController(){
        this.cmpn = App.getInstance().getCompany();
        ss = cmpn.getShipStore();
    }

    public Ship searchShipByMMSI(int p) {
        return ss.getShipByMMSI(p);
    }

    public Ship searchShipByIMO(String p) {
        return ss.getShipByIMO(p);
    }

    public Ship searchShipByCallSign(String p) {
        return ss.getShipByCallSign(p);
    }

    public ShipStore getShipStore(){
        return ss;
    }
}
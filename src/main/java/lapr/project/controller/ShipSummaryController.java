package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.stores.ShipStore;

public class ShipSummaryController {

    private final Company company;
    private final ShipStore shipStore;

    public ShipSummaryController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    public String getShipSummaryByMMSI(int mmsi) throws IllegalArgumentException, NullPointerException {
        return shipStore.getShipSummaryByMMSI(mmsi);
    }

    public String getShipSummaryByIMO(String imo) throws IllegalArgumentException {
        return shipStore.getShipSummaryByIMO(imo);
    }

    public String getShipSummaryByCallSign(String callSign) throws IllegalArgumentException {
        return shipStore.getShipSummaryByCallSign(callSign);
    }

    public ShipStore getShipStore() {
        return shipStore;
    }
}

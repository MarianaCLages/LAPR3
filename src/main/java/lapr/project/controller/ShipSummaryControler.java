package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.ShipImporter;
import lapr.project.model.stores.ShipStore;
import lapr.project.shared.exceptions.InvalidLineException;

import java.io.File;
import java.io.FileNotFoundException;

public class ShipSummaryControler {

    private Company company;
    private ShipStore shipStore;

    public ShipSummaryControler() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    public String getShipSummaryByMMSI(long mmsi) throws IllegalArgumentException {
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

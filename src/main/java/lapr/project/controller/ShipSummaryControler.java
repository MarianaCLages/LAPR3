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

    public String getShipSummaryByMMSI(long mmsi) {
        return shipStore.getShipSummaryByMMSI(mmsi);
    }

    public String getShipSummaryByIMO(String imo) {
        return shipStore.getShipSummaryByIMO(imo);
    }

    public String getShipSummaryByCallSign(String callSign) {
        return shipStore.getShipSummaryByCallSign(callSign);
    }

    public void importShips(String fileName) throws InvalidLineException, FileNotFoundException {
        ShipImporter.importsShips(new File(fileName));
    }

}

package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.ShipImporter;
import lapr.project.shared.exceptions.InvalidLineException;

import java.io.File;
import java.io.FileNotFoundException;

public class ImportShipsController {

    private final Company company;

    public ImportShipsController() {
        this.company = App.getInstance().getCompany();
    }

    public boolean importShips(String fileName) throws InvalidLineException, FileNotFoundException {
        boolean returnValue = false;
        returnValue = ShipImporter.importsShips(new File(fileName));
        company.getShipStore().calculateTravelledDistanceOfAllShips();
        return returnValue;
    }
}

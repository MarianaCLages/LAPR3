package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.ui.ShipImporter;
import lapr.project.shared.exceptions.InvalidLineException;

import java.io.File;
import java.io.FileNotFoundException;

public class ImportShipsController {

    private final Company company;

    /**
     * Constructor.
     */
    public ImportShipsController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Imports ships from file. If necessary to add to the database implement the method to save in the DB.
     *
     * @param fileName the file
     * @return true if it succeeds, false if it doesn't
     * @throws InvalidLineException in case that a line is invalid
     * @throws FileNotFoundException in case the file doesn't exit/wasn't found
     */
    public boolean importShips(String fileName) throws InvalidLineException, FileNotFoundException {
        boolean returnValue;
        returnValue = ShipImporter.importsShips(new File(fileName));

        company.getShipStore().calculateTravelledDistanceOfAllShips();

        return returnValue;
    }
}

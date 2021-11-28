package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.PortImporter;
import lapr.project.model.ShipImporter;
import lapr.project.shared.exceptions.InvalidLineException;

import java.io.File;
import java.io.FileNotFoundException;

public class ImportPortsController {

    private final Company company;

    /**
     * Constructor.
     */
    public ImportPortsController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Imports ships from file.
     *
     * @param fileName the file
     * @return true if it succeeds, false if it doesn't
     * @throws InvalidLineException
     * @throws FileNotFoundException
     */

    public boolean importPorts(String fileName) throws FileNotFoundException {

        boolean returnValue;

        returnValue = PortImporter.importPorts(new File(fileName));

        company.getShipStore().calculateTravelledDistanceOfAllShips();

        return returnValue;

    }

}

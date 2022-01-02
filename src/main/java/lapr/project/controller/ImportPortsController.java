package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;
import lapr.project.model.stores.PortStore;
import lapr.project.shared.exceptions.InvalidLineException;

import java.io.FileNotFoundException;

public class ImportPortsController {

    /**
     * Constructor.
     */
    public ImportPortsController() {
        Company company = App.getInstance().getCompany();
        PortStore store = company.getPortStore();
        DatabaseConnection dbConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Imports ports from file.
     *
     * @param fileName the file
     * @return true if it succeeds, false if it doesn't
     * @throws InvalidLineException
     * @throws FileNotFoundException
     */

    public boolean importPorts(String fileName) throws FileNotFoundException {
        boolean returnValue;

        // returnValue = PortImporter.importPorts(new File(fileName), store, portStoreData,dbConnection);
        returnValue = true;
        return returnValue;
    }
}


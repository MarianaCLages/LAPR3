package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.PortStoreData;
import lapr.project.model.Company;
import lapr.project.model.PortImporter;
import lapr.project.model.stores.PortStore;
import lapr.project.shared.exceptions.InvalidLineException;

import java.io.File;
import java.io.FileNotFoundException;

public class ImportPortsController {

    private final PortStore store;
    private final PortStoreData portStoreData;
    private final DatabaseConnection dbConnection;

    /**
     * Constructor.
     */
    public ImportPortsController() {
        this.dbConnection = App.getInstance().getDatabaseConnection();
        Company company = App.getInstance().getCompany();
        this.store = company.getPortStore();
        this.portStoreData = company.getPortStoreData();
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

        returnValue = PortImporter.importPorts(new File(fileName), store, portStoreData,dbConnection);

        return returnValue;
    }
}


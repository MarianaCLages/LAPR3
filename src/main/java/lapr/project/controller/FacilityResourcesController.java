package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CallTheAvailableResourcesFunction;
import lapr.project.data.DatabaseConnection;

import java.io.IOException;

import java.sql.*;

public class FacilityResourcesController {

    private final DatabaseConnection connection;
    private final CallTheAvailableResourcesFunction callTheAvailableResourcesFunction;

    /**
     * Constructor
     */
    public FacilityResourcesController() {
        connection = App.getInstance().getDatabaseConnection();
        callTheAvailableResourcesFunction = new CallTheAvailableResourcesFunction();
    }

    /**
     * Gets the available resources of a facility.
     *
     * @param month the month
     * @param year  the year
     * @param id    the facility ID
     * @return the available resources of a facility
     * @throws SQLException
     * @throws IOException
     */
    public String getFacilityResources(int month, int year, int id) throws SQLException, IOException {
        return callTheAvailableResourcesFunction.callFunction(month, year, id, connection);
    }
}

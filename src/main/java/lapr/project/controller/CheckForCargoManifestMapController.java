package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CheckForCargoManifestMapFunction;
import lapr.project.data.DatabaseConnection;

import java.sql.Date;
import java.sql.SQLException;

public class CheckForCargoManifestMapController {

    private final DatabaseConnection databaseConnection;

    /**
     * Constructor.
     */
    public CheckForCargoManifestMapController() {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the function that generates the loading and unloading map.
     *
     * @param rPortID the port ID
     * @param rDate   the date
     * @return the loading and unloading map
     * @throws SQLException
     */
    public String callFunction(String rPortID, Date rDate) throws SQLException {
        return CheckForCargoManifestMapFunction.callFunction(databaseConnection, rPortID, rDate);
    }
}

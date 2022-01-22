package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CheckForCargoManifestMapFunction;
import lapr.project.data.DatabaseConnection;

import java.sql.Date;
import java.sql.SQLException;

public class CheckForCargoManifestMapController {

    CheckForCargoManifestMapFunction function = new CheckForCargoManifestMapFunction();
    DatabaseConnection databaseConnection = App.getInstance().getDatabaseConnection();


    public String callFunction(String rPortID, Date rDate) throws SQLException {
        return function.callFunction(databaseConnection,rPortID,rDate);
    }
}

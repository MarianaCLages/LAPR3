package lapr.project.controller;

import lapr.project.data.DataBaseScripts.GetAuditTrailScript;
import lapr.project.data.DatabaseConnection;

import java.sql.SQLException;

public class GetAuditTrailController {

    private final DatabaseConnection databaseConnection;

    public GetAuditTrailController() {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    public String getAuditTrail() throws SQLException {
        return GetAuditTrailScript.getAuditTrail(databaseConnection);
    }
}

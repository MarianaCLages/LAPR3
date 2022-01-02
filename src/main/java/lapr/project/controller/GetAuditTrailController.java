package lapr.project.controller;

import lapr.project.data.DataBaseScripts.GetAuditTrailScript;
import lapr.project.data.DatabaseConnection;

import java.sql.SQLException;

public class GetAuditTrailController {

    private final DatabaseConnection databaseConnection;

    /**
     * Constructor.
     */
    public GetAuditTrailController() {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the audit trail.
     *
     * @return the audit trail info
     * @throws SQLException
     */
    public String getAuditTrail() throws SQLException {
        return GetAuditTrailScript.getAuditTrail(databaseConnection);
    }
}

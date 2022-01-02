package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAuditTrailScript {

    /**
     * Constructor.
     */
    public GetAuditTrailScript() {
        //Empty constructor
    }

    /**
     * Gets the audit trail.
     *
     * @param databaseConnection the database connection
     * @return the audit trail
     * @throws SQLException
     */
    public static String getAuditTrail(DatabaseConnection databaseConnection) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        StringBuilder sb = new StringBuilder();

        sb.append("\nAvailable Information about the CargoManifest/Container Table: \n");

        String sqlCommand = "SELECT * FROM AUDITTRAIL";
        try (PreparedStatement auditTrailStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet auditTrailResultSet = auditTrailStatement.executeQuery()) {

                while (auditTrailResultSet.next()) {

                    sb.append("Cargo Manifest ID: ").append(auditTrailResultSet.getString(1))
                            .append("\nContainer ID: ").append(auditTrailResultSet.getString(2))
                            .append("\nOperation Date: ").append(auditTrailResultSet.getObject(3))
                            .append("\nOperation Type: ").append(auditTrailResultSet.getString(4))
                            .append("\nUser Logged: ").append(auditTrailResultSet.getString(5))
                            .append("\n\n");
                }
            }
        }
        return sb.toString();
    }
}
package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.*;

public class CheckForCargoManifestMapFunction {

    /**
     * Constructor.
     */
    public CheckForCargoManifestMapFunction() {
        //Empty constructor
    }

    //US407

    /**
     * Calls the function that generates the loading and unloading map.
     *
     * @param databaseConnection the database connection
     * @param facilityID         the facility ID
     * @param date               the date
     * @return the loading and unloading map
     * @throws SQLException
     */
    public static String callFunction(DatabaseConnection databaseConnection, String facilityID, java.sql.Date date) throws SQLException {
        try (CallableStatement cstmt = databaseConnection.getConnection().prepareCall("{? = call fncCheckForCargoManifestMap(?,?)}")) {

            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, facilityID); //8
            cstmt.setDate(3, date);

            cstmt.executeUpdate();

            return cstmt.getString(1);
        }
    }
}
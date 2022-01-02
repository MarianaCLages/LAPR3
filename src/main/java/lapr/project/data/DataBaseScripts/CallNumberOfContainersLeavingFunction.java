package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

public class CallNumberOfContainersLeavingFunction {

    /**
     * Constructor.
     */
    private CallNumberOfContainersLeavingFunction() {
        // Empty constructor
    }

    /**
     * Calls the function that gets the number of containers leaving next month.
     *
     * @param connection the database connection
     * @param facilityId the facility ID
     * @param date       the date
     * @return the number of containers leaving next month
     */
    public static int numberOfContainers(DatabaseConnection connection, String facilityId, Date date) {
        int returnValue = 0;
        String sqlString = "{? = call GETCONTAINERSLEAVINGNEXTMONTH(?,?)}";
        try (CallableStatement cstmt = connection.getConnection().prepareCall(sqlString)) {
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, facilityId);
            cstmt.setDate(3, date);

            cstmt.execute();
            returnValue = cstmt.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
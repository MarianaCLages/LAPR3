package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class CallOccupationRateFunction {

    /**
     * Constructor.
     */
    public CallOccupationRateFunction() {
        // Empty constructor
    }

    /**
     * Calls the function that gets the warehouse occupation rate.
     *
     * @param connection the database connection
     * @param facilityId the facility ID
     * @return the warehouse occupation rate
     */
    public static double occupationRateFunction(DatabaseConnection connection, String facilityId) {
        double returnValue = 0;
        String sqlString = "{? = call GETWAREHOUSEOCCUPATIONRATE(?)}";
        try (CallableStatement cstmt = connection.getConnection().prepareCall(sqlString)) {
            cstmt.registerOutParameter(1, Types.FLOAT);
            cstmt.setString(2, facilityId);

            cstmt.execute();
            returnValue = cstmt.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}

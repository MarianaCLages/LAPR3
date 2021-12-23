package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class CallOccupationRateFunction {
    private CallOccupationRateFunction() {
    }

    public static double occupationRateFunction(DatabaseConnection connection, String facilityId) {
        double returnValue = 0;
        String sqlString = "{? = CALL GETWAREHOUSEOCCUPATIONRATE(?)}";
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

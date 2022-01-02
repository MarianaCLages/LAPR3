package lapr.project.data.DataBaseScripts;

import lapr.project.controller.App;
import lapr.project.data.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class CheckWarehouseExistsFunction {


    public static boolean warehouseExists(DatabaseConnection connection, String facilityId) {
        int returnValue = 0;
        String sqlString = "{? = call LAPR3_G096.FNVALIDWAREHOUSE(?)}";
        try (CallableStatement cstmt = connection.getConnection().prepareCall(sqlString)) {
            cstmt.registerOutParameter(1, Types.NUMERIC);
            cstmt.setString(2, facilityId);

            cstmt.execute();
            returnValue = cstmt.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue != 0;

    }
}

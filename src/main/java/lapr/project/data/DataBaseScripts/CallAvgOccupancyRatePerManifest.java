/*package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

public class CallAvgOccupancyRatePerManifest {

    public CallAvgOccupancyRatePerManifest() {
        // Empty constructor
    }

    public static double occupationRateFunction(DatabaseConnection connection, int mmsi, String begin, String end) {
        double returnValue = 0;
        String sqlString = "{? = call fnGetAverageOccupancyRatePerManifest(?,?,?)}";
        try (CallableStatement cstmt = connection.getConnection().prepareCall(sqlString)) {
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setInt(2, mmsi);
            cstmt.setDate(3, Date.valueOf(begin));
            cstmt.setDate(4, Date.valueOf(end));

            cstmt.execute();
            returnValue = cstmt.getDouble(1);

        } catch (SQLException e) {
            e.printStackTrace(); //Fazer exceçao - si
        }
        return returnValue;
    }
}
*/
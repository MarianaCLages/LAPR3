package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.InvalidShipException;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CallAvgOccupancyRatePerManifest {

    public CallAvgOccupancyRatePerManifest() {
        // Empty constructor
    }

    public static double occupationRateFunction(DatabaseConnection connection, int mmsi, String begin, String end) throws InvalidShipException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date parsedBegin = null;
        java.util.Date parsedEnd = null;

        try {
            parsedBegin = format.parse(begin);
            parsedEnd = format.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Date beginDate = new java.sql.Date(parsedBegin.getTime());
        java.sql.Date endDate = new java.sql.Date(parsedEnd.getTime());

        if (endDate.before(beginDate))
            throw new IllegalArgumentException("\nThe trip begin date has a value that is after the trip end date! Please try again.");

        double returnValue = 0;
        String sqlString = "{? = call fnGetAverageOccupancyRatePerManifest(?,?,?)}";
        try (CallableStatement cstmt = connection.getConnection().prepareCall(sqlString)) {
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setInt(2, mmsi);
            cstmt.setDate(3, beginDate);
            cstmt.setDate(4, endDate);

            cstmt.execute();
            returnValue = cstmt.getDouble(1);

        } catch (SQLException e) {
           throw new InvalidShipException();
        }

        if(returnValue > 100) {
            throw new IllegalArgumentException("The cargo manifest has a weight that overboards the current capacity of the ship! Please verify the capacity of the ship and change it!");
        }

        return returnValue;
    }
}

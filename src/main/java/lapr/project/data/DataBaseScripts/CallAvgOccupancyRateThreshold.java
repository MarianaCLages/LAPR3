package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.InvalidShipException;
import lapr.project.shared.exceptions.NoValidInformationInsideThatTripExeception;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CallAvgOccupancyRateThreshold {

    public CallAvgOccupancyRateThreshold() {
        //Empty Constructor
    }

    public static String occupationRateFunction(DatabaseConnection connection, int mmsi, String begin, String end, int treshold) throws IllegalArgumentException, InvalidShipException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Date parsedBegin = null;
        java.util.Date parsedEnd = null;

        try {
            parsedBegin = format.parse(begin);
            parsedEnd = format.parse(end);
        } catch (ParseException e) {
            throw new IllegalArgumentException("There was an error while parsing the date! Please verify the same.");
        }

        java.sql.Date beginDate = new java.sql.Date(parsedBegin.getTime());
        java.sql.Date endDate = new java.sql.Date(parsedEnd.getTime());

        if (endDate.before(beginDate))
            throw new IllegalArgumentException("\nThe trip begin date has a value that is after the trip end date!! Please try again!");

        String returnValue = null;
        String sqlString = "{? = call fnOccupancyRateWithThresholdPerVoyage(?,?,?,?)}";
        try (CallableStatement cstmt = connection.getConnection().prepareCall(sqlString)) {
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setInt(2, mmsi);
            cstmt.setDate(3, beginDate);
            cstmt.setDate(4, endDate);
            cstmt.setInt(5, treshold);

            cstmt.execute();
            returnValue = cstmt.getNString(1);

        } catch (SQLException e) {
            throw new InvalidShipException();
        }

        return returnValue;
    }
}
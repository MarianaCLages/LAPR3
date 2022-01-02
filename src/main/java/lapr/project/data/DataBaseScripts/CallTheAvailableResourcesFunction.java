package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CallTheAvailableResourcesFunction {


    public CallTheAvailableResourcesFunction() {
        //EMPTY
    }

    public String callFunction(int month, int year, int id, DatabaseConnection connection) throws SQLException {

        int day;

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
            day = 31;
        else if (month == 2 && (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0))
            day = 29;
        else if (month == 2 && (year % 4 != 0) && (year % 100 == 0) || (year % 400 != 0))
            day = 28;
        else
            day = 30;

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1); // <-- months start
        // at 0.
        cal.set(Calendar.DAY_OF_MONTH, 1);

        java.sql.Date dateI = new java.sql.Date(cal.getTimeInMillis());

        CallableStatement cstmt = connection.getConnection().prepareCall("{? = call fnGetShipAreaByDate(?,?,?)}");

        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.setInt(2, id); //8
        cstmt.setDate(3, dateI);
        cstmt.setInt(4, day);

        cstmt.executeUpdate();

        CallableStatement cstmt2 = connection.getConnection().prepareCall("{? = call fnGetContainerByDate(?,?,?)}");

        cstmt2.registerOutParameter(1, Types.VARCHAR);
        cstmt2.setInt(2, id);
        cstmt2.setDate(3, dateI);
        cstmt2.setInt(4, day);

        cstmt2.executeUpdate();

        String containerCapacity;
        String areaFacility;

        containerCapacity = cstmt2.getString(1);
        areaFacility = cstmt.getString(1);

        String[] split = containerCapacity.split(",");
        String[] split2 = areaFacility.split(",");
        containerCapacity = "";

        for (int i = 1; i < split.length; i++) {
            containerCapacity = containerCapacity + "Day" + i + ": " +
                    "\nContainer Capacity:" + split[i] + "%\n" +
                    "Facility Area capcity:" + split2[i] + "%\n";
        }

        return containerCapacity;
    }
}

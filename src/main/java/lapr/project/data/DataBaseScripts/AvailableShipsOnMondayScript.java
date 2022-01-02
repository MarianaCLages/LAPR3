package lapr.project.data.DataBaseScripts;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lapr.project.data.DatabaseConnection;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class AvailableShipsOnMondayScript {

    private DatabaseConnection databaseConnection;

    /**
     * Constructor.
     * @param db the database
     */
    public AvailableShipsOnMondayScript(DatabaseConnection db) {
        this.databaseConnection = db;
    }


    //US210

    /**
     * Gets which ships are available next Monday.
     * Note that if the current day is a Monday, it only considers the Monday in the following week.
     *
     * @return the ships that are available next Monday
     */
    public ArrayList<String> get() {
        ArrayList<String> shipList = new ArrayList<String>();

        Connection connection = databaseConnection.getConnection();
        // today's date
        LocalDateTime ld1 = LocalDateTime.now();
        // next monday
        ld1 = ld1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ld = "TO_DATE('" + ld1.format(ft) + "', 'DD/MM/YYYY')";

        String query = "select SHIP.CALLSIGN AS SHIP_CALLSIGN, " + ld + " as NEXT_MONDAY\n" +
                "from SHIP\n" +
                "         INNER JOIN TRIP ON TRIP.VEHICLEID = SHIP.VEHICLEID\n" +
                "WHERE (EXTRACT(DAY FROM ENDDATE) = EXTRACT(DAY FROM " + ld + ")\n" +
                "    AND EXTRACT(MONTH FROM ENDDATE) = EXTRACT(MONTH FROM " + ld + "))\n" +
                "UNION\n" +
                "(SELECT SHIP.CALLSIGN, " + ld + " as NEXT_MONDAY\n" +
                " FROM SHIP\n" +
                "          INNER JOIN TRIP ON TRIP.VEHICLEID = SHIP.VEHICLEID\n" +
                " WHERE (ENDDATE < " + ld + "))\n" +
                "UNION\n" +
                "(SELECT SHIP.CALLSIGN, " + ld + " as NEXT_MONDAY\n" +
                " FROM SHIP\n" +
                "          INNER JOIN TRIP ON TRIP.VEHICLEID = SHIP.VEHICLEID\n" +
                " WHERE (TRIP.STARTDATE > " + ld + "))\n";


        try (PreparedStatement getPreparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    shipList.add(resultSet.getString("SHIP_CALLSIGN"));
                }
                return shipList;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }
}

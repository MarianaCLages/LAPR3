package lapr.project.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class seaDistancesImporter {

    public static void main(String[] args) {

        try {
            importSeaDistances(new File("data-seaDistance/seadists.csv"), ConnectionFactory.getInstance().getDatabaseConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Imports the sea distances.
     *
     * @param path         the file path
     * @param dbConnection the database connection
     * @return true if it succeeds, false if it doesn't
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public static boolean importSeaDistances(File path, DatabaseConnection dbConnection) throws FileNotFoundException, SQLException {
        boolean returnValue = false;
        Connection connection = dbConnection.getConnection();
        Scanner sc = new Scanner(new File(String.valueOf(path)));
        sc.nextLine();
        do {
            String[] line = sc.nextLine().split(",");

            String sqlCommand = "Select * from Facility where FACILITYID='" + line[1] + "'";
            try (PreparedStatement getCountryCodeStatement = connection.prepareStatement(sqlCommand)) {
                try (ResultSet resultSetFacility1Code = getCountryCodeStatement.executeQuery()) {
                    if (resultSetFacility1Code.next()) {
                        String portId1 = resultSetFacility1Code.getString(1);
                        sqlCommand = "Select * from Facility where FACILITYID='" + line[4] + "'";
                        try (PreparedStatement getCountryCode2Statement = connection.prepareStatement(sqlCommand)) {
                            try (ResultSet resultSetFacility2Code = getCountryCode2Statement.executeQuery()) {
                                if (resultSetFacility2Code.next()) {

                                    String portId2 = resultSetFacility2Code.getString(1);

                                    sqlCommand = "INSERT INTO SEADISTANCE (FIRSTPORTID, SECONDPORTID, SEADISTANCE)\n" +
                                            "VALUES ('" + portId1 + "', '" + portId2 + "', " + line[6] + ")";
                                    try (PreparedStatement insertStatement = connection.prepareCall(sqlCommand)) {
                                        insertStatement.executeUpdate();
                                    }

                                }
                            }
                        }
                    }
                }
            }
        } while (sc.hasNextLine());
        sc.close();

        return returnValue;
    }
}

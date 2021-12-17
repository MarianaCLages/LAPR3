package lapr.project.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class seaDistancesImporter {

    public static void main(String[] args) {

        try {
            importSeaDistances(new File("data-seaDistance/seadists.csv"), ConnectionFactory.getInstance().getDatabaseConnection());
        } catch (Exception e) {

        }


    }

    public static boolean importSeaDistances(File path, DatabaseConnection dbConnection) throws FileNotFoundException, SQLException {
        boolean returnValue = false;
        Connection connection = dbConnection.getConnection();
        Scanner sc = new Scanner(new File(String.valueOf(path)));
        sc.nextLine();

        do {
            String[] line = sc.nextLine().split(",");

            char stringToCharPort1[] = line[0].toCharArray();

            String alpha2CodePort1 = Character.toString(stringToCharPort1[0]) + Character.toString(stringToCharPort1[1]);
            String alpha3CodePort1 = alpha2CodePort1 + Character.toString(stringToCharPort1[2]);

            alpha2CodePort1 = alpha2CodePort1.toUpperCase(Locale.ROOT);
            alpha3CodePort1 = alpha3CodePort1.toUpperCase(Locale.ROOT);


            char stringToCharPort2[] = line[5].toCharArray();

            String alpha2CodePort2 = Character.toString(stringToCharPort2[0]) + Character.toString(stringToCharPort2[1]);
            String alpha3CodePort2 = alpha2CodePort2 + Character.toString(stringToCharPort2[2]);

            alpha2CodePort2 = alpha2CodePort2.toUpperCase(Locale.ROOT);
            alpha3CodePort2 = alpha3CodePort2.toUpperCase(Locale.ROOT);

            int port1ID = 0;
            int port2ID = 0;

            String sqlCommand = "select * from FACILITY where ALPHA3CODE = '" + alpha3CodePort1 + "' and ALPHA2CODE = '" + alpha2CodePort1 + "' ";

            try (PreparedStatement getCountryPreparedStatement = connection.prepareStatement(sqlCommand)) {
                try (ResultSet resultSetCountry = getCountryPreparedStatement.executeQuery()) {

                    if (resultSetCountry.next()) {


                        port1ID = resultSetCountry.getInt("FACILITYID");

                    }

                }
            }

            sqlCommand = "select * from FACILITY where ALPHA3CODE = '" + alpha3CodePort2 + "' and ALPHA2CODE = '" + alpha2CodePort2 + "' ";

            try (PreparedStatement getCountryPreparedStatement = connection.prepareStatement(sqlCommand)) {
                try (ResultSet resultSetCountry = getCountryPreparedStatement.executeQuery()) {

                    if (resultSetCountry.next()) {


                        port2ID = resultSetCountry.getInt("FACILITYID");

                    }

                }
            }


            sqlCommand = "insert into SeaDistance (firstPortID, secondPortID, seaDistance) values ('" + port1ID + "','" + port2ID + "','" + line[6] + "')";

            try (PreparedStatement geContainerPreparedStatement = connection.prepareStatement(sqlCommand)) {
                geContainerPreparedStatement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }


        } while (sc.hasNextLine());
        sc.close();

        return returnValue;
    }


}

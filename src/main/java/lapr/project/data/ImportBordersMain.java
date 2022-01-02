package lapr.project.data;

import lapr.project.controller.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class ImportBordersMain {

    public static void main(String[] args) throws FileNotFoundException {
        Connection connection = App.getInstance().getDatabaseConnection().getConnection();


        Scanner sc = new Scanner(new File("data/borders.csv"));

        sc.nextLine();
        String alpha2code1;
        String alpha3code1;
        String alpha2code2;
        String alpha3code2;
        int i = 0;
        do {
            i++;

            String[] line = sc.nextLine().split(", ");
            String sqlQuery = "Select ALPHA2CODE, ALPHA3CODE from COUNTRY where COUNTRY.NAME = '" + line[0] + "'";
            try (PreparedStatement getCountryPreparedStatement = connection.prepareStatement(sqlQuery)) {
                try (ResultSet countryResultSet = getCountryPreparedStatement.executeQuery()) {
                    if (countryResultSet.next()) {
                        alpha2code1 = countryResultSet.getString(1);
                        alpha3code1 = countryResultSet.getString(2);

                        sqlQuery = "Select ALPHA2CODE, ALPHA3CODE from COUNTRY where COUNTRY.NAME = '" + line[1] + "'";
                        try (PreparedStatement getCountry2PreparedStatement = connection.prepareStatement(sqlQuery)) {
                            try (ResultSet country2ResultSet = getCountry2PreparedStatement.executeQuery()) {
                                if (country2ResultSet.next()) {
                                    alpha2code2 = country2ResultSet.getString(1);
                                    alpha3code2 = country2ResultSet.getString(2);

                                    sqlQuery = "INSERT INTO BORDER(FIRSTCOUNTRYALPHA2CODE, FIRSTCOUNTRYALPHA3CODE, SECONDCOUNTRYALPHA2CODE, SECONDCOUNTRYALPHA3CODE)\n" +
                                            "values ('" + alpha2code1 + "', '" + alpha3code1 + "', '" + alpha2code2 + "', '" + alpha3code2 + "')";

                                    try (PreparedStatement setBorderPreparedStatement = connection.prepareStatement(sqlQuery)) {
                                        setBorderPreparedStatement.executeUpdate();

                                    }
                                }


                            }
                        }
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (sc.hasNextLine());
        sc.close();
    }
}

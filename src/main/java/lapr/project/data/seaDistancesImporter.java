package lapr.project.data;

import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
import lapr.project.model.stores.PortStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class seaDistancesImporter {

    public static void main(String[] args) {




    }


    public static boolean importSeaDistances(File path, DatabaseConnection dbConnection) throws FileNotFoundException {
        boolean returnValue = false;
        Connection connection = dbConnection.getConnection();
        Scanner sc = new Scanner(new File(String.valueOf(path)));
        sc.nextLine();
        do {
            String[] line = sc.nextLine().split(",");

            String sqlCommand = "insert into SeaDistance (firstPortID, secondPortID, seaDistance) values ('" + line[1] + "','" + line[5] + "','" + line[6] + "')";

            try (PreparedStatement geContainerPreparedStatement = connection.prepareStatement(sqlCommand)) {
                try (ResultSet addressesResultSet = geContainerPreparedStatement.executeQuery()) {


                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

            //  portStoreData.save(dbConnection, port);

        } while (sc.hasNextLine());
        sc.close();

        return returnValue;
    }


}

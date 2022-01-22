package lapr.project.data.DataBaseScripts;

import lapr.project.controller.App;
import lapr.project.controller.generateNextWeekManifestsController;
import lapr.project.data.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class generateNextWeekManifestsScript {

    /**
     * Constructor.
     */
    public generateNextWeekManifestsScript(){
        //Empty constructor
    }

    //US407

    /*public List<String> getLoadingCargoManifestsFromDatabase(DatabaseConnection databaseConnection) throws SQLException {
        String sqlCommand = " SCRIPT!!!! ";

        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                for (int i = 0; i < j; i++) {
                    resultSet.next();
                }

                if (resultSet.next()) {
                    //return resultSet.getInt("CARGOMANIFESTID");

                }// else return 0;
            }
        }
        return null;
    }*/
}

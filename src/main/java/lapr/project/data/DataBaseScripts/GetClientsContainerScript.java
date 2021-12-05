package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetClientsContainerScript {

    private DatabaseConnection databaseConnection;


    public GetClientsContainerScript(DatabaseConnection db) {
        this.databaseConnection = db;

    }

    //US204

    public ArrayList<String> getClientContainers(String clientID) {
        ArrayList<String> rlcontainerID = new ArrayList<>();

        Connection connection = databaseConnection.getConnection();


        String query = " SELECT CONTAINERID as CLIENTID \n" +
                " FROM CONTAINERCLIENT \n" +
                " WHERE (CLIENTID = " + clientID + ") \n";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    rlcontainerID.add(resultSet.getString("CLIENTID"));
                } else {
                    return rlcontainerID;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }
}

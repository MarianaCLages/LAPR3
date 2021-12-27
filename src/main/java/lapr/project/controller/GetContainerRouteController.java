package lapr.project.controller;

import lapr.project.data.DataBaseScripts.GetContainerRouteScript;
import lapr.project.data.DatabaseConnection;

import java.sql.SQLException;

public class GetContainerRouteController {

    private final DatabaseConnection databaseConnection;

    public GetContainerRouteController() {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    public String getContainerRoute(int containerID, int clientID) throws SQLException {

        String clientIDString = Integer.toString(clientID);
        String containerIDString = Integer.toString(containerID);

        return GetContainerRouteScript.callFunction(containerIDString, clientIDString, databaseConnection);

    }
}

package lapr.project.controller;

import lapr.project.data.DataBaseScripts.GetContainerRouteScript;
import lapr.project.data.DatabaseConnection;

import java.sql.SQLException;

public class GetContainerRouteController {

    private final DatabaseConnection databaseConnection;

    /**
     * Constructor.
     */
    public GetContainerRouteController() {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the container route.
     *
     * @param containerID the container ID
     * @param clientID    the client ID
     * @return the container route
     * @throws SQLException
     * @throws NullPointerException
     */
    public String getContainerRoute(int containerID, int clientID) throws SQLException, NullPointerException {
        String clientIDString = Integer.toString(clientID);
        String containerIDString = Integer.toString(containerID);

        return GetContainerRouteScript.callFunction(containerIDString, clientIDString, databaseConnection);
    }
}

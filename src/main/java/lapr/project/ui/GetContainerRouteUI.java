package lapr.project.ui;

import lapr.project.controller.GetContainerRouteController;

import java.sql.SQLException;

public class GetContainerRouteUI implements Runnable {

    private final GetContainerRouteController getContainerRouteController;

    public GetContainerRouteUI() {
        this.getContainerRouteController = new GetContainerRouteController();
    }

    @Override
    public void run() {

        int containerID;
        int clientID;

        do {
            try {
                clientID = Utils.readIntegerFromConsole("Enter the client reference ID:");
                if (clientID < 0) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid option! (There is no negative client ID!)");
                clientID = -1;
            }
        } while (clientID == -1);

        do {
            try {
                containerID = Utils.readIntegerFromConsole("Enter the container ID:");
                if (containerID < 0) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid option! (There is no negative container ID!)");
                containerID = -1;
            }
        } while (containerID == -1);

        try {
            System.out.println(getContainerRouteController.getContainerRoute(containerID, clientID));
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
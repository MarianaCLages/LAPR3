package lapr.project.ui;

import lapr.project.controller.GetAuditTrailController;

import java.sql.SQLException;

public class GetAuditTrailUI implements Runnable {

    public GetAuditTrailUI() {
        //Empty
    }

    private final GetAuditTrailController getContainerRouteController = new GetAuditTrailController();

    @Override
    public void run() {

        try {
            System.out.println(getContainerRouteController.getAuditTrail());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
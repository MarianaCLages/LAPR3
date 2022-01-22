package lapr.project.ui;

import lapr.project.controller.CheckForCargoManifestMapController;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;


public class CheckForCargoManifestMapUI implements Runnable {

    private final CheckForCargoManifestMapController ctrl;

    public CheckForCargoManifestMapUI(){
        this.ctrl = new CheckForCargoManifestMapController();
    }

    @Override
    public void run() {
        java.sql.Date rDate = Date.valueOf(LocalDate.now());
        String rPortID = Utils.readLineFromConsole("Please enter the facility ID: ");

        try {
            String result = ctrl.callFunction(rPortID,rDate);

            System.out.println(result);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

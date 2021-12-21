package lapr.project.ui;

import lapr.project.controller.FacilityResourcesController;

import java.io.IOException;
import java.sql.SQLException;

public class FacilityResourcesUI implements Runnable{


    public void run(){

        FacilityResourcesController facilityResourcesController = new FacilityResourcesController();
        int id;
        int month;
        int year;

        id = Utils.readIntegerFromConsole("Facility ID?");
        month = Utils.readIntegerFromConsole("Month?");
        year = Utils.readIntegerFromConsole("Year?");



        try {
            String s = facilityResourcesController.getFacilityResources(month,year,id);

            if(s != null){
                System.out.println("\nOperation made successfully!");
                System.out.println(s);
            }
            else{
                System.out.println("There is not facility with that Id!");
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }


    }
}

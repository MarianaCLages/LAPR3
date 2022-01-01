package lapr.project.ui;

import lapr.project.controller.GraphNClosestePlacesController;

public class GraphNClosestPlacesUI implements Runnable {


    @Override
    public void run() {

        GraphNClosestePlacesController graphNClosestePlacesController = new GraphNClosestePlacesController();

        int n = Utils.readIntegerFromConsole("N Closest places?");

        try{
            System.out.println(graphNClosestePlacesController.graphNClosestPlacesController(n));
        }catch (Exception e){
            System.out.println("Something went wrong please try again!");
        }


    }
}

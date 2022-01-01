package lapr.project.ui;

import lapr.project.controller.GraphNCClosestPlacesController;
import lapr.project.shared.exceptions.NoPathFoundForSpecificVertexException;

public class GraphNClosestPlacesUI implements Runnable {


    @Override
    public void run() {

        GraphNCClosestPlacesController graphNCClosestPlacesController = new GraphNCClosestPlacesController();

        int numberOfNPlaces;

        do {
            try {
                numberOfNPlaces = Utils.readIntegerFromConsole("Please type the desired : 'N' for all the closest places per place (city/port)");
                if (numberOfNPlaces < 0)
                    throw new NumberFormatException("Please enter a valid number! (No negative numbers! It must be > 0)");
            } catch (NumberFormatException ex1) {
                System.out.println(ex1.getMessage());
                numberOfNPlaces = -1;
            } catch (IllegalArgumentException ex2) {
                System.out.println("Please enter a number! (Don't enter a letter nor symbol!)");
                numberOfNPlaces = -1;
            }

        } while (numberOfNPlaces == -1);


        try {
            System.out.println(graphNCClosestPlacesController.graphNClosestPlacesController(numberOfNPlaces));
        } catch (NoPathFoundForSpecificVertexException ex1) {
            System.out.println(ex1.getMessage());
        } catch (Exception ex2) {
            System.out.println("Something went wrong please try again!");
        }

    }
}

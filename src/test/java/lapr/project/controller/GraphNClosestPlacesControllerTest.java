package lapr.project.controller;

import lapr.project.shared.exceptions.NoPathFoundForSpecificVertexException;
import org.junit.jupiter.api.Test;

class GraphNClosestPlacesControllerTest {


    @Test
    void graphNClosestPlacesController() {

        GraphNClosestPlacesController graphNCClosestPlacesController = new GraphNClosestPlacesController();


        try {
            String actual = graphNCClosestPlacesController.getTheNClosestPlaces(2);

            //  if (actual == null || actual.equals("")) fail();


        } catch (NoPathFoundForSpecificVertexException e) {
            System.out.println("NANI");
        }

    }

}

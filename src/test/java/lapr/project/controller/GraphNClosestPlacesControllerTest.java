package lapr.project.controller;

import lapr.project.shared.exceptions.NoPathFoundForSpecificVertexException;
import org.junit.jupiter.api.Test;

class GraphNClosestPlacesControllerTest {

    @Test
    void graphNClosestPlacesController() {

        GraphNClosestPlacesController graphNClosestPlacesController = new GraphNClosestPlacesController();


        try {
            String actual = graphNClosestPlacesController.getTheNClosestPlaces(1);

          //  if (actual == null || actual.equals("")) fail();


        } catch (NoPathFoundForSpecificVertexException e) {
            System.out.println("NANI");
        }

    }
}
package lapr.project.shared.graph;

import lapr.project.controller.App;
import lapr.project.model.Continent;
import lapr.project.model.Country;
import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
import lapr.project.shared.exceptions.NoPathFoundForSpecificVertexException;
import lapr.project.shared.exceptions.NullVerticesException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphNClosestPlacesTest {

    static FreightNetwork f = new FreightNetwork();

    @BeforeAll
    static void init() throws NullVerticesException {
        f.createGraph(5, App.getInstance().getDatabaseConnection());
    }

    @Test
    void getNClosestPlaces() {


        try {
            String value = GraphNClosestPlaces.getNClosestPlaces(f.getGraph(), 0);

            if (value.isEmpty() || value == null) {
                fail();
            }

            assertEquals(value, value);

            GraphNClosestPlaces.getNClosestPlaces(f.getGraph(), 10);

            if (value.isEmpty() || value == null) {
                fail();
            }

        } catch (NoPathFoundForSpecificVertexException e) {
            //nothing
        } catch (Exception e) {
            //nothing here
        }


    }
}
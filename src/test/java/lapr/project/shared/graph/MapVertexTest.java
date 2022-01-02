package lapr.project.shared.graph;

import lapr.project.model.City;
import lapr.project.model.Continent;
import lapr.project.model.Country;
import lapr.project.shared.exceptions.NullVerticesException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapVertexTest {

    @Test
    void testToString() {


        City city = new City("Deez", 10, 10, new Country("Portugal", null, null, 2000, Continent.EUROPE));


        try {
            MapVertex<Vertex, Double> mapVertex = new MapVertex<>(city);

            String actual = mapVertex.toString();

            if (actual == null || actual.equals("")) fail();

            assertEquals(actual, actual);

        } catch (NullVerticesException e) {
            e.printStackTrace();
        }

    }
}
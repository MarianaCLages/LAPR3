package lapr.project.shared.graph;

import lapr.project.model.City;
import lapr.project.model.Continent;
import lapr.project.model.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void testEdge() {


        City c1 = new City("Deez", 10, 10, new Country("Portugal", null, null, 2000, Continent.EUROPE));
        City c2 = new City("Deez", 10, 10, new Country("Portugal", null, null, 2000, Continent.EUROPE));


        Edge<Vertex, Double> edge = new Edge(c1, c2, 10);

        edge.setWeight(new Double(20));

        int hash = edge.hashCode();

        if(hash == 0) fail();

    }

}
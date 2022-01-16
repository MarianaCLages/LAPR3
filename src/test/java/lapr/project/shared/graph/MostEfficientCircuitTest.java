package lapr.project.shared.graph;

import lapr.project.model.City;
import lapr.project.model.Continent;
import lapr.project.model.Country;
import lapr.project.shared.exceptions.NullVerticesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;

public class MostEfficientCircuitTest {


    Graph<Vertex, Double> graph = new MapGraph<>(false);

    @BeforeEach
    void setUp() throws NullVerticesException {

        graph.addEdge(new City("Lisboa", 10, 40, new Country("Portugal", null, null, 2000, Continent.EUROPE)), new City("Madrid", 12, 36, new Country("Espanha", null, null, 7000, Continent.EUROPE)), 0.0);

    }


    @Test
    void efficientCircuit(){

        LinkedList<Vertex> path = new LinkedList<>();
        MostEfficientCircuit mostEfficientCircuit = new MostEfficientCircuit();
        Vertex vertex = null;

        for(Vertex v : graph.vertices()){
            if(v.getDesignation().equals("Lisboa"))
                vertex = v;
        }

        LinkedList<Vertex> ls = mostEfficientCircuit.efficientCircuit(graph,vertex,path);

        assertEquals(ls.toString(),"[City{name='Lisboa', latitude=10.0, longitude=40.0, country=Portugal}]");
    }

}

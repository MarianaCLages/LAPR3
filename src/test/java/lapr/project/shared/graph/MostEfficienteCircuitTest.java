package lapr.project.shared.graph;

import lapr.project.controller.App;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.NullVerticesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class MostEfficienteCircuitTest {

    FreightNetwork freightNetwork = new FreightNetwork();
    DatabaseConnection databaseConnection;

    @BeforeEach
    void freightNetwork() throws NullVerticesException {
        databaseConnection = App.getInstance().getDatabaseConnection();
        freightNetwork.createGraph(3,databaseConnection);

    }

    @Test
    void mostEfficientCircuit(){

        MostEfficientCircuit mostEfficientCircuit = new MostEfficientCircuit();
        Graph<Vertex, Double> graph = freightNetwork.getGraph();
        LinkedList<Vertex> ls;
        Vertex vertice = null;
        LinkedList<Vertex> path = new LinkedList<>();


        for (Vertex v : graph.vertices()) {
            if (v.getDesignation().equals("London"))
                vertice = v;
        }


        ls = mostEfficientCircuit.efficientCircuit(graph, vertice, path);
        String s = "[Facility{identification='29239', name='London', continent='Europe', country='United Kingdom', location=latitude = 51.5, longitude = -0.066666667}, Facility{identification='29002', name='Liverpool', continent='Europe', country='United Kingdom', location=latitude = 53.46666667, longitude = -3.033333333}, Facility{identification='29749', name='Dublin', continent='Europe', country='Ireland', location=latitude = 53.35, longitude = -6.266666667}, City{name='Dublin', latitude=53.31666667, longitude=-6.233333, country=Ireland}, City{name='London', latitude=51.5, longitude=-0.083333, country=United Kingdom}, Facility{identification='29239', name='London', continent='Europe', country='United Kingdom', location=latitude = 51.5, longitude = -0.066666667}]";


        assertEquals(s,ls.toString());
    }
}

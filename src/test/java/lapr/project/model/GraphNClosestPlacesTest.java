package lapr.project.model;

import lapr.project.shared.exceptions.NoPathFoundForSpecificVertexException;
import lapr.project.shared.exceptions.NullVerticesException;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.GraphNClosestPlaces;
import lapr.project.shared.graph.MapGraph;
import lapr.project.shared.graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphNClosestPlacesTest {


    Graph<Vertex, Double> graph = new MapGraph<>(false);
    Graph<Vertex, Double> graph2 = new MapGraph<>(false);
    Graph<Vertex, Double> graph3 = new MapGraph<>(false);
    Graph<Vertex, Double> graph4 = new MapGraph<>(false);
    Graph<Vertex, Double> graph5 = null;

    @BeforeEach
    public void setUp() throws NullVerticesException {

        graph.addEdge(new City("Lisboa", 10, 40, new Country("Portugal", null, null, 2000, Continent.EUROPE)), new City("Madrid", 12, 36, new Country("Espanha", null, null, 7000, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Luanda", -8, 13, new Country("Angola", null, null, 5500, Continent.AFRICA)), new City("Bailundo", -12, 15, new Country("Angola", null, null, 8500, Continent.AFRICA)), 0.0);

        graph3.addEdge(new City("Madrid", 12, 23, new Country("Espanha", null, null, 7000, Continent.EUROPE)), new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Bruxelas", 22, 28, new Country("Bélgica", null, null, 5500, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Luxemburgo", 30, 47, new Country("Luxemburgo", null, null, 8500, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Bruxelas", 22, 28, new Country("Bélgica", null, null, 5500, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Luxemburgo", 30, 47, new Country("Luxemburgo", null, null, 8500, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Berna", 26.5, 50, new Country("Suíça", null, null, 6800, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Varsóvia", 34, 41, new Country("Polónia", null, null, 3000, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Bruxelas", 22, 28, new Country("Bélgica", null, null, 5500, Continent.EUROPE)), new City("Luxemburgo", 30, 47, new Country("Luxemburgo", null, null, 8500, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Berna", 26.5, 50, new Country("Suíça", null, null, 6800, Continent.EUROPE)), new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)), 0.0);
        graph3.addEdge(new City("Pretória", -25, 28, new Country("África do Sul", null, null, 5500, Continent.AFRICA)), new City("Durban", 29, 31, new Country("África do Sul", null, null, 8500, Continent.AFRICA)), 0.0);

        graph4.addEdge(new City("Pretória", -25, 28, new Country("África do Sul", null, null, 5500, Continent.AFRICA)), new City("Durban", 29, 31, new Country("África do Sul", null, null, 8500, Continent.AFRICA)), 0.0);
        graph4.addEdge(new City("Madrid", 12, 23, new Country("Espanha", null, null, 7000, Continent.EUROPE)), new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), 0.0);
        graph4.addEdge(new City("Tokyo", 36, 138, new Country("Japan", null, null, 7000, Continent.ASIA)), new City("Pequim", 40, 116, new Country("China", null, null, 6500, Continent.ASIA)), 0.0);
        graph4.addEdge(new City("Brasilia", -16, -47, new Country("Brasil", null, null, 7000, Continent.AMERICA)), new City("Rio de Janeiro", -23, -43, new Country("Brasil", null, null, 6500, Continent.ASIA)), 0.0);
        graph4.addEdge(new City("Canberra", -35, 149, new Country("Australia", null, null, 7000, Continent.OCEANIA)), new City("Queensland", -28, 153, new Country("Australia", null, null, 6500, Continent.OCEANIA)), 0.0);


    }

    @Test
    void GraphNClosestPlaces() {


        try {
            assertEquals(GraphNClosestPlaces.getNClosestPlaces(graph, 0), "No values returned!");


            String expected = "\n" +
                    "Origin Vertex: Lisboa , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Madrid , which is a : City\n" +
                    "\t\t\tLisboa which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Luanda , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Bailundo , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!";

            String expected3 = "\n" +
                    "Origin Vertex: Madrid , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Berlim , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Bruxelas , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Luxemburgo , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Roma , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berlim , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Bruxelas , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berlim , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Luxemburgo , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berlim , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Berna , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berlim , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Varsóvia , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Bruxelas , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Luxemburgo , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berna , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Roma , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Pretória , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Durban , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!";

            String expected4 = "\n" +
                    "Origin Vertex: Madrid , which is a : City\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tBerlim which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berlim , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tBruxelas which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Bruxelas , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tLuxemburgo which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Luxemburgo , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tRoma which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Roma , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berlim , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tBruxelas which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Bruxelas , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tBerlim which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berlim , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tLuxemburgo which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Luxemburgo , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tBerlim which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berlim , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Berna , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tBerlim which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berlim , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Varsóvia , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tBerlim which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Bruxelas , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tLuxemburgo which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Luxemburgo , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tBruxelas which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Berna , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tRoma which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Roma , which is a : City\n" +
                    "\t\t\tMadrid which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tParis which is a : City , with a distance of: 0.0\n" +
                    "\t\t\tBerlim which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Pretória , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Durban , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!";

            String expected5 = "\n" +
                    "Origin Vertex: Pretória , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Durban , which is a : City\n" +
                    "\t\t\tPretória which is a : City , with a distance of: 0.0\n" +
                    "\n" +
                    "Origin Vertex: Madrid , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Paris , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Tokyo , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Pequim , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Brasilia , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Rio de Janeiro , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Canberra , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!\n" +
                    "Origin Vertex: Queensland , which is a : City\n" +
                    "\t\t\tThere are no connections for this place!";



            String expected2 = "";

            assertEquals(expected, GraphNClosestPlaces.getNClosestPlaces(graph, 1));
            assertEquals(expected2, GraphNClosestPlaces.getNClosestPlaces(graph2, 2));
            assertEquals(expected3,GraphNClosestPlaces.getNClosestPlaces(graph3,1));
            assertEquals(expected4,GraphNClosestPlaces.getNClosestPlaces(graph3,10));
            assertEquals(expected5,GraphNClosestPlaces.getNClosestPlaces(graph4,1));

            assertEquals(expected2,GraphNClosestPlaces.getNClosestPlaces(graph5,1));

        } catch (NoPathFoundForSpecificVertexException e) {
            //If the method enters this catch that means the last assert throws the "NoPathFoundForSpecificVertexException" exception which makes sense since the graph 5 is null!
        }


    }

}

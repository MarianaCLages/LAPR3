package lapr.project.model;

import lapr.project.shared.exceptions.NullVerticesException;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.MapGraph;
import lapr.project.shared.graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphNClosestPlacesTest {


    Graph<Vertex, Double> graph = new MapGraph<>(false);

    @BeforeEach
    public void setUp() throws NullVerticesException{

        graph.addEdge(new City("Lisboa", 10, 40, new Country("Portugal", null, null, 2000, Continent.EUROPE)), new City("Madrid", 12, 36, new Country("Espanha", null, null, 7000, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Madrid", 12, 23, new Country("Espanha", null, null, 7000, Continent.EUROPE)), new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Bruxelas", 22, 28, new Country("Bélgica", null, null, 5500, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Luxemburgo", 30, 47, new Country("Luxemburgo", null, null, 8500, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Bruxelas", 22, 28, new Country("Bélgica", null, null, 5500, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Luxemburgo", 30, 47, new Country("Luxemburgo", null, null, 8500, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Berna", 26.5, 50, new Country("Suíça", null, null, 6800, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Varsóvia", 34, 41, new Country("Polónia", null, null, 3000, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Bruxelas", 22, 28, new Country("Bélgica", null, null, 5500, Continent.EUROPE)), new City("Luxemburgo", 30, 47, new Country("Luxemburgo", null, null, 8500, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Berna", 26.5, 50, new Country("Suíça", null, null, 6800, Continent.EUROPE)), new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)), 0.0);
        //graph.addEdge(new City("Pretória", -25, 28, new Country("África do Sul", null, null, 5500, Continent.AFRICA)), new City("Durban", 29, 31, new Country("África do Sul", null, null, 8500, Continent.AFRICA)), 0.0);
        graph.addEdge(new City("Luanda", -8, 13, new Country("Angola", null, null, 5500, Continent.AFRICA)), new City("Bailundo", -12, 15, new Country("Angola", null, null, 8500, Continent.AFRICA)), 0.0);

    }

    @Test
    void GraphNClosestPlaces(){


        assertEquals(GraphNClosestPlaces.getTheNPlacesClosest(graph,0),"No values returned!");

        //System.out.println(GraphNClosestPlaces.getTheNPlacesClosest(graph,1));

        String expected = "The 1 closest places per continent are :\n" +
                "EUROPE :\n" +
                "City{name='Madrid', latitude=12.0, longitude=36.0, country=Espanha}n closest city/port:\n" +
                "[][City{name='Bailundo', latitude=-12.0, longitude=15.0, country=Angola}]\n" +
                "AFRICA :\n" +
                "City{name='Luanda', latitude=-8.0, longitude=13.0, country=Angola}n closest city/port:\n" +
                "[][City{name='Lisboa', latitude=10.0, longitude=40.0, country=Portugal}]\n" +
                "AMERICA :\n" +
                "\n" +
                "OCEANIA :\n" +
                "\n" +
                "ANTARCTICA :\n" +
                "\n" +
                "ASIA :\n" +
                "\n";

        assertEquals(expected,GraphNClosestPlaces.getTheNPlacesClosest(graph,1));
    }
}

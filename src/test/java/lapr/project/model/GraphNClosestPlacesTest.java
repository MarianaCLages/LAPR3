package lapr.project.model;

import lapr.project.shared.exceptions.NullVerticesException;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.MapGraph;
import lapr.project.shared.graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class GraphNClosestPlacesTest {


    Graph<Vertex, Double> graph = new MapGraph<>(false);
    GraphNClosestPlaces graphNClosestPlaces = new GraphNClosestPlaces();

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


        assertEquals(GraphNClosestPlaces.getNClosestPlaces(graph,0),"No values returned!");

        //System.out.println(GraphNClosestPlaces.getTheNPlacesClosest(graph,1));

        String expected = "Origin Vertex: Lisboa , which is a : City\n" +
                "Origin Vertex: Madrid , which is a : City\n" +
                "             Lisboa which is a : City , with a distance of: 0.0\n" +
                "Origin Vertex: Luanda , which is a : City\n" +
                "Origin Vertex: Bailundo , which is a : City\n";

        assertEquals(expected,GraphNClosestPlaces.getNClosestPlaces(graph,1));
    }

    @Test
    void swap(){

        ArrayList<LinkedList<Vertex>> arrayList = new ArrayList<>();
        ArrayList<LinkedList<Vertex>> arrayListExpected = new ArrayList<>();

        LinkedList<Vertex> v1 = new LinkedList<>();
        LinkedList<Vertex> v2 = new LinkedList<>();

        ArrayList<Double> ad = new ArrayList<>();
        ArrayList<Double> adExpected = new ArrayList<>();
        adExpected.add(2.2);
        adExpected.add(1.2);
        ad.add(1.2);
        ad.add(2.2);

        v1.add(0,new City("Madrid", 12, 36, new Country("Espanha", null, null, 7000, Continent.EUROPE)));
        v1.add(1,new City("Bailundo", -12, 15, new Country("Angola", null, null, 8500, Continent.AFRICA)));
        v1.add(2,new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)));

        v2.add(0, new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)));
        v2.add(1,new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)));

        arrayList.add(v1);
        arrayList.add(v2);
        arrayListExpected.add(v2);
        arrayListExpected.add(v1);



        graphNClosestPlaces.swap(ad,0,1,arrayList);


        assertEquals(adExpected,ad);
        assertEquals(arrayListExpected,arrayList);

    }

    @Test
    void partitionTest(){

        ArrayList<LinkedList<Vertex>> arrayList = new ArrayList<>();
        ArrayList<LinkedList<Vertex>> arrayListExpected = new ArrayList<>();

        LinkedList<Vertex> v1 = new LinkedList<>();
        LinkedList<Vertex> v2 = new LinkedList<>();

        ArrayList<Double> ad = new ArrayList<>();
        ArrayList<Double> adExpected = new ArrayList<>();
        adExpected.add(2.2);
        adExpected.add(1.2);
        ad.add(1.2);
        ad.add(2.2);

        v1.add(0,new City("Madrid", 12, 36, new Country("Espanha", null, null, 7000, Continent.EUROPE)));
        v1.add(1,new City("Bailundo", -12, 15, new Country("Angola", null, null, 8500, Continent.AFRICA)));
        v1.add(2,new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)));

        v2.add(0, new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)));
        v2.add(1,new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)));

        arrayList.add(v1);
        arrayList.add(v2);
        arrayListExpected.add(v2);
        arrayListExpected.add(v1);



        graphNClosestPlaces.partition(ad,0,1,1,arrayList);

        assertEquals(arrayListExpected,arrayList);
        assertEquals(adExpected,ad);

    }

    @Test
    void quickSort(){


        ArrayList<LinkedList<Vertex>> arrayList = new ArrayList<>();
        ArrayList<LinkedList<Vertex>> arrayListExpected = new ArrayList<>();

        LinkedList<Vertex> v1 = new LinkedList<>();
        LinkedList<Vertex> v2 = new LinkedList<>();

        ArrayList<Double> ad = new ArrayList<>();
        ArrayList<Double> adExpected = new ArrayList<>();
        adExpected.add(2.2);
        adExpected.add(3.2);
        ad.add(3.2);
        ad.add(2.2);

        v1.add(0,new City("Madrid", 12, 36, new Country("Espanha", null, null, 7000, Continent.EUROPE)));
        v1.add(1,new City("Bailundo", -12, 15, new Country("Angola", null, null, 8500, Continent.AFRICA)));
        v1.add(2,new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)));

        v2.add(0, new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)));
        v2.add(1,new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)));

        arrayList.add(v1);
        arrayList.add(v2);
        arrayListExpected.add(v2);
        arrayListExpected.add(v1);


        graphNClosestPlaces.quickSort(ad,arrayList);

        assertEquals(arrayListExpected,arrayList);
        assertEquals(adExpected,ad);
    }
}

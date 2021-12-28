/*package lapr.project.controller;

import lapr.project.model.City;
import lapr.project.model.Continent;
import lapr.project.model.Country;
import lapr.project.shared.graph.ColourGraph;
import lapr.project.shared.graph.MatrixGraph;
import lapr.project.shared.graph.Vertex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColourGraphControllerTest {

    @Test
    void colourGraph() {
        //Arrange
        ColourGraph colourGraph = new ColourGraph();
        MatrixGraph<Vertex, Double> graph = new MatrixGraph<>(true);
        graph.addEdge(new City("Lisboa", 10, 40, new Country("Portugal", null, null, 2000, Continent.EUROPE)), new City("Madrid", 12, 36, new Country("Espanha", null, null, 7000, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Madrid", 12, 23, new Country("Espanha", null, null, 7000, Continent.EUROPE)), new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Bruxelas", 22, 28, new Country("Bélgica", null, null, 5500, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Luxemburgo", 30, 47, new Country("Luxemburgo", null, null, 8500, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Berna", 26.5, 50, new Country("Suíça", null, null, 6800, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Paris", 14, 40, new Country("França", null, null, 6500, Continent.EUROPE)), new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Bruxelas", 22, 28, new Country("Bélgica", null, null, 5500, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Luxemburgo", 30, 47, new Country("Luxemburgo", null, null, 8500, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Berna", 26.5, 50, new Country("Suíça", null, null, 6800, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Berlim", 20, 45, new Country("Alemanha", null, null, 8000, Continent.EUROPE)), new City("Varsóvia", 34, 41, new Country("Polónia", null, null, 3000, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Bruxelas", 22, 28, new Country("Bélgica", null, null, 5500, Continent.EUROPE)), new City("Luxemburgo", 30, 47, new Country("Luxemburgo", null, null, 8500, Continent.EUROPE)), 0.0);
        graph.addEdge(new City("Berna", 26.5, 50, new Country("Suíça", null, null, 6800, Continent.EUROPE)), new City("Roma", 28, 30, new Country("Itália", null, null, 7500, Continent.EUROPE)), 0.0);

        //Act
        String actual = colourGraph.setColours(graph);
        String expected = "City: Lisboa, Colour: 0\n" +
                "City: Madrid, Colour: 1\n" +
                "City: Paris, Colour: 0\n" +
                "City: Berlim, Colour: 1\n" +
                "City: Bruxelas, Colour: 2\n" +
                "City: Luxemburgo, Colour: 3\n" +
                "City: Berna, Colour: 2\n" +
                "City: Roma, Colour: 1\n" +
                "City: Varsóvia, Colour: 0";

        //Assert
        assertEquals(expected, actual);
    }
}
 */
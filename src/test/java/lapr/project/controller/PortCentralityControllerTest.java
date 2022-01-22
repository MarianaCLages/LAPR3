package lapr.project.controller;

import lapr.project.model.City;
import lapr.project.model.Continent;
import lapr.project.model.Country;
import lapr.project.shared.graph.MatrixGraph;
import lapr.project.shared.graph.Vertex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortCentralityControllerTest {

    static MatrixGraph<Vertex, Double> graph = new MatrixGraph<>(false);

    @BeforeAll
    static void beforeAll() {

        //Cities
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
    }

    @Test
    void portCentralityControllerN5() {

        PortCentralityController controller = new PortCentralityController();

        try {
            //Arrange
            String actual = controller.getCriticalPorts(graph, 5);

            //Act
            String expected = "Port: Paris, Centrality: 53\n" +
                    "Port: Madrid, Centrality: 31\n" +
                    "Port: Berlim, Centrality: 31\n" +
                    "Port: Lisboa, Centrality: 17\n" +
                    "Port: Bruxelas, Centrality: 17\n";

            //Assert
            if (actual == null || actual.equals("")) {
                fail();
            }
            assertEquals(expected, actual);

        } catch (Exception e) {

        }
    }

    @Test
    void portCentralityControllerN9() {

        PortCentralityController controller = new PortCentralityController();

        try {
            //Arrange
            String actual = controller.getCriticalPorts(graph, 9);

            //Act
            String expected = "Port: Paris, Centrality: 53\n" +
                    "Port: Madrid, Centrality: 31\n" +
                    "Port: Berlim, Centrality: 31\n" +
                    "Port: Lisboa, Centrality: 17\n" +
                    "Port: Bruxelas, Centrality: 17\n" +
                    "Port: Luxemburgo, Centrality: 17\n" +
                    "Port: Berna, Centrality: 17\n" +
                    "Port: Roma, Centrality: 17\n" +
                    "Port: Varsóvia, Centrality: 17\n";

            //Assert
            if (actual == null || actual.equals("")) {
                fail();
            }
            assertEquals(expected, actual);

        } catch (Exception e) {

        }
    }
}
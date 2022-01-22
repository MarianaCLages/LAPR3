package lapr.project.shared.graph;

import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.NullVerticesException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortCentralityTest {

    static MatrixGraph<Vertex, Double> graph = new MatrixGraph<>(false);

    @BeforeAll
    static void beforeAll() {
        DatabaseConnection database = new DatabaseConnection("jdbc:oracle:thin:@vsgate-s1.dei.isep.ipp.pt:10676/xepdb1", "graphtest", "mypassword");
        FreightNetwork freightNetwork = new FreightNetwork();
        try {
            freightNetwork.createGraph(10, database);
            graph = freightNetwork.getGraph();
        } catch (NullVerticesException e) {
            fail();
        }
    }

    @Test
    void getCentralityOfNPorts() {
        //Arrange
        String expected = "Port: Monaco, Centrality: 4199\n" +
                "Port: Brest, Centrality: 4199\n" +
                "Port: Barcelona, Centrality: 4071\n" +
                "Port: Split, Centrality: 2993\n" +
                "Port: Hamburg, Centrality: 2989\n" +
                "Port: Zeebrugge, Centrality: 2533\n" +
                "Port: Aspropyrgos, Centrality: 1843\n" +
                "Port: Gdansk, Centrality: 1405\n" +
                "Port: Ambarli, Centrality: 1263\n" +
                "Port: Callao, Centrality: 1063\n";

        //Act
        String actual = PortCentrality.getCentralityOfNPorts(graph, 10);

        //Assert
        assertEquals(expected, actual);
    }
}
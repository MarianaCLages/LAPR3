package lapr.project.shared.graph;

import lapr.project.controller.App;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.NullVerticesException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PortCentralityTest {

    static FreightNetwork freightNetwork = new FreightNetwork();

    @BeforeAll
    static void beforeAll() {
        try {
            freightNetwork.createGraph(10, App.getInstance().getDatabaseConnection());
        } catch (NullVerticesException e) {
            fail();
        }
    }

    @Test
    void getCentralityOfNPorts() {
        //Arrange
        String expected1 = "Port: Monaco, Centrality: 4199\n" +
                "Port: Brest, Centrality: 4199\n" +
                "Port: Barcelona, Centrality: 4071\n" +
                "Port: Split, Centrality: 2993\n" +
                "Port: Hamburg, Centrality: 2989\n" +
                "Port: Zeebrugge, Centrality: 2533\n" +
                "Port: Aspropyrgos, Centrality: 1843\n" +
                "Port: Gdansk, Centrality: 1405\n" +
                "Port: Ambarli, Centrality: 1263\n" +
                "Port: Callao, Centrality: 1063\n";

        String expected2 = "Port: Brest, Centrality: 4199\n" +
                "Port: Monaco, Centrality: 4199\n" +
                "Port: Barcelona, Centrality: 4071\n" +
                "Port: Split, Centrality: 2993\n" +
                "Port: Hamburg, Centrality: 2989\n" +
                "Port: Zeebrugge, Centrality: 2533\n" +
                "Port: Aspropyrgos, Centrality: 1843\n" +
                "Port: Gdansk, Centrality: 1405\n" +
                "Port: Ambarli, Centrality: 1263\n" +
                "Port: Callao, Centrality: 1063\n";

        //Act
        String actual = PortCentrality.getCentralityOfNPorts(freightNetwork.getGraph(), 10);

        //Assert
        if(!actual.equals(expected1)) {
            assertEquals(expected2, actual);
        } else {
            assertEquals(expected1, actual);
        }
    }
}
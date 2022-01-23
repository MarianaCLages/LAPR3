package lapr.project.shared.graph;

import lapr.project.controller.App;
import lapr.project.model.Continent;
import lapr.project.model.Country;
import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
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
            freightNetwork.getGraph().addEdge((Vertex) new Port("21457", "Monaco", "Europe", new Country("Monaco", "MC".toCharArray(), "MCO".toCharArray(), 0.04, Continent.EUROPE), new FacilityLocation(7.45, 43.73333333), 200), (Vertex) new Port("13012", "Leixoes", "Europe", new Country("Portugal", "PT".toCharArray(), "PRT".toCharArray(), 10.31, Continent.EUROPE), new FacilityLocation(-8.7, 41.18333333), 200), 1.0);
        } catch (NullVerticesException e) {
            fail();
        }
    }

    @Test
    void getCentralityOfNPorts() {
        //Arrange


        String expected2 = "Port: Leixoes, Centrality: 4669\n" +
                "Port: Monaco, Centrality: 4575\n" +
                "Port: Brest, Centrality: 4199\n" +
                "Port: Split, Centrality: 2993\n" +
                "Port: Hamburg, Centrality: 2989\n" +
                "Port: Zeebrugge, Centrality: 2533\n" +
                "Port: Aspropyrgos, Centrality: 1843\n" +
                "Port: Gdansk, Centrality: 1405\n" +
                "Port: Ambarli, Centrality: 1263\n";

        //Act
        String actual = PortCentrality.getCentralityOfNPorts(freightNetwork.getGraph(), 9);

        //Assert
        assertEquals(expected2, actual);

    }
}
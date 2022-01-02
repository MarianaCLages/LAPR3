package lapr.project.model;


import lapr.project.shared.graph.FreightNetwork;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreightNetworkTest {

/*   @Test
    void createGraphTest() {// uses a different user on database in order to have solid data to test
        DatabaseConnection database = new DatabaseConnection("jdbc:oracle:thin:@vsgate-s1.dei.isep.ipp.pt:10676/xepdb1", "graphtest", "mypassword");
        FreightNetwork f = new FreightNetwork();
        try {
            assertTrue(f.createGraph(5, database));
            assertEquals(140, f.size());
            assertEquals(590, f.connectionsSize());
         //   assertEquals(6, f.getGraph().outgoingEdges(f.getGraph().vertex(30)).size());
        } catch (NullVerticesException e) {
            fail();
        }
    }*/

    @Test
    void numberOfVerticesTest() {
        FreightNetwork f = new FreightNetwork();
        Country country1 = new Country("pds", "df".toCharArray(), "dfs".toCharArray(), 15, Continent.EUROPE);
        City c1 = new City("dafg", 12, 23, country1);
        Country country2 = new Country("fgndfh", "gf".toCharArray(), "hkh".toCharArray(), 15, Continent.EUROPE);
        City c3 = new City("datg", 22, 33, country2);
        Country country3 = new Country("ads", "rf".toCharArray(), "sdg".toCharArray(), 15, Continent.EUROPE);
        City c2 = new City("dafr", 18, 28, country3);

        f.addEdgeAndCalculateWeight(c1, c3);
        f.addEdgeAndCalculateWeight(c1, c2);
        f.addEdgeAndCalculateWeight(c2, c3);
        assertEquals(3, f.size());


    }

    @Test
    void numberOfEdgesTest() {
        FreightNetwork f = new FreightNetwork();
        Country country1 = new Country("pds", "df".toCharArray(), "dfs".toCharArray(), 15, Continent.EUROPE);
        City c1 = new City("dafg", 12, 23, country1);
        Country country2 = new Country("fgndfh", "gf".toCharArray(), "hkh".toCharArray(), 15, Continent.EUROPE);
        City c3 = new City("datg", 22, 33, country2);
        Country country3 = new Country("ads", "rf".toCharArray(), "sdg".toCharArray(), 15, Continent.EUROPE);
        City c2 = new City("dafr", 18, 28, country3);

        f.addEdgeWithWeight(c1, c3, 5);
        f.addEdgeAndCalculateWeight(c1, c2);
        f.addEdgeAndCalculateWeight(c2, c3);

        assertEquals(6, f.connectionsSize());

    }


    @Test
    void createGraph() {
        FreightNetwork f = new FreightNetwork();
        Country country1 = new Country("pds", "df".toCharArray(), "dfs".toCharArray(), 15, Continent.EUROPE);
        City c1 = new City("dafg", 12, 23, country1);
        Country country2 = new Country("fgndfh", "gf".toCharArray(), "hkh".toCharArray(), 15, Continent.EUROPE);
        City c3 = new City("datg", 22, 33, country2);
        Country country3 = new Country("ads", "rf".toCharArray(), "sdg".toCharArray(), 15, Continent.EUROPE);
        City c2 = new City("dafr", 18, 28, country3);

        f.addEdgeAndCalculateWeight(c1, c3);
        f.addEdgeAndCalculateWeight(c1, c2);
        f.addEdgeAndCalculateWeight(c2, c3);

        String expected = "Vertices:\n" +
                "City{name='dafg', latitude=12.0, longitude=23.0, country=pds}\n" +
                "City{name='datg', latitude=22.0, longitude=33.0, country=fgndfh}\n" +
                "City{name='dafr', latitude=18.0, longitude=28.0, country=ads}\n" +
                "\n" +
                "Matrix:\n" +
                "   |  0  |  1  |  2 \n" +
                " 0 |     |  X  |  X  \n" +
                " 1 |  X  |     |  X  \n" +
                " 2 |  X  |  X  |     \n" +
                "\n" +
                "Edges:\n" +
                "From 0 to 1-> City{name='dafg', latitude=12.0, longitude=23.0, country=pds} -> City{name='datg', latitude=22.0, longitude=33.0, country=fgndfh}\n" +
                "Weight: 1537284.46\n" +
                "From 0 to 2-> City{name='dafg', latitude=12.0, longitude=23.0, country=pds} -> City{name='dafr', latitude=18.0, longitude=28.0, country=ads}\n" +
                "Weight: 856261.63\n" +
                "From 1 to 0-> City{name='datg', latitude=22.0, longitude=33.0, country=fgndfh} -> City{name='dafg', latitude=12.0, longitude=23.0, country=pds}\n" +
                "Weight: 1537284.46\n" +
                "From 1 to 2-> City{name='datg', latitude=22.0, longitude=33.0, country=fgndfh} -> City{name='dafr', latitude=18.0, longitude=28.0, country=ads}\n" +
                "Weight: 686004.95\n" +
                "From 2 to 0-> City{name='dafr', latitude=18.0, longitude=28.0, country=ads} -> City{name='dafg', latitude=12.0, longitude=23.0, country=pds}\n" +
                "Weight: 856261.63\n" +
                "From 2 to 1-> City{name='dafr', latitude=18.0, longitude=28.0, country=ads} -> City{name='datg', latitude=22.0, longitude=33.0, country=fgndfh}\n" +
                "Weight: 686004.95\n" +
                "\n";
        assertEquals(expected, f.toString());
    }
}
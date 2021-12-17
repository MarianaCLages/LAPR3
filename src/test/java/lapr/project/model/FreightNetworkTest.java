package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FreightNetworkTest {

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
                "City{name='dafg', latitude=12.0, longitude=23.0}\n" +
                "City{name='datg', latitude=22.0, longitude=33.0}\n" +
                "City{name='dafr', latitude=18.0, longitude=28.0}\n" +
                "\n" +
                "Matrix:\n" +
                "   |  0  |  1  |  2 \n" +
                " 0 |     |  X  |  X  \n" +
                " 1 |  X  |     |  X  \n" +
                " 2 |  X  |  X  |     \n" +
                "\n" +
                "Edges:\n" +
                "From 0 to 1-> City{name='dafg', latitude=12.0, longitude=23.0} -> City{name='datg', latitude=22.0, longitude=33.0}\n" +
                "Weight: 1537284.46\n" +
                "From 0 to 2-> City{name='dafg', latitude=12.0, longitude=23.0} -> City{name='dafr', latitude=18.0, longitude=28.0}\n" +
                "Weight: 856261.63\n" +
                "From 1 to 0-> City{name='datg', latitude=22.0, longitude=33.0} -> City{name='dafg', latitude=12.0, longitude=23.0}\n" +
                "Weight: 1537284.46\n" +
                "From 1 to 2-> City{name='datg', latitude=22.0, longitude=33.0} -> City{name='dafr', latitude=18.0, longitude=28.0}\n" +
                "Weight: 686004.95\n" +
                "From 2 to 0-> City{name='dafr', latitude=18.0, longitude=28.0} -> City{name='dafg', latitude=12.0, longitude=23.0}\n" +
                "Weight: 856261.63\n" +
                "From 2 to 1-> City{name='dafr', latitude=18.0, longitude=28.0} -> City{name='datg', latitude=22.0, longitude=33.0}\n" +
                "Weight: 686004.95\n" +
                "\n";
        Assertions.assertEquals(expected, f.toString());
    }
}
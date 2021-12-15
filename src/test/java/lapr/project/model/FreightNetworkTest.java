package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FreightNetworkTest {

    @Test
    void createGraph() {
        FreightNetwork f = new FreightNetwork();
        City c1 = new City("dafg", 12, 23);
        City c3 = new City("datg", 22, 33);
        City c2 = new City("dafr", 18, 28);

        f.addEdge(c1, c3, 15);
        f.addEdge(c1, c2, 17);
        f.addEdge(c2, c3, 17);

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
                "Weight: 15.0\n" +
                "From 0 to 2-> City{name='dafg', latitude=12.0, longitude=23.0} -> City{name='dafr', latitude=18.0, longitude=28.0}\n" +
                "Weight: 17.0\n" +
                "From 1 to 0-> City{name='datg', latitude=22.0, longitude=33.0} -> City{name='dafg', latitude=12.0, longitude=23.0}\n" +
                "Weight: 15.0\n" +
                "From 1 to 2-> City{name='datg', latitude=22.0, longitude=33.0} -> City{name='dafr', latitude=18.0, longitude=28.0}\n" +
                "Weight: 17.0\n" +
                "From 2 to 0-> City{name='dafr', latitude=18.0, longitude=28.0} -> City{name='dafg', latitude=12.0, longitude=23.0}\n" +
                "Weight: 17.0\n" +
                "From 2 to 1-> City{name='dafr', latitude=18.0, longitude=28.0} -> City{name='datg', latitude=22.0, longitude=33.0}\n" +
                "Weight: 17.0\n\n";
        Assertions.assertEquals(expected, f.toString());
    }

}
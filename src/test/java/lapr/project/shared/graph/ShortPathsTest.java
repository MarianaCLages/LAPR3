package lapr.project.shared.graph;


import lapr.project.controller.App;
import lapr.project.model.*;
import lapr.project.shared.exceptions.NullVerticesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class ShortPathsTest {
    //TODO tentar não depender da base de dados para assim os testes serem mais rapidos
    static FreightNetwork f = new FreightNetwork();

    @BeforeAll
    static void init() throws NullVerticesException {
        f.createGraph(5, App.getInstance().getDatabaseConnection());
    }


    @Test
    void landPathTest() throws NullVerticesException {
        List<Vertex> list = new ArrayList();
        list.add(new City("Lisbon", 38.71666667, -9.133333, new Country("Portugal", "PT".toCharArray(), "PTR".toCharArray(), 10.31, Continent.EUROPE)));
        list.add(new City("Madrid", 40.4, -3.683333, new Country("Spain", "ES".toCharArray(), "ESP".toCharArray(), 46.53, Continent.EUROPE)));


        Vertex vInitial = new City("Monaco", 40.4, -3.683333, new Country("Monaco", "MC".toCharArray(), "MCO".toCharArray(), 46.53, Continent.EUROPE));
        Vertex vFinal = new City("Paris", 48.86666667, 2.333333, new Country("France", "FR".toCharArray(), "FRA".toCharArray(), 66.99, Continent.EUROPE));

        Set<Vertex> actual = ShortPaths.landPath(f.getGraph(), list, vInitial, vFinal);

        Set<Vertex> expected = new LinkedHashSet<>();

        expected.add(new City("Monaco", 40.4, -3.683333, new Country("Monaco", "MC".toCharArray(), "MCO".toCharArray(), 46.53, Continent.EUROPE)));
        expected.add(new City("Paris", 48.86666667, 2.333333, new Country("France", "FR".toCharArray(), "FRA".toCharArray(), 66.99, Continent.EUROPE)));
        expected.add(new City("Madrid", 40.4, -3.683333, new Country("Spain", "ES".toCharArray(), "ESP".toCharArray(), 46.53, Continent.EUROPE)));
        expected.add(new City("Lisbon", 38.71666667, -9.133333, new Country("Portugal", "PT".toCharArray(), "PTR".toCharArray(), 10.31, Continent.EUROPE)));
        expected.add(new City("Madrid", 40.4, -3.683333, new Country("Spain", "ES".toCharArray(), "ESP".toCharArray(), 46.53, Continent.EUROPE)));
        expected.add(new City("Paris", 48.86666667, 2.333333, new Country("France", "FR".toCharArray(), "FRA".toCharArray(), 66.99, Continent.EUROPE)));

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.iterator().next(), actual.iterator().next());
        }

    }


    @Test
    void seaPathTest() throws NullVerticesException {
        List<Vertex> list = new ArrayList();
        list.add(new Port("17386", "Barcelona", "Europe", "Spain", new FacilityLocation(-8.7, 41.18333333), 500));


        Vertex vInitial = new Port("13012", "Leixoes", "Europe", "Portugal", new FacilityLocation(-8.7, 41.18333333), 500);
        Vertex vFinal = new Port("21457", "Monaco", "Europe", "Monaco", new FacilityLocation(-8.7, 41.18333333), 500);

        Set<Vertex> actual = ShortPaths.seaPath(f.getGraph(), list, vInitial, vFinal);

        Set<Vertex> expected = new LinkedHashSet<>();

        expected.add(new Port("13012", "Leixoes", "Europe", "Portugal", new FacilityLocation(-8.7, 41.18333333), 500));
        expected.add(new Port("13012", "Barcelona", "Europe", "Portugal", new FacilityLocation(-8.7, 41.18333333), 500));
        expected.add(new Port("13012", "Monaco", "Europe", "Portugal", new FacilityLocation(-8.7, 41.18333333), 500));

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.iterator().next(), actual.iterator().next());
        }
    }

    @Test
    void getPathTest() {
        List<Vertex> list = new ArrayList();
        list.add(new City("Lisbon", 38.71666667, -9.133333, new Country("Portugal", "PT".toCharArray(), "PTR".toCharArray(), 10.31, Continent.EUROPE)));
        list.add(new City("Madrid", 40.4, -3.683333, new Country("Spain", "ES".toCharArray(), "ESP".toCharArray(), 46.53, Continent.EUROPE)));


        Vertex vInitial = new Port("13012", "Leixoes", "Europe", "Portugal", new FacilityLocation(-8.7, 41.18333333), 500);
        Vertex vFinal = new City("Paris", 48.86666667, 2.333333, new Country("France", "FR".toCharArray(), "FRA".toCharArray(), 66.99, Continent.EUROPE));

        Set<Vertex> actual = ShortPaths.getPath(f.getGraph(), list, vInitial, vFinal);
        Set<Vertex> expected = new LinkedHashSet<>();

        expected.add(new Port("13012", "Leixoes", "Europe", "Portugal", new FacilityLocation(-8.7, 41.18333333), 500));
        expected.add(new Port("13012", "Setubal", "Europe", "Portugal", new FacilityLocation(-8.7, 41.18333333), 500));
        expected.add(new City("Lisbon", 38.71666667, -9.133333, new Country("Portugal", "PT".toCharArray(), "PTR".toCharArray(), 10.31, Continent.EUROPE)));
        expected.add(new City("Madrid", 40.4, -3.683333, new Country("Spain", "ES".toCharArray(), "ESP".toCharArray(), 46.53, Continent.EUROPE)));
        expected.add(new Port("13012", "Valencia", "Europe", "Portugal", new FacilityLocation(-8.7, 41.18333333), 500));
        expected.add(new Port("13012", "Barcelona", "Europe", "Portugal", new FacilityLocation(-8.7, 41.18333333), 500));
        expected.add(new Port("13012", "Monaco", "Europe", "Portugal", new FacilityLocation(-8.7, 41.18333333), 500));
        expected.add(new City("Monaco", 40.4, -3.683333, new Country("Monaco", "MC".toCharArray(), "MCO".toCharArray(), 46.53, Continent.EUROPE)));
        expected.add(new City("Paris", 48.86666667, 2.333333, new Country("France", "FR".toCharArray(), "FRA".toCharArray(), 66.99, Continent.EUROPE)));

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.iterator().next(), actual.iterator().next());
        }
    }
}
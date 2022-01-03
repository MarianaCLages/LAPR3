package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class PortTest {

    @Test
    void portTest() {
        try {
            Country c1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

            Port port = new Port("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);

            Port port2 = new Port("29002", "Liverpool", "Europe", "Portugal", new FacilityLocation(53.46666667, -3.033333333), 0);


        } catch (Exception e) {
            fail();
        }
    }
}
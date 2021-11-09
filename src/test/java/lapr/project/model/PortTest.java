package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortTest {

    @Test
    void portTest() {
        try {
            Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));
        } catch (Exception e) {
            fail();
        }
    }
}
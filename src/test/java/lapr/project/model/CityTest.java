package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    private final City city = new City("Deez", 10, 10);

    @Test
    void testToString() {

        String expected = "City{name='Deez', latitude=10.0, longitude=10.0}";
        String actual = city.toString();

        assertEquals(expected, actual);

    }

    @Test
    void getDesignation() {

        assertNotNull(city.getDesignation());

    }

    @Test
    void getLongitude() {
        assertNotNull(city.getDesignation());
    }

    @Test
    void getLatitude() {
        assertNotNull(city.getDesignation());
    }

    @Test
    void compareTo() {

        City city2 = new City("Yah", 20, 20);

        assertNotNull(city.compareTo(city2));

    }
}
package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    private final City city = new City("Deez", 10, 10, new Country("Portugal", null, null, 2000, Continent.EUROPE));

    @Test
    void testToString() {

        String expected = "City{name='Deez', latitude=10.0, longitude=10.0, country=Portugal}";
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
    void getColour() {
        assertEquals(-1, city.getColour());
    }

    @Test
    void setColour() {
        city.setColour(1);
        assertEquals(1, city.getColour());
    }

    @Test
    void isColour() {
        city.setColour(10);
        if (!city.isColour()) fail();
        city.setColour(-1);
        if (city.isColour()) fail();
    }

    @Test
    void getContinent() {
        assertNotNull(city.getContinent());
    }

    @Test
    void getCountry() {
        assertNotNull(city.getCountry());
    }

    @Test
    void compareTo() {

        City city2 = new City("Yah", 20, 20, new Country("Portugal", null, null, 2000, Continent.EUROPE));

        assertNotNull(city.compareTo(city2));

    }

    @Test
    void equals() {

        City city2 = new City("Yah", 20, 20, new Country("Portugal", null, null, 2000, Continent.EUROPE));

        boolean actual = city.equals(city2);

        if(actual) fail();

        boolean actual2 = city.equals(new Object());

        if(actual2) fail();

        City city3 = new City("Deez", 10, 90, new Country("Portugal", null, null, 20011, Continent.EUROPE));

        boolean actual3 = city.equals(city3);

        if(!actual3) fail();

    }

}

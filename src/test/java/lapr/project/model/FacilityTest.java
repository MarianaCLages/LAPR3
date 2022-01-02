package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacilityTest {
    Country c1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

    Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 10);


    @Test
    void facilityTest() {
        try {
            Facility facility2 = new Facility("29002", "Liverpool", "Europe", "Yah", new FacilityLocation(53.46666667, -3.033333333), 0);
            Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    void testEquals() {
        Facility facility1 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        Facility facility2 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(facility1, facility2);
    }

    @Test
    void testEqualsSameObject() {
        Facility facility1 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(facility1, facility1);
    }

    @Test
    void testEqualsAnotherClass() {
        Integer integer = 4;
        Facility facility1 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertNotEquals(facility1, integer);
    }

    @Test
    void testNotEquals() {
        Facility facility1 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        Facility facility2 = new Facility("29012", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertNotEquals(facility1, facility2);
    }

  /*  @Test
    void testHashCode() {
        int expected = -1380312517;
        Facility facility1 = new Facility("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));
        assertEquals(expected, facility1.hashCode());
    }*/

    @Test
    void getIdentification() {
        String expected = "29002";
        Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(expected, facility.getIdentification());
    }

    @Test
    void setIdentification() {
        String expected = "29012";
        Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        facility.setIdentification("29012");
        assertEquals(expected, facility.getIdentification());
    }

    @Test
    void getName() {
        String expected = "Liverpool";
        Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(expected, facility.getName());
    }

    @Test
    void setName() {
        String expected = "Liverpol";
        Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        facility.setName("Liverpol");
        assertEquals(expected, facility.getName());
    }

    @Test
    void getContinent() {
        String expected = "Europe";
        Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(expected, facility.getContinent());
    }

    @Test
    void setContinent() {
        String expected = "Europee";
        Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        facility.setContinent("Europee");
        assertEquals(expected, facility.getContinent());
    }

    @Test
    void getCountry() {
        String expected = "United Kingdom";
        Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(expected, facility.getCountry());
    }

    @Test
    void setCountry() {
        Country expected = new Country("Portugal", "pt".toCharArray(), "ptr".toCharArray(), 4, Continent.EUROPE);
        Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        facility.setCountry(expected);
        assertEquals("Portugal", facility.getCountry());
    }

    @Test
    void getLocation() {
        FacilityLocation expected = new FacilityLocation(53.46666667, -3.033333333);
        Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(expected, facility.getLocation());
    }

    @Test
    void setLocation() {
        FacilityLocation expected = new FacilityLocation(53.4666667, -3.033383333);
        Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        facility.setLocation(new FacilityLocation(53.4666667, -3.033383333));
        assertEquals(expected, facility.getLocation());
    }

    @Test
    void CompareToEquals() {
        Facility facility1 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        Facility facility2 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(0, facility1.compareTo(facility2));

    }


    @Test
    void CompareToGreaterLongitude() {
        Facility facility1 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(54, -3.033333333), 0);
        Facility facility2 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(1, facility1.compareTo(facility2));

    }

    @Test
    void CompareToGreaterLatitude() {
        Facility facility1 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -2.033333333), 0);
        Facility facility2 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(1, facility1.compareTo(facility2));

    }

    @Test
    void CompareToLowerLatitude() {
        Facility facility1 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -2.033333333), 0);
        Facility facility2 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(-1, facility2.compareTo(facility1));
    }

    @Test
    void CompareToLowerLongitude() {
        Facility facility1 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(52.46666667, -3.033333333), 0);
        Facility facility2 = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        assertEquals(-1, facility1.compareTo(facility2));
    }

    @Test
    void getCapacity() {
        int capacity = facility.getCapacity();

        assertNotNull(capacity);

        if (capacity == 0) fail();

    }

    @Test
    void equals() {

        Country c2 = new Country("United Kigdom", "UA".toCharArray(), "UAK".toCharArray(), 15, Continent.EUROPE);

        Facility facility1 = new Facility("29001", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 10);
        Facility facility2 = new Facility("29033", "LivArpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 10);
        Facility facility3 = new Facility("290012312", "Liverpool", "EuCZXCZXpE", c1, new FacilityLocation(53.46666667, -3.033333333), 10);
        Facility facility4 = new Facility("29001231232", "Liverpool", "Europe", c2, new FacilityLocation(53.46666667, -3.033333333), 10);
        Facility facility5 = new Facility("2912312002", "Liverpool", "Europe", c1, new FacilityLocation(51.46666667, -3.033333333), 10);
        Facility facility6 = new Facility("290012312", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -10.033333333), 10);
        Facility facility7 = new Facility("29012312302", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 1000);

        boolean actual1 = facility.equals(facility1);

        if (actual1) fail();

        boolean actual2 = facility.equals(facility2);

        if (actual2) fail();

        boolean actual3 = facility.equals(facility3);

        if (actual3) fail();

        boolean actual4 = facility.equals(facility4);

        if (actual4) fail();

        boolean actual5 = facility.equals(facility5);

        if (actual5) fail();

        boolean actual6 = facility.equals(facility6);

        if (actual6) fail();

        boolean actual7 = facility.equals(facility7);

        if (actual7) fail();


    }

    @Test
    void getDesignation() {
        String capacity = facility.getDesignation();

        assertNotNull(capacity);

    }

    @Test
    void isColour() {

        boolean isColour2 = facility.isColour();

        if(isColour2) fail();

        facility.setColour(10);

        boolean isColour3 = facility.isColour();

        if(!isColour3) fail();


    }

}
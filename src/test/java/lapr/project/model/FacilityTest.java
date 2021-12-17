package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacilityTest {
    Country c1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

    Facility facility = new Facility("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);

    @Test
    void facilityTest() {
        try {
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
}
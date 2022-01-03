package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    char[] alpha = {'a', 'b', 'c'};

    Country country = new Country("Portugal", alpha, alpha, 2000, Continent.EUROPE);

    @Test
    void getName() {

        String actual = country.getName();

        assertNotNull(actual);

        if (actual.equals("")) fail();

    }

    @Test
    void getAlpha2Code() {

        char[] actual = country.getAlpha2Code();

        assertNotNull(actual);

    }

    @Test
    void getAlpha3Code() {

        char[] actual = country.getAlpha3Code();

        assertNotNull(actual);

    }

    @Test
    void getPopulation() {

        double actual = country.getPopulation();

        assertNotNull(actual);

        if (actual == 0) {
            fail();
        }

    }

    @Test
    void getContinent() {

        Continent actual = country.getContinent();

        assertNotNull(actual);

        if(actual == null) fail();

    }
}
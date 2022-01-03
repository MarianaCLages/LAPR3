package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContinentTest {

    @Test
    void valueOfName() {
        Continent continent = Continent.ASIA;
        Continent actual = Continent.valueOfName(continent.getName());

        assertNotNull(actual);

        if (actual == null) fail();

    }

    @Test
    void getName() {
        Continent continent = Continent.ASIA;
        String actual = continent.getName();

        assertNotNull(actual);

        if (actual == null) fail();
    }
}
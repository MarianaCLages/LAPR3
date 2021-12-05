package lapr.project.utils.mappers.dto;

import lapr.project.model.FacilityLocation;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class PortDTOTest {

    PortDTO portDTO = new PortDTO("America", "USA", "01", "Port", new FacilityLocation(10, 10));

    @Test
    void testToString() {

        String expected = "Port Information :\n" +
                "Identification: America\n" +
                "Name: USA\n" +
                "Continent: 01\n" +
                "Country: Port\n" +
                "Longitude = 10.0\n" +
                "Latitude = 10.0\n";

        String actual = portDTO.toString();

        if (actual.equals("") || StringUtils.isBlank(actual)) {
            fail();
        }

        assertEquals(expected, actual);
    }
}
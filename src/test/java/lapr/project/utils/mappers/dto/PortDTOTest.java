package lapr.project.utils.mappers.dto;

import lapr.project.model.FacilityLocation;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class PortDTOTest {

    PortDTO portDTO = new PortDTO("America", "USA", "01", "Port", new FacilityLocation(10, 10));

    @Test
    void testToString() {

        String expected = "PortDTO{identification='America', name='USA', continent='01', country='Port', facilityLocation=latitude = 10.0, longitude = 10.0}";

        String actual = portDTO.toString();

        if (actual.equals("") || StringUtils.isBlank(actual)) {
            fail();
        }

        assertEquals(expected, actual);
    }
}
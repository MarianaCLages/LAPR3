package lapr.project.utils.mappers.dto;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class ShipDTOTest {

    ShipDTO shipDTO = new ShipDTO(1, 1, 2, 2);

    @Test
    void testToString() {

        String expected = "MMSI: 1; Total number of movements: 1; Travelled Distance: 2.0; Delta Distance: 2.0\n";

        String actual = shipDTO.toString();

        if (actual.equals("") || StringUtils.isBlank(actual)) fail();

        assertEquals(expected, actual);
    }
}
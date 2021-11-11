package lapr.project.utils.mappers;

import lapr.project.model.Ship;
import lapr.project.utils.mappers.dto.ShipDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipMapperTest {

    ShipMapper shipMapper = new ShipMapper();

    Ship ship1 = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

    List<Ship> shipList = new ArrayList<>();

    @Test
    void toDTO() {
        shipList.add(ship1);

        List<ShipDTO> actual = shipMapper.toDTO(shipList);

        if(actual.get(0) == null) fail();

    }
}
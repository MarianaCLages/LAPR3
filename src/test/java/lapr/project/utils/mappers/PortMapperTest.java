package lapr.project.utils.mappers;

import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
import lapr.project.utils.mappers.dto.PortDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortMapperTest {

    PortMapper portMapper = new PortMapper();

    Port port = new Port("America", "USA", "01", "Port", new FacilityLocation(10, 10));

    List<Port> portList = new ArrayList<>();

    @Test
    void toDTO() {
        portList.add(port);

        List<PortDTO> actual = portMapper.toDTO(portList);

        if (actual.get(0) == null) {
            fail();
        }
    }
}
package lapr.project.utils.mappers;

import lapr.project.model.Port;
import lapr.project.utils.mappers.dto.PortDTO;

import java.util.ArrayList;
import java.util.List;

public class PortMapper {

    /**
     * Converts the port info to DTO.
     *
     * @param port the port
     * @return the port info in DTO
     */
    private PortDTO toDTO(Port port) {
        return new PortDTO(port.getIdentification(), port.getName(), port.getContinent(), port.getCountry(), port.getLocation());
    }

    /**
     * Converts the port list to DTO.
     *
     * @param pList the port list
     * @return the port list in DTO
     */
    public List<PortDTO> toDTO(List<Port> pList) {
        List<PortDTO> portListDto = new ArrayList<>();
        for (Port port : pList) {
            portListDto.add(this.toDTO(port));
        }
        return portListDto;
    }


}

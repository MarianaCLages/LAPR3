package lapr.project.utils.mappers;

import lapr.project.model.Ship;
import lapr.project.utils.mappers.dto.ShipDTO;

import java.util.ArrayList;
import java.util.List;

public class ShipMapper {

    /**
     * Converts the ship info to DTO.
     *
     * @param ship the ship
     * @return the ship info in DTO
     */
    private ShipDTO toDTO(Ship ship) {
        return new ShipDTO(ship.getMmsi(), ship.getTotalNumberOfMovements(), ship.getTravelledDistance(), ship.getDeltaDistance());
    }

    /**
     * Converts the ship list to DTO.
     *
     * @param sList the ship list
     * @return the ship list in DTO
     */
    public List<ShipDTO> toDTO(List<Ship> sList) {
        List<ShipDTO> shipDTOS = new ArrayList<>();
        for (Ship ship : sList) {
            shipDTOS.add(this.toDTO(ship));
        }
        return shipDTOS;
    }
}


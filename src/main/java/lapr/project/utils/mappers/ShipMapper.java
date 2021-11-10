package lapr.project.utils.mappers;

import lapr.project.model.Ship;
import lapr.project.utils.mappers.dto.ShipDTO;

import java.util.ArrayList;
import java.util.List;

public class ShipMapper {

    private ShipDTO toDTO(Ship ship) {
        return new ShipDTO(ship.getMmsi(), ship.getTotalNumberOfMovements(), ship.getTravelledDistance(), ship.getDeltaDistance());
    }

    public List<ShipDTO> toDTO(List<Ship> sList) {
        List<ShipDTO> shipDTOS = new ArrayList<>();
        for (Ship ship : sList) {
            shipDTOS.add(this.toDTO(ship));
        }
        return shipDTOS;
    }
}


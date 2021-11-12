package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;
import lapr.project.utils.mappers.ShipMapper;
import lapr.project.utils.mappers.dto.ShipDTO;

import java.util.ArrayList;
import java.util.List;

public class ListShipsController {

    Company company;
    ShipStore shipStore;
    List<Ship> shipList;
    ShipMapper shipMapper;

    /**
     * Constructor.
     */
    public ListShipsController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    /**
     * Gets the Ship Store.
     *
     * @return the Ship Store
     */
    public ShipStore getShipStore() {
        return shipStore;
    }

    /**
     * Gets the ship list.
     *
     * @return the ship list
     */
    public List<Ship> getShipList() {
        shipList = new ArrayList<>();
        shipList = shipStore.transformAVLintoList();
        return shipList;
    }

    /**
     * Sorts the ship list.
     *
     * @return the sorted ship list
     */
    public List<Ship> sortedList() {
        if (shipStore.sortedList().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return shipStore.sortedList();
    }

    /**
     * Gets the ship list in DTO.
     *
     * @return the ship list in DTO
     */
    public List<ShipDTO> getShipListDTO() {
        try {
            this.shipMapper = new ShipMapper();
            List<Ship> sShips = sortedList();
            return shipMapper.toDTO(sShips);
        } catch (Exception e) {
            return null;
        }
    }
}
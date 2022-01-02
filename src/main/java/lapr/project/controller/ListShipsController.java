package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;
import lapr.project.utils.mappers.ShipMapper;
import lapr.project.utils.mappers.dto.ShipDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListShipsController {

    private final Company company;
    private final ShipStore shipStore;
    private List<Ship> shipList;
    private final ShipMapper shipMapper;

    /**
     * Constructor.
     */
    public ListShipsController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
        this.shipList = new ArrayList<>();
        this.shipMapper = new ShipMapper();
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
        this.shipList = shipStore.transformAVLintoListMMSI();
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
            List<Ship> sShips = sortedList();
            return shipMapper.toDTO(sShips);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
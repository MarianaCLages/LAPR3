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

    public ListShipsController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    public List<Ship> getShipList() {
        shipList = new ArrayList<>();
        shipList = shipStore.getlShip();
        return shipList;
    }

    public List<Ship> sortedList() {
        if (shipStore.sortedList().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return shipStore.sortedList();
    }

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
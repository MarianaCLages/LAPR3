package lapr.project.controller;

import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PairsOfShipsControllerTest {

    PairsOfShipsController pairsOfShipsController = new PairsOfShipsController();

    @Test
    void getPairs() {

        Ship ship = new Ship(111111111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1);

        Ship ship2 = new Ship(111112111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1);

        String sdate = "31/11/2020 23:16";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));

        String sdate2 = "31/12/2020 23:16";
        LocalDateTime date2 = LocalDateTime.from(formatter.parse(sdate2));

        Position posgeral = new Position(1, 0, 0, 1, 1, date);
        Position posgeral2 = new Position(1, 0, 0, 1, 1, date2);

        ship.getPosDate().addPosition(posgeral);
        ship.getPosDate().addPosition(posgeral2);

        ship2.getPosDate().addPosition(posgeral);
        ship2.getPosDate().addPosition(posgeral2);

        pairsOfShipsController.getShipStore().addShip(ship);
        pairsOfShipsController.getShipStore().addShip(ship2);

        try {
            String actual = pairsOfShipsController.getPairs();

            int size = pairsOfShipsController.getSize();

            if (size == 0) fail();
            if (actual.equals("") || StringUtils.isBlank(actual)) {
                fail();
            }

        } catch (NullPointerException e) {

        }
    }

    @Test
    void getShipStoreNotNull() {

        ShipStore shipStore = pairsOfShipsController.getShipStore();

        if (shipStore == null) fail();


    }

}
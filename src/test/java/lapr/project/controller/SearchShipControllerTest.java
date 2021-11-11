package lapr.project.controller;

import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchShipControllerTest {

    SearchShipController ctrl = new SearchShipController();

    @Test
    void searchShipByMMSI() {
        Ship s1 = new Ship(111111111, "A", "AAAAAAAAAA", 0, 1, "A", "A", 0, 0, 0, 0);
        ctrl.getShipStore().addShip(s1);
        assertEquals(s1,ctrl.searchShipByMMSI(111111111)); //check for an existing ship
    }

    @Test
    void searchShipByIMO() {
        Ship s2 = new Ship(222222222, "B", "BBBBBBBBBB", 0, 1, "B", "B", 0, 0, 0, 0);
        ctrl.getShipStore().addShip(s2);
        assertEquals(s2,ctrl.searchShipByIMO("BBBBBBBBBB")); //check for an existing ship
        assertEquals(null,ctrl.searchShipByIMO("DDDDDDDDDD")); //check for a non existing ship
    }

    @Test
    void searchShipByCallSign() {
        Ship s3 = new Ship(333333333, "C", "CCCCCCCCCC", 0, 1, "C", "C", 0, 0, 0, 0);
        ctrl.getShipStore().addShip(s3);
        assertEquals(s3,ctrl.searchShipByCallSign("C")); //check for an existing ship
        assertEquals(null,ctrl.searchShipByCallSign("D")); //check for a non existing ship
    }
}
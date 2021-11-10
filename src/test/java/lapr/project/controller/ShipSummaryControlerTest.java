package lapr.project.controller;

import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipSummaryControlerTest {

    ShipSummaryControler shipSummaryControler = new ShipSummaryControler();


    @Test
    void getShipSummaryByMMSI() {

        //Arrange
        shipSummaryControler.getShipStore().addShip(new Ship(111111111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1));
        //Act
        String actual = shipSummaryControler.getShipSummaryByMMSI(111111111);
        //Assert
        assertNotNull(actual);
    }

    @Test
    void getShipSummaryByIMO() {
        //Arrange
        shipSummaryControler.getShipStore().addShip(new Ship(111114111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1));
        //Act
        String actual = shipSummaryControler.getShipSummaryByIMO("IMO0000000");
        //Assert
        assertNotNull(actual);
    }

    @Test
    void getShipSummaryByCallSign() {
        //Arrange
        shipSummaryControler.getShipStore().addShip(new Ship(111114111, "name", "IMO0000000", 1, 1, "F", "A", 1, 1, 1, 1));
        //Act
        String actual = shipSummaryControler.getShipSummaryByCallSign("F");
        //Assert
        assertNotNull(actual);
    }
}
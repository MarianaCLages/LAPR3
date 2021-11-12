package lapr.project.controller;

import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class ShipSummaryControllerTest {

    ShipSummaryController shipSummaryController = new ShipSummaryController();


    @Test
    void getShipSummaryByMMSI() {

        //Arrange
        shipSummaryController.getShipStore().addShip(new Ship(111111111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1));
        //Act
        String actual = shipSummaryController.getShipSummaryByMMSI(111111111);
        //Assert
        assertNotNull(actual);
    }

    @Test
    void getShipSummaryByMMSIMutant() {

        shipSummaryController.getShipStore().addShip(new Ship(111111111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1));
        String actual = shipSummaryController.getShipSummaryByMMSI(111111111);

        if (StringUtils.isBlank(actual) || actual.equals("")) fail();
    }

/*    @Test
    void getShipSummaryByIMO() {
        //Arrange
        shipSummaryControler.getShipStore().addShip(new Ship(111114111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1));
        //Act
        String actual = shipSummaryControler.getShipSummaryByIMO("IMO0000000");
        //Assert
        assertNotNull(actual);
    }*/

    @Test
    void getShipSummaryByIMOMutant() {

        shipSummaryController.getShipStore().addShip(new Ship(111111111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1));
        String actual = shipSummaryController.getShipSummaryByIMO("IMO0000000");

        if (StringUtils.isBlank(actual) || actual.equals("")) fail();
    }

/*    @Test
    void getShipSummaryByCallSign() {
        //Arrange
        shipSummaryControler.getShipStore().addShip(new Ship(111114111, "name", "IMO0000000", 1, 1, "F", "A", 1, 1, 1, 1));
        //Act
        String actual = shipSummaryControler.getShipSummaryByCallSign("F");
        //Assert
        assertNotNull(actual);
    }*/

    @Test
    void getShipSummaryByCallSignMutant() {
        shipSummaryController.getShipStore().addShip(new Ship(111111111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1));
        String actual = shipSummaryController.getShipSummaryByCallSign("a");

        if (StringUtils.isBlank(actual) || actual.equals("")) fail();
    }
}
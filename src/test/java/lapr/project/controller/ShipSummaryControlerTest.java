package lapr.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipSummaryControlerTest {

    ShipSummaryControler shipSummaryControler = new ShipSummaryControler();


    @Test
    void getShipSummaryByMMSI() {

        //Arrange
        String expected = null;
        //Act
        String actual = shipSummaryControler.getShipSummaryByMMSI(1111111111);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getShipSummaryByIMO() {
        //Arrange
        String expected = null;
        //Act
        String actual = shipSummaryControler.getShipSummaryByIMO("IMO0000000");
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getShipSummaryByCallSign() {
        //Arrange
        String expected = null;
        //Act
        String actual = shipSummaryControler.getShipSummaryByCallSign("F");
        //Assert
        assertEquals(expected, actual);
    }
}
package lapr.project.controller;

import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipSummaryControlerTest {

    ShipSummaryControler shipSummaryControler = new ShipSummaryControler();


    @Test
    void getShipSummaryByMMSI() {

        //Arrange
        String expected = "MMSI : 111111111\n" +
                "Vessel name: A\n" +
                "Start Base date Time: null\n" +
                "End base date time : null\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 0\n" +
                "Max SOG : 0.0\n" +
                "Mean SOG : 0.0\n" +
                "Max COG : 0.0\n" +
                "Mean COG : 0.0\n" +
                "Departure Latitude : 0.0\n" +
                "Departure Longitude : 0.0\n" +
                "Arrival Latitude : 0.0\n" +
                "Arrival Longitude : 0.0\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        shipSummaryControler.getShipStore().addShip(new Ship(111111111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1));
        //Act
        String actual = shipSummaryControler.getShipSummaryByMMSI(111111111);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getShipSummaryByIMO() {
        //Arrange
        String expected = "IMO : IMO0000000\n" +
                "Vessel name: A\n" +
                "Start Base date Time: null\n" +
                "End base date time : null\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 0\n" +
                "Max SOG : 0.0\n" +
                "Mean SOG : 0.0\n" +
                "Max COG : 0.0\n" +
                "Mean COG : 0.0\n" +
                "Departure Latitude : 0.0\n" +
                "Departure Longitude : 0.0\n" +
                "Arrival Latitude : 0.0\n" +
                "Arrival Longitude : 0.0\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        shipSummaryControler.getShipStore().addShip(new Ship(111114111, "name", "IMO0000000", 1, 1, "a", "A", 1, 1, 1, 1));
        //Act
        String actual = shipSummaryControler.getShipSummaryByIMO("IMO0000000");
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getShipSummaryByCallSign() {
        //Arrange
        String expected = "Call Sign : F\n" +
                "Vessel name: A\n" +
                "Start Base date Time: null\n" +
                "End base date time : null\n" +
                "Total movement time: 0 minutes\n" +
                "Total number of movements : 0\n" +
                "Max SOG : 0.0\n" +
                "Mean SOG : 0.0\n" +
                "Max COG : 0.0\n" +
                "Mean COG : 0.0\n" +
                "Departure Latitude : 0.0\n" +
                "Departure Longitude : 0.0\n" +
                "Arrival Latitude : 0.0\n" +
                "Arrival Longitude : 0.0\n" +
                "Travelled Distance : 0.0\n" +
                "Delta Distance : 0.0";
        shipSummaryControler.getShipStore().addShip(new Ship(111114111, "name", "IMO0000000", 1, 1, "F", "A", 1, 1, 1, 1));
        //Act
        String actual = shipSummaryControler.getShipSummaryByCallSign("F");
        //Assert
        assertEquals(expected, actual);
    }
}
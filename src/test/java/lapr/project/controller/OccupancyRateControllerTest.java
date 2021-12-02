package lapr.project.controller;

import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OccupancyRateControllerTest {

    OccupancyRateController ctrl = new OccupancyRateController();

    Ship ship = new Ship(222222222, "aa", "IMO1111111", 11, 11, "AA", "70", 30, 30, 30, 30);


    @Test
    void occupancyRate() {
        //Arrange
        String expected = "Occupancy Rate: 6,67%";
        //Act
        String actual = ctrl.occupancyRate(ship.getMmsi());
        //Assert
        assertEquals(expected, actual);
    }
}
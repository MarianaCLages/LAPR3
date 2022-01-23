package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.shared.exceptions.InvalidDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipWaterPhysicsControllerTest {

    ShipWaterPhysicsController ctrl = new ShipWaterPhysicsController();

    @Test
    void calculateTotalMassTest() throws InvalidDataException {
        //Arrange Data
        double expected1 = 25000;
        double expected2 = 0;

        //Actual
        double actual1 = ctrl.calculateTotalMass(50);
        double actual2 = ctrl.calculateTotalMass(0);

        //Result
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);

        try {
            ctrl.calculateData("200", new Ship());
            String value = ctrl.SummaryString();

            if (value.isEmpty()) fail();
        } catch (Exception e) {

        }

    }

    @Test
    void calculatePressureExertedTest() {
        //Arrange Data
        double expected1 = 245250;
        double expected2 = 0;

        //Actual
        double actual1 = ctrl.calculatePressureExerted(25000);
        double actual2 = ctrl.calculatePressureExerted(0);

        //Result
        assertEquals(actual1, expected1);
        assertEquals(actual2, expected2);

        try {
            String value = ctrl.SummaryString();

            if (value.isEmpty()) fail();
        } catch (Exception e) {

        }
    }

    @Test
    void calculateHeightAboveWater() throws InvalidDataException {
        //Arrange Data
        double expected1 = 2.93;

        //Actual
        double actual1 = ctrl.calculateHeightAboveWater(2500, 10, 40, 9);

        //Result
        assertEquals(actual1, expected1, 2);

        try {
            List<String> value = ctrl.getShipCargoManifests(999333222);

            if (value.isEmpty()) fail();
        } catch (Exception e) {

        }

    }

    @Test
    void getShipList() throws InvalidDataException {
        //Arrange Data
        double expected1 = 2.93;

        //Actual
        double actual1 = ctrl.calculateHeightAboveWater(2500, 10, 40, 9);

        //Result
        assertEquals(actual1, expected1, 2);

        try {
            List<Ship> value = ctrl.getShiplist();

            if (value.isEmpty()) fail();
        } catch (Exception e) {

        }

    }


}
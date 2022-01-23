package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculateCenterControllerTest {

    @Test
    void calculateCenterController() {

        CalculateCenterController calculateCenterController = new CalculateCenterController();

        List<Double> list = calculateCenterController.calculateCenterOfMass(71);

        if (list.get(0) == 0) fail();
        if (list.get(1) == 0) fail();
        if (list.get(2) == 0) fail();

        double actualX71 = (calculateCenterController.calculateCenterOfMass(71).get(0));
        double actualY71 = (calculateCenterController.calculateCenterOfMass(71).get(1));
        double actualZ71 = (calculateCenterController.calculateCenterOfMass(71).get(2));

        double expectedX71 = 40.27777777777778;
        double expectedY71 = 16.944444444444443;
        double expectedZ71 = 17.77777777777778;

        assertEquals(expectedX71, actualX71);
        assertEquals(expectedY71, actualY71);
        assertEquals(expectedZ71, actualZ71);

        if (actualX71 != 40.27777777777778) fail();
        else if (actualY71 != 16.944444444444443) fail();
        else if (actualZ71 != 17.77777777777778) fail();

    }

}
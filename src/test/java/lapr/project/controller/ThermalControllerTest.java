package lapr.project.controller;

import lapr.project.shared.exceptions.ProportionalityConstantNullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThermalControllerTest {

    @Test
    void getMaterialThermalResistance() {

        ThermalController thermalController = new ThermalController();

        try {
            String actual = thermalController.getMaterialThermalResistance(1);


            if (actual == null || actual.equals("")) fail();

        } catch (ProportionalityConstantNullException e) {

        }

    }
}
package lapr.project.controller;

import lapr.project.shared.exceptions.ProportionalityConstantNullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThermalResistanceControllerTest {

    @Test
    void getMaterialThermalResistance() {

        ThermalResistanceController thermalResistanceController = new ThermalResistanceController();

        try {
            String actual = thermalResistanceController.getMaterialThermalResistance(1);


            if (actual == null || actual.equals("")) fail();

        } catch (ProportionalityConstantNullException e) {

        }

    }
}
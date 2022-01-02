package lapr.project.controller;

import lapr.project.shared.exceptions.MaterialTypeNullException;
import lapr.project.shared.exceptions.NoMaterialsForThatTemperatureException;
import lapr.project.shared.exceptions.NoMaterialsFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTemperatureControllerTest {

    @Test
    void materialTemperatureController() {

        MaterialTemperatureController materialTemperatureController = new MaterialTemperatureController();

        try {
            String actual = materialTemperatureController.materialTemperatureController(7);

            if(actual == null || actual.equals("")) fail();

        } catch (MaterialTypeNullException | NoMaterialsForThatTemperatureException | NoMaterialsFoundException e) {
            System.out.println("NANI");
        }


    }
}
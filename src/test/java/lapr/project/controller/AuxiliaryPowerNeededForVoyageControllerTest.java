package lapr.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuxiliaryPowerNeededForVoyageControllerTest {

    private AuxiliaryPowerNeededForVoyageController auxiliaryPowerNeededForVoyageController = new AuxiliaryPowerNeededForVoyageController();

    @Test
    void calculateSupplyNeededForMinus5() {

        String value = auxiliaryPowerNeededForVoyageController.calculateSupplyNeededForMinus5(2, 20, 9000);
        if (value.isEmpty()) fail();

    }

    @Test
    void calculateSupplyNeededFor7() {

        String value = auxiliaryPowerNeededForVoyageController.calculateSupplyNeededFor7(2, 20, 9000);
        if (value.isEmpty()) fail();

    }
}
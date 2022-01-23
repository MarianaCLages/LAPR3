package lapr.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountDaysEachShipScriptControllerTest {

    @Test
    void countDaysEachShipScript() {

        CountDaysEachShipScriptController controller = new CountDaysEachShipScriptController();

        String value = controller.CountDaysEachShipScript(2022);

        if (value.isEmpty()) fail();


    }
}
package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class FindAvailableShipsControllerTest {

    @Test
    void availableShipTest() {

        FindAvailableShipsController findAvailableShipsController = new FindAvailableShipsController();
        
        try {
            Assertions.assertNotNull(findAvailableShipsController.getAvailableShips());
        } catch (Exception e) {

        }

    }

}
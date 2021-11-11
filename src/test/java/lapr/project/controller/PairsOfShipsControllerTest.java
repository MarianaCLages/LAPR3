package lapr.project.controller;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class PairsOfShipsControllerTest {

    PairsOfShipsController pairsOfShipsController = new PairsOfShipsController();

    @Test
    void getPairs() {

        try {
            String actual = pairsOfShipsController.getPairs();

            if (actual.equals("") || StringUtils.isBlank(actual)) {
                fail();
            }

        } catch (NullPointerException e) {

        }
    }
}
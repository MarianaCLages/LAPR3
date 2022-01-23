package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvgOccupancyRateWithThresholdPerVoyageControllerTest {

    @Test
    void getAvgOccupancyRatePerManifest() {

        AvgOccupancyRateWithThresholdPerVoyageController avgOccupancyRatePerManifestController = new AvgOccupancyRateWithThresholdPerVoyageController();
        String value = null;

        try {
            value = avgOccupancyRatePerManifestController.getAvgOccupancyRateThreshold(999333222, "2021-12-01 00:00:00", "2022-01-10 00:00:00",66).toString();
            if (value.isEmpty()) fail();
        } catch (Exception e) {

        }

    }

    @Test
    void getAllShipsWithTrip() {

        AvgOccupancyRateWithThresholdPerVoyageController avgOccupancyRatePerManifestController = new AvgOccupancyRateWithThresholdPerVoyageController();

        try {
            List<String> listShips = avgOccupancyRatePerManifestController.getAllShipsWithTrip();

            if (listShips.isEmpty()) fail();

        } catch (SQLException exception) {

        }

    }

    @Test
    void verifyShip() {
        AvgOccupancyRateWithThresholdPerVoyageController avgOccupancyRatePerManifestController = new AvgOccupancyRateWithThresholdPerVoyageController();

        try {
            boolean flag = avgOccupancyRatePerManifestController.verifyShip("999333222");

            if (flag == false) fail();

        } catch (SQLException exception) {

        }
    }
}
package lapr.project.controller;

import lapr.project.shared.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OccupancyRateGivenCargoManifestDateControllerTest {

    @Test
    void getOccupancyRate() {

        OccupancyRateGivenCargoManifestDateController occupancyRateGivenCargoManifestDateController = new OccupancyRateGivenCargoManifestDateController();


        try {
            double actual = occupancyRateGivenCargoManifestDateController.getOccupancyRate(303296000, "2021-10-22 00:00:00");

            if (actual == 0) fail();

        } catch (ShipCargoCapacityException | VehicleIDNotValidException | CargoManifestDoesntBelongToThatShipException | CargoManifestIDException | ContainersInsideCargoManifestListSizeException | ContainerGrossException e) {

        }


    }
}
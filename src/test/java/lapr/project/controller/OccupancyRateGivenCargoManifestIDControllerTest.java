package lapr.project.controller;

import lapr.project.shared.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OccupancyRateGivenCargoManifestIDControllerTest {

    @Test
    void getOccupancyRate() {

        OccupancyRateGivenCargoManifestIDController occupancyRateGivenCargoManifestIDController = new OccupancyRateGivenCargoManifestIDController();


        try {
            double actual = occupancyRateGivenCargoManifestIDController.getOccupancyRate(303296000, "103");

            if (actual == 0) fail();

        } catch (ShipCargoCapacityException | VehicleIDNotValidException | CargoManifestDoesntBelongToThatShipException | ContainersInsideCargoManifestListSizeException | ContainerGrossException e) {

        }


    }
}
package lapr.project.controller;

import lapr.project.shared.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OffloadedContainersControllerTest {

    @Test
    void offLoadedShips() {

        OffloadedContainersController offloadedContainersController = new OffloadedContainersController();


        try {
            String actual = offloadedContainersController.offLoadedShips(303296000);

            if (actual == null || actual.equals("")) fail();

        } catch (FacilityNotFoundException | ContainersInsideCargoManifestListSizeException e) {

        }


    }
}
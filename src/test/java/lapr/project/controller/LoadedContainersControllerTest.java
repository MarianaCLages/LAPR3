package lapr.project.controller;

import lapr.project.shared.exceptions.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoadedContainersControllerTest {

    @Test
    void getLoadContainers() {

        LoadedContainersController loadedContainersController = new LoadedContainersController();


        try {

            String actual = loadedContainersController.getLoadContainers(303296000, "1");

            if (actual == null || actual.equals("")) fail();

        } catch (ShipCargoCapacityException | SQLException | VehicleIDNotValidException | CargoManifestDoesntBelongToThatShipException | CargoManifestIDException | ContainersInsideCargoManifestListSizeException | ContainerGrossException e) {
            System.out.println("NANI");
        }


    }
}
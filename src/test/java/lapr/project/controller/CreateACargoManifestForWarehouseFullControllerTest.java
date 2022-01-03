package lapr.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateACargoManifestForWarehouseFullControllerTest {

    @Test
    void createMatrix() {

        CreateACargoManifestForWarehouseFullController createACargoManifestForWarehouseFullController = new CreateACargoManifestForWarehouseFullController();


        try {

            createACargoManifestForWarehouseFullController.createMatrix("1", "2579", 1, 1, 1);

        } catch (Exception e) {

        }

    }
}
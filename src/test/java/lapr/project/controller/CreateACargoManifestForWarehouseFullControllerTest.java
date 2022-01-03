package lapr.project.controller;

import org.junit.jupiter.api.Test;

class CreateACargoManifestForWarehouseFullControllerTest {

    @Test
    void createMatrix() {

        CreateACargoManifestForWarehouseFullController createACargoManifestForWarehouseFullController = new CreateACargoManifestForWarehouseFullController();


        try {

            createACargoManifestForWarehouseFullController.createCMC("1", "2579", 1, 1, 1);

        } catch (Exception e) {

        }

    }
}
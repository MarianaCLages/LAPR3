package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class FacilityResourcesControllerTest {

    @Test
    void getFacilityResources() {

        FacilityResourcesController facilityResourcesController = new FacilityResourcesController();

        try {
            String actual = facilityResourcesController.getFacilityResources(10, 2021, 8);

            if (actual == null || actual.equals("")) fail();

        } catch (SQLException | IOException e) {
            System.out.println("NANI");
        }


    }
}
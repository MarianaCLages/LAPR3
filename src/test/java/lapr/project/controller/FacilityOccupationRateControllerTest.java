package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.fail;

class FacilityOccupationRateControllerTest {


    private final FacilityOccupationRateController facilityOccupationRateController = new FacilityOccupationRateController();

    @Test
    void getOccupation() {


        String actual = facilityOccupationRateController.getOccupation("500");
        String actual2 = facilityOccupationRateController.getOccupation("10");
        String actual3 = facilityOccupationRateController.getOccupation("10136");


        // if (!actual.equals("")) fail();


    }

    @Test
    void getNumberContainersLeaving() throws ParseException {


        String actual = facilityOccupationRateController.getNumberContainersLeaving("10136");

        if (!actual.equals("")) fail();


    }
}
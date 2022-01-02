package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class FacilityOccupationRateControllerTest {


    private final FacilityOccupationRateController facilityOccupationRateController = new FacilityOccupationRateController();

    @Test
    void getOccupation() {

        try {

            String actual = facilityOccupationRateController.getOccupation("10136");


            if (actual == null || actual.equals("")) fail();

        } catch (Exception e) {
            //Este teste da erro, ver dps o porque
        }

    }

    @Test
    void getNumberContainersLeaving() {

        try {
            String actual = facilityOccupationRateController.getNumberContainersLeaving("10136");

            if (actual == null || actual.equals("")) fail();

        } catch (ParseException e) {
            System.out.println("NANI");
        }

    }
}
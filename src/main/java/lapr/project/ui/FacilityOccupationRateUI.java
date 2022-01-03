package lapr.project.ui;

import lapr.project.controller.FacilityOccupationRateController;

import java.text.ParseException;

public class FacilityOccupationRateUI implements Runnable {

    private final FacilityOccupationRateController facilityOccupationRateController;

    public FacilityOccupationRateUI() {
        this.facilityOccupationRateController = new FacilityOccupationRateController();
    }


    @Override
    public void run() {

        int facilityId = 0;

        do {
            try {
                facilityId = Utils.readIntegerFromConsole("Please enter the facility ID:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid ID!");
                facilityId = 0;
            }
        } while (facilityId == 0);


        try {
            System.out.println(facilityOccupationRateController.getOccupation(String.valueOf(facilityId)));
            System.out.println(facilityOccupationRateController.getNumberContainersLeaving(String.valueOf(facilityId)));

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

    }
}

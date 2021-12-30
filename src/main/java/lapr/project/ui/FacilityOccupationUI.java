package lapr.project.ui;

import lapr.project.controller.FacilityOccupationRateController;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;

public class FacilityOccupationUI implements Runnable {

    private final FacilityOccupationRateController facilityOccupationRateController;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public FacilityOccupationUI() {
        this.facilityOccupationRateController = new FacilityOccupationRateController();
    }


    @Override
    public void run() {

        int facilityId = 0;

        do {
            try {
                facilityId = Utils.readIntegerFromConsole("Please enter the FacilityId:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid Id!");
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

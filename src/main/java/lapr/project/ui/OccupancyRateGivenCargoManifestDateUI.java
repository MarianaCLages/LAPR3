package lapr.project.ui;

import lapr.project.controller.OccupancyRateGivenCargoManifestDateController;
import lapr.project.shared.exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OccupancyRateGivenCargoManifestDateUI implements Runnable {

    private final OccupancyRateGivenCargoManifestDateController occupancyRateGivenCargoManifestDateController;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public OccupancyRateGivenCargoManifestDateUI() {
        this.occupancyRateGivenCargoManifestDateController = new OccupancyRateGivenCargoManifestDateController();
    }

    @Override
    public void run() {

        String date;
        int shipMmsi = 0;
        LocalDateTime dateTime = null;

        do {
            try {
                shipMmsi = Utils.readIntegerFromConsole("Please enter the desired MMSI: ");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid MMSI!");
                shipMmsi = 0;
            }
        } while (shipMmsi == 0);

        do {
            try {
                date = Utils.readLineFromConsole("Please enter the date of the cargo manifest (Format - yyyy-MM-dd HH:mm:ss): ");
                dateTime = LocalDateTime.from(formatter.parse(date));
            } catch (Exception e) {
                System.out.println("Please enter a valid date! (Or a valid date format!)");
                date = null;
            }
        } while (date == null);

        try {
            System.out.printf("For the given information, the occupancy rate its : %.2f%%\n", occupancyRateGivenCargoManifestDateController.getOccupancyRate(shipMmsi, date));
        } catch (ShipCargoCapacityException | ContainerGrossException | ContainersInsideCargoManifestListSizeException | CargoManifestIDException | CargoManifestDoesntBelongToThatShipException | VehicleIDNotValidException | IllegalArgumentException ex1) {
            System.out.println(ex1.getMessage());
        }

    }

}

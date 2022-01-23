package lapr.project.ui;

import lapr.project.controller.CalculateCenterController;

public class CalculateCenterUI implements Runnable {

    CalculateCenterController calculateCenterController;

    public CalculateCenterUI() {
        calculateCenterController = new CalculateCenterController();
    }

    @Override
    public void run() {
        int vesselType;

        do {
            vesselType = Utils.readIntegerFromConsole("Please enter the ship's vessel type: (Available options: 71, 72, 74)");

            if (vesselType != 72 && vesselType != 74 && vesselType != 71) {
                System.out.println("Please insert an available vessel type!");
            }
        } while (vesselType != 72 && vesselType != 74 && vesselType != 71);
        System.out.println(calculateCenterController.calculateCenterController(vesselType));
    }
}

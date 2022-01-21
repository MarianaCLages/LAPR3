package lapr.project.ui;

import lapr.project.controller.CalculateCenterController;

public class CalculateCenterUI implements Runnable {

    public CalculateCenterUI(){
        //Empty Constructor
    }

    @Override
    public void run() {

        CalculateCenterController calculateCenterController = new CalculateCenterController();
        int vesselType;

        do {
            vesselType = Utils.readIntegerFromConsole("Please insert ship's vessel type? (Available: 80,31,71)");

            if(vesselType != 80 && vesselType != 31 && vesselType != 71){
                System.out.println("Please Insert an available vessel type!");
            }
        }while (vesselType != 80 && vesselType != 31 && vesselType != 71);
        System.out.println(calculateCenterController.calculateCenterController(vesselType));
    }
}

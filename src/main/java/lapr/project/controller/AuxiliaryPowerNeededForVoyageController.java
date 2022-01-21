package lapr.project.controller;

import lapr.project.model.PhysicsCalculation;

public class AuxiliaryPowerNeededForVoyageController {

    public AuxiliaryPowerNeededForVoyageController(){
        // Empty constructor
    }

    public String calculateSupplyNeededForMinus5(int numberOfContainers, double temperature, int journeyTime) {
        return "The Supplies needed for the trip is " +
                PhysicsCalculation.calculateSuppliesNeededForMinus5(numberOfContainers,temperature, journeyTime) +
                ".";
    }

    public String calculateSupplyNeededFor7(int numberOfContainers, double temperature, int journeyTime) {
        return "The Supplies needed for the trip is " +
                PhysicsCalculation.calculateSuppliesNeededFor7(numberOfContainers,temperature, journeyTime) +
                ".";
    }
}

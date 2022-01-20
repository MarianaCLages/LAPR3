package lapr.project.controller;

import lapr.project.model.PhysicsCalculation;

import java.text.DecimalFormat;

public class TotalEnergySuppliedController {

    private final DecimalFormat df = new DecimalFormat("0.00");

    public TotalEnergySuppliedController() {
        // Empty constructor
    }

    public StringBuilder calculationToMinus5(int numberOfContainers, double temperature, int journeyTime) {
        StringBuilder sb = new StringBuilder();
        String totalEnergy = df.format(PhysicsCalculation.calculateTotalEnergySuppliedMinus5(numberOfContainers, temperature, journeyTime));
        return sb.append("Journey time: ").append(journeyTime).append("s").append("\n").append("Temperature: ").append(temperature).append("ºC").append("\n").append("Total energy to be supplied: ").append(totalEnergy).append(" J");
    }

    public StringBuilder calculationTo7(int numberOfContainers, double temperature, int journeyTime) {
        StringBuilder sb = new StringBuilder();
        String totalEnergy = df.format(PhysicsCalculation.calculateTotalEnergySupplied7(numberOfContainers, temperature, journeyTime));
        return sb.append("Journey time: ").append(journeyTime).append("s").append("\n").append("Temperature: ").append(temperature).append("ºC").append("\n").append("Total energy to be supplied: ").append(totalEnergy).append(" J");
    }
}

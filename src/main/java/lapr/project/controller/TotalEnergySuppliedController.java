package lapr.project.controller;

import lapr.project.model.PhysicsCalculation;

import java.text.DecimalFormat;

public class TotalEnergySuppliedController {

    private final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Constructor.
     */
    public TotalEnergySuppliedController() {
        // Empty constructor
    }

    /**
     * Gets the result values of the total energy needed to supply to a set of containers of -5ºC.
     *
     * @param numberOfContainers the number of containers
     * @param temperature        the container temperature
     * @param journeyTime        the journey time
     * @return a string with all the result values
     */
    public StringBuilder calculationToMinus5(int numberOfContainers, double temperature, int journeyTime) {
        StringBuilder sb = new StringBuilder();
        String totalEnergy = df.format(PhysicsCalculation.calculateTotalEnergySuppliedMinus5(numberOfContainers, temperature, journeyTime));
        return sb.append("Journey time: ").append(journeyTime).append("s").append("\n").append("Temperature: ").append(temperature).append("ºC").append("\n").append("Total energy to be supplied: ").append(totalEnergy).append(" J");
    }

    /**
     * Gets the result values of the total energy needed to supply to a set of containers of 7ºC.
     *
     * @param numberOfContainers the number of containers
     * @param temperature        the container temperature
     * @param journeyTime        the journey time
     * @return a string with all the result values
     */
    public StringBuilder calculationTo7(int numberOfContainers, double temperature, int journeyTime) {
        StringBuilder sb = new StringBuilder();
        String totalEnergy = df.format(PhysicsCalculation.calculateTotalEnergySupplied7(numberOfContainers, temperature, journeyTime));
        return sb.append("Journey time: ").append(journeyTime).append("s").append("\n").append("Temperature: ").append(temperature).append("ºC").append("\n").append("Total energy to be supplied: ").append(totalEnergy).append(" J");
    }
}

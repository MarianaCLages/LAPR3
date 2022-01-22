package lapr.project.controller;

import lapr.project.model.PhysicsCalculation;

import java.text.DecimalFormat;

public class TotalEnergySuppliedController {

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
    public Double calculationToMinus5(int numberOfContainers, double temperature, int journeyTime) {
        return PhysicsCalculation.calculateTotalEnergySuppliedMinus5(numberOfContainers, temperature, journeyTime);
    }

    /**
     * Gets the result values of the total energy needed to supply to a set of containers of 7ºC.
     *
     * @param numberOfContainers the number of containers
     * @param temperature        the container temperature
     * @param journeyTime        the journey time
     * @return a string with all the result values
     */
    public Double calculationTo7(int numberOfContainers, double temperature, int journeyTime) {
        return PhysicsCalculation.calculateTotalEnergySupplied7(numberOfContainers, temperature, journeyTime);
    }
}

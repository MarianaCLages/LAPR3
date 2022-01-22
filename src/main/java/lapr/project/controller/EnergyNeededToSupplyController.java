package lapr.project.controller;

import lapr.project.model.PhysicsCalculation;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnergyNeededToSupplyController {

    /**
     * Constructor.
     */
    public EnergyNeededToSupplyController() {
        // Empty constructor
    }

    /**
     * Gets the value of the energy needed to supply to the container cargo (with a temperature of -5ºC) in a voyage, depending on the position of the containers on the ship.
     *
     * @param section    the trip section
     * @param frontFaces the number of front faces
     * @param sideSides  the number of side faces
     * @param topSides   the number of top sides
     * @return a string with all the result values
     */
    public String calculateToMinus5(Map<Integer, Double> section, int frontFaces, int sideSides, int topSides) {
        return "The total energy needed will be " +
                PhysicsCalculation.calculateEnergyConsumptionMinus5(section, frontFaces, sideSides, topSides) +
                " Joules.";
    }

    /**
     * Gets the value of the energy needed to supply to the container cargo (with a temperature of 7ºC) in a voyage, depending on the position of the containers on the ship.
     *
     * @param section the trip section
     * @param front   the number of front faces
     * @param side    the number of side faces
     * @param top     the number of top faces
     * @return a string with all the result values
     */
    public String calculateTo7(LinkedHashMap<Integer, Double> section, int front, int side, int top) {
        return "The total energy needed will be " +
                PhysicsCalculation.calculateEnergyConsumption7(section, front, side, top) +
                " Joules.";
    }
}
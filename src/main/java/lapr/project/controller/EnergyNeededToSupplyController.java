package lapr.project.controller;

import lapr.project.model.PhysicsCalculation;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnergyNeededToSupplyController {
    public EnergyNeededToSupplyController() {
    }

    public String calculateToMinus5(Map<Integer, Double> section, int frontFaces, int sideSides, int topSides) {
        return "The total energy needed will be " +
                PhysicsCalculation.calculateEnergyConsumptionMinus5(section, frontFaces, sideSides, topSides) +
                " Joules.";
    }

    public String calculateTo7(LinkedHashMap<Integer, Double> section, int front, int side, int top) {
        return "The total energy needed will be " +
                PhysicsCalculation.calculateEnergyConsumption7(section, front, side, top) +
                " Joules.";
    }
}

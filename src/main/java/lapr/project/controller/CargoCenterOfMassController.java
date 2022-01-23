package lapr.project.controller;

import lapr.project.model.PhysicsCalculation;

import java.util.List;

public class CargoCenterOfMassController {
    public CargoCenterOfMassController() {
    }

    public double calculateCenter(List<Double> list) {
        return PhysicsCalculation.calculateCenterMassOneDim(list);
    }
}


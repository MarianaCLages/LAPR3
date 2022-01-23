package lapr.project.controller;

import lapr.project.model.PhysicsCalculation;

import java.util.List;

public class CargoCenterOfMassController {
    /**
     * Empty Constructor
     */
    public CargoCenterOfMassController() {
        //Empty
    }


    /**
     * Calculates the center of mass of a given Vessel for a given Cargo Manifest (X number of containers)
     * @param list recieves all containers positions inside that cargo manifest
     * @return the center of mass of that cargo manifest
     */
    public double calculateCenter(List<Double> list) {
        return PhysicsCalculation.calculateCenterMassOneDim(list);
    }
}
package lapr.project.controller;

import lapr.project.shared.CalculateCenter;

import java.util.List;

public class CalculateCenterController {

    private final CalculateCenter calculateCenter;

    /**
     * Constructor.
     */
    public CalculateCenterController() {
       this.calculateCenter = new CalculateCenter();
    }

    /**
     * Gets the value of the center of mass calculation for a vessel.
     *
     * @param vesselType the vessel type
     * @return the value of the center of mass calculation for a vessel
     */
    public List<Double> calculateCenterOfMass(int vesselType) {
        return calculateCenter.calculateCenter(vesselType);
    }
}

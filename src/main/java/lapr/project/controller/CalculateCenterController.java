package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CalculateCenter;

public class CalculateCenterController {

    /**
     * Constructor.
     */
    public CalculateCenterController() {
        //Empty Constructor
    }

    /**
     * Gets the value of the center of mass calculation for a vessel.
     *
     * @param vesselType the vessel type
     * @return the value of the center of mass calculation for a vessel
     */
    public String calculateCenterController(int vesselType) {
        CalculateCenter calculateCenter = new CalculateCenter();
        return calculateCenter.calculateCenter(vesselType);
    }
}

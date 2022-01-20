package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CalculateCenter;

public class CalculateCenterController {

    public CalculateCenterController(){
        //Empty Constructor
    }

    public String calculateCenterController(int vesselType){
        CalculateCenter calculateCenter = new CalculateCenter();
        return calculateCenter.calculateCenter(vesselType);
    }
}

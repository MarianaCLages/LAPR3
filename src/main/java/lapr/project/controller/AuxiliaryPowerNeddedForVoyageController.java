package lapr.project.controller;

import lapr.project.controller.App;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.PhysicsCalculation;
import lapr.project.shared.exceptions.ProportionalityConstantNullException;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AuxiliaryPowerNeddedForVoyageController {

    private final DatabaseConnection connection;

    public AuxiliaryPowerNeddedForVoyageController(){
        connection = App.getInstance().getDatabaseConnection();
    }

    public String calculateSupplyNeededForMinus5(int numberOfContainers, double temperature, int journeyTime) {
        return "The Supplies needed for the trip is " +
                PhysicsCalculation.calculateSuppliesNeededForMinus5(numberOfContainers,temperature, journeyTime) +
                ".";
    }

    public String calculateSupplyNeededFor7(int numberOfContainers, double temperature, int journeyTime) {
        return "The Supplies needed for the trip is " +
                PhysicsCalculation.calculateSuppliesNeededFor7(numberOfContainers,temperature, journeyTime) +
                ".";
    }
}

package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.model.PhysicsCalculation;
import lapr.project.shared.Constants;

import java.sql.SQLException;
import java.text.DecimalFormat;

public class TotalEnergyInACertainTripController {

    private final DecimalFormat df = new DecimalFormat("0.00");
    private final DatabaseConnection databaseConnection;

    public TotalEnergyInACertainTripController() {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    public StringBuilder calculationToMinus5Degrees() {
        StringBuilder sb = new StringBuilder();
        String totalEnergy = df.format(PhysicsCalculation.calculateEnergyConsumptionDeterminedTrip7C());
        return sb.append("Journey time: ").append(Constants.VOYAGE_TIME).append("s").append("\n").append("Temperature: ").append("20 ").append("ºC").append("\n").append("Total energy to be supplied: ").append(totalEnergy).append(" J");
    }

    public StringBuilder calculationTo7Degrees() {
        StringBuilder sb = new StringBuilder();
        String totalEnergy = df.format(PhysicsCalculation.calculateEnergyConsumptionDeterminedTrip7C());
        return sb.append("Journey time: ").append(Constants.VOYAGE_TIME).append("s").append("\n").append("Temperature: ").append("20 ").append("ºC").append("\n").append("Total energy to be supplied: ").append(totalEnergy).append(" J");
    }

    public String getAllTripList() throws SQLException {
        return DataBaseUtils.getAllTrips(databaseConnection);
    }

    public boolean verifyTrip(String option) throws SQLException {
        return DataBaseUtils.verifyTrip(option,databaseConnection);
    }

}
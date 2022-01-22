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

    /**
     * Constructor.
     */
    public TotalEnergyInACertainTripController() {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the result values of the energy needed to supply to a container of -5ºC with an exterior temperature of 20ºC and a travel time of 2h30.
     *
     * @return a string with all the result values
     */
    public StringBuilder calculationToMinus5Degrees() {
        StringBuilder sb = new StringBuilder();
        String totalEnergy = df.format(PhysicsCalculation.calculateEnergyConsumptionDeterminedTripMinus5C());
        return sb.append("Journey time: ").append(Constants.VOYAGE_TIME).append("s").append("\n").append("Temperature: ").append("20").append("ºC").append("\n").append("Total energy to be supplied: ").append(totalEnergy).append(" J");
    }

    /**
     * Gets the result values of the energy needed to supply to a container of 7ºC with an exterior temperature of 20ºC and a travel time of 2h30.
     *
     * @return a string with all the result values
     */
    public StringBuilder calculationTo7Degrees() {
        StringBuilder sb = new StringBuilder();
        String totalEnergy = df.format(PhysicsCalculation.calculateEnergyConsumptionDeterminedTrip7C());
        return sb.append("Journey time: ").append(Constants.VOYAGE_TIME).append("s").append("\n").append("Temperature: ").append("20").append("ºC").append("\n").append("Total energy to be supplied: ").append(totalEnergy).append(" J");
    }

    /**
     * Gets a list with all the trips.
     *
     * @return a list with all the trips
     * @throws SQLException
     */
    public String getAllTripList() throws SQLException {
        return DataBaseUtils.getAllTrips(databaseConnection);
    }

    /**
     * Verifies if the trip exists.
     *
     * @param option the trip ID chosen by the user
     * @return true if it exists, false if it doesn't
     * @throws SQLException
     */
    public boolean verifyTrip(String option) throws SQLException {
        return DataBaseUtils.verifyTrip(option, databaseConnection);
    }
}
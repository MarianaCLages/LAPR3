package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.model.PhysicsCalculation;

import java.sql.SQLException;
import java.text.DecimalFormat;

public class TotalEnergySuppliedController {

    private final DatabaseConnection connection;

    /**
     * Constructor.
     */
    public TotalEnergySuppliedController() {
        this.connection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the result values of the total energy needed to supply to a set of containers of -5ºC.
     *
     * @param numberOfContainers the number of containers
     * @param temperature        the container temperature
     * @param journeyTime        the journey time
     * @return a string with all the result values
     */
    public Double calculationToMinus5(int numberOfContainers, double temperature, int journeyTime) {
        return PhysicsCalculation.calculateTotalEnergySuppliedMinus5(numberOfContainers, temperature, journeyTime);
    }

    /**
     * Gets the result values of the total energy needed to supply to a set of containers of 7ºC.
     *
     * @param numberOfContainers the number of containers
     * @param temperature        the container temperature
     * @param journeyTime        the journey time
     * @return a string with all the result values
     */
    public Double calculationTo7(int numberOfContainers, double temperature, int journeyTime) {
        return PhysicsCalculation.calculateTotalEnergySupplied7(numberOfContainers, temperature, journeyTime);
    }

    /**
     * Gets a list with all the trips.
     *
     * @return a list with all the trips
     * @throws SQLException
     */
    public String getAllTripList() throws SQLException {
        return DataBaseUtils.getAllTrips(connection);
    }

    /**
     * Verifies if the trip exists.
     *
     * @param option the trip ID chosen by the user
     * @return true if it exists, false if it doesn't
     * @throws SQLException
     */
    public boolean verifyTrip(String option) throws SQLException {
        return DataBaseUtils.verifyTrip(option, connection);
    }
}

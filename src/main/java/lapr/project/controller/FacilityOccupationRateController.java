package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CallNumberOfContainersLeavingFunction;
import lapr.project.data.DataBaseScripts.CallOccupationRateFunction;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;

import java.sql.Date;
import java.text.ParseException;

import static lapr.project.data.DataBaseScripts.CheckWarehouseExistsFunction.warehouseExists;

public class FacilityOccupationRateController {

    private final DatabaseConnection connection;

    /**
     * Constructor.
     */
    public FacilityOccupationRateController() {
        this.connection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the facility occupation.
     *
     * @param id the facility ID
     * @return the facility occupation
     */
    public String getOccupation(String id) {
        if (warehouseExists(connection, id)) {
            double occupation = 0;
            occupation = CallOccupationRateFunction.occupationRateFunction(connection, id);
            StringBuilder sb = new StringBuilder();

            sb.append("Occupation of the facility ").append(id).append(" is ").append(occupation);

            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * Gets the number of containers leaving the facility in the next month.
     *
     * @param id the facility ID
     * @return the number of containers leaving the facility in the next month
     * @throws ParseException
     */
    public String getNumberContainersLeaving(String id) throws ParseException {
        int numberOfContainers;

        if (warehouseExists(connection, id)) {
            Date dateDate = new Date(System.currentTimeMillis());
            numberOfContainers = CallNumberOfContainersLeavingFunction.numberOfContainers(connection, id, dateDate);

            return "There are " + numberOfContainers + " leaving facility " + id + " in the next month";
        } else {
            return "";
        }
    }

}

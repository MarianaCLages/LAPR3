package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CallNumberOfContainersLeavingFunction;
import lapr.project.data.DataBaseScripts.CallOccupationRateFunction;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;

import java.sql.Date;
import java.text.ParseException;

public class FacilityOccupationRateController {
    private Company company;
    private DatabaseConnection connection;


    public FacilityOccupationRateController() {
        this.company = App.getInstance().getCompany();
        this.connection = App.getInstance().getDatabaseConnection();

    }

    public String getOccupation(String id) {
        double occupation = 0;
        occupation = CallOccupationRateFunction.occupationRateFunction(connection, id);
        StringBuilder sb = new StringBuilder();

        sb.append("Occupation of the facility ").append(id).append(" is ").append(occupation);

        return sb.toString();
    }


    public String getNumberContainersLeaving(String id) throws ParseException {
        int numberOfContainers = 0;

        Date dateDate = new Date(System.currentTimeMillis());
        numberOfContainers = CallNumberOfContainersLeavingFunction.numberOfContainers(connection, id, dateDate);

        return "There are " + numberOfContainers + " leaving facility " + id + " in the next month";
    }

}

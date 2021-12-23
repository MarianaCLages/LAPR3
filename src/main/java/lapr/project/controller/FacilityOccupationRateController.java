package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CallNumberOfContainersLeavingFunction;
import lapr.project.data.DataBaseScripts.CallOccupationRateFunction;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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


    public String getNumberContainersLeaving(String id, String date) throws ParseException {
        int numberOfContainers = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsed = (Date) format.parse(date);
        Date dateDate = new Date(parsed.getTime());
        numberOfContainers = CallNumberOfContainersLeavingFunction.numberOfContainers(connection, id, dateDate);

        return "There are " + numberOfContainers + " leaving facility " + id + " in the month next to " + date;
    }

}

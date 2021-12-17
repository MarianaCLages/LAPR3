package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.model.City;
import lapr.project.model.Company;
import lapr.project.model.FreightNetwork;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

public class CreateGraphController {
    private final Company company;
    private final DatabaseConnection connection;

    /**
     * Constructor.
     */
    public CreateGraphController() {
        this.company = App.getInstance().getCompany();
        this.connection = App.getInstance().getDatabaseConnection();

    }

    public void createGraph(int n) {

    }


}

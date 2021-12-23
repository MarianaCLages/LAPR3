package lapr.project.controller;


import lapr.project.data.DataBaseScripts.CallTheAvailableResourcesFunction;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FacilityResourcesController {


    private Company company;
    private DatabaseConnection connection;
    private CallTheAvailableResourcesFunction callTheAvailableResourcesFunction;


    public FacilityResourcesController(){
        company = App.getInstance().getCompany();
        connection = App.getInstance().getDatabaseConnection();
        callTheAvailableResourcesFunction = new CallTheAvailableResourcesFunction();
    }

    public String getFacilityResources(int month, int year, int id) throws SQLException, IOException {

        return callTheAvailableResourcesFunction.callFunction(month,year,id,connection) ;
    }
}

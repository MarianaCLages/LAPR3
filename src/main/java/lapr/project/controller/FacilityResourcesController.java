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


    public FacilityResourcesController(){
        company = App.getInstance().getCompany();
        connection = App.getInstance().getDatabaseConnection();

    }

    public String getFacilityResources(int month, int year, int id) throws SQLException, IOException {

        CallTheAvailableResourcesFunction callTheAvailableResourcesFunction = new CallTheAvailableResourcesFunction();
        SimpleDateFormat sdf = new SimpleDateFormat(
                "MM-dd-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1); // <-- months start
        // at 0.
        cal.set(Calendar.DAY_OF_MONTH, 1);

        java.sql.Date dateI = new java.sql.Date(cal.getTimeInMillis());

        CallableStatement cstmt = connection.getConnection().prepareCall("{? = call getShipAreaByDate1223(?,?)}");

        cstmt.registerOutParameter(1, Types.NUMERIC);
        cstmt.setInt(2,id); //8
        cstmt.setDate(3,dateI);

        cstmt.executeUpdate();



        CallableStatement cstmt2 = connection.getConnection().prepareCall("{? = call getContainerByDate1223(?,?)}");

        cstmt2.registerOutParameter(1,Types.NUMERIC);
        cstmt2.setInt(2,id);
        cstmt2.setDate(3,dateI);

        cstmt2.executeUpdate();




        return callTheAvailableResourcesFunction.callFunction(cstmt,cstmt2);
        /*
        if(facilityResourcesScript.resources(connection,id,month,year)){
            return true;
        }*/


    }
}

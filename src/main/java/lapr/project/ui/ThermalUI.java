package lapr.project.ui;

import lapr.project.controller.ThermalController;
import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

public class ThermalUI implements Runnable{

    public ThermalUI(){}

    @Override
    public void run(){

        ThermalController thermalController = new ThermalController();

        int id = Utils.readIntegerFromConsole("Container ID:");
        try {
            DatabaseConnection db = ConnectionFactory.getInstance().getDatabaseConnection();
            System.out.println(thermalController.ThermalController(id,db));
        } catch (IOException | SQLException ioException) {
            ioException.printStackTrace();
        }




    }
}

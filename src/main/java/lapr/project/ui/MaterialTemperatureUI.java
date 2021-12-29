package lapr.project.ui;

import lapr.project.controller.MaterialTemperatureController;
import lapr.project.data.ConnectionFactory;
import lapr.project.data.DataBaseScripts.GetMaterialsScript;
import lapr.project.data.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

public class MaterialTemperatureUI implements Runnable {

    public MaterialTemperatureUI(){}


    @Override
    public void run() {


        int temperature;
        MaterialTemperatureController materialTemperatureController = new MaterialTemperatureController();
        DatabaseConnection db = null;
        try {
            db = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        temperature = Utils.readIntegerFromConsole("Temperature?");
        try {
            System.out.println(materialTemperatureController.materialTemperatureController(temperature,db));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}

package lapr.project.controller;

import lapr.project.data.DataBaseScripts.GetMaterialsScript;
import lapr.project.data.DatabaseConnection;

import java.sql.SQLException;

public class MaterialTemperatureController {

    public MaterialTemperatureController(){}


    public String materialTemperatureController(int temperature, DatabaseConnection db) throws SQLException {
        GetMaterialsScript getMaterialsScript = new GetMaterialsScript();
        return getMaterialsScript.materialScript(temperature,db);
    }
}

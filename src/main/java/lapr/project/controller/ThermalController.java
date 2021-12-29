package lapr.project.controller;

import lapr.project.data.DataBaseScripts.GetMaterialsScript;
import lapr.project.data.DatabaseConnection;

import java.sql.SQLException;

public class ThermalController {

    public ThermalController(){}

    public String ThermalController(int id, DatabaseConnection db) throws SQLException {

        GetMaterialsScript getMaterialsScript = new GetMaterialsScript();

        return getMaterialsScript.getThermalResistance(id,db);
    }
}

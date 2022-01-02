package lapr.project.controller;

import lapr.project.data.DataBaseScripts.GetMaterialsScript;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.ProportionalityConstantNullException;

public class ThermalController {

    private final GetMaterialsScript getMaterialsScript;

    public ThermalController() {
        DatabaseConnection databaseConnection = App.getInstance().getDatabaseConnection();
        getMaterialsScript = new GetMaterialsScript(databaseConnection);
    }

    public String getMaterialThermalResistance(int id) throws ProportionalityConstantNullException {
        return getMaterialsScript.getThermalResistance(id);
    }
}

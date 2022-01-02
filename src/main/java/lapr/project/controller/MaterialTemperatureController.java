package lapr.project.controller;

import lapr.project.data.DataBaseScripts.GetMaterialsScript;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.MaterialTypeNullException;
import lapr.project.shared.exceptions.NoMaterialsForThatTemperatureException;
import lapr.project.shared.exceptions.NoMaterialsFoundException;

public class MaterialTemperatureController {

    private final GetMaterialsScript getMaterialsScript;

    /**
     * Constructor.
     */
    public MaterialTemperatureController() {
        DatabaseConnection databaseConnection = App.getInstance().getDatabaseConnection();
        getMaterialsScript = new GetMaterialsScript(databaseConnection);
    }

    /**
     * Gets the materials for a specific temperature.
     *
     * @param temperature the temperature
     * @return the materials for a specific temperature
     * @throws MaterialTypeNullException
     * @throws NoMaterialsFoundException
     * @throws NoMaterialsForThatTemperatureException
     */
    public String materialTemperatureController(int temperature) throws MaterialTypeNullException, NoMaterialsFoundException, NoMaterialsForThatTemperatureException {
        return getMaterialsScript.materialScript(temperature);
    }
}

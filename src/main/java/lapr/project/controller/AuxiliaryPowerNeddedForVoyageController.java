package lapr.project.controller;

import lapr.project.controller.App;
import lapr.project.data.DataBaseScripts.AuxiliaryPowerNeededForVoyage;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.ProportionalityConstantNullException;

import java.sql.SQLException;

public class AuxiliaryPowerNeddedForVoyageController {

    private final DatabaseConnection connection;

    public AuxiliaryPowerNeddedForVoyageController(){
        connection = App.getInstance().getDatabaseConnection();
    }

    public String AuxiliaryPowerNeddedForVoyage(int mmsi) throws SQLException, ProportionalityConstantNullException {

        AuxiliaryPowerNeededForVoyage auxiliaryPowerNeededForVoyage = new AuxiliaryPowerNeededForVoyage();

        String energyRequired = auxiliaryPowerNeededForVoyage.amountOfEnergyRequired(mmsi,connection);

        return energyRequired;
    }
}

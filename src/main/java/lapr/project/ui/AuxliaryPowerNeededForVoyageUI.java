package lapr.project.ui;

import lapr.project.controller.AuxiliaryPowerNeddedForVoyageController;
import lapr.project.shared.exceptions.ProportionalityConstantNullException;

import java.sql.SQLException;

public class AuxliaryPowerNeededForVoyageUI implements Runnable{


    public AuxliaryPowerNeededForVoyageUI(){
        //Empty Constructor
    }
    @Override
    public void run() {

        AuxiliaryPowerNeddedForVoyageController auxiliaryPowerNeddedForVoyageController = new AuxiliaryPowerNeddedForVoyageController();
        int mmsi = Utils.readIntegerFromConsole("Ship mmsi?");

        try {
            String energyRequired = auxiliaryPowerNeddedForVoyageController.AuxiliaryPowerNeddedForVoyage(mmsi);

            System.out.println(energyRequired);
        } catch (SQLException | ProportionalityConstantNullException throwables) {
            throwables.printStackTrace();
        }

    }


}

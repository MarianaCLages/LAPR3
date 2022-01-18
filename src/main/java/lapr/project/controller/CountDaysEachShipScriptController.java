package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CountDaysEachShipScript;
import lapr.project.data.DatabaseConnection;

public class CountDaysEachShipScriptController {


    private final DatabaseConnection connection;

    public CountDaysEachShipScriptController(){
        this.connection = App.getInstance().getDatabaseConnection();
    }

    public String CountDaysEachShipScript(int year){

        CountDaysEachShipScript countDaysEachShipScript = new CountDaysEachShipScript();

        return countDaysEachShipScript.CountDaysEachShip(connection,year);
    }
}

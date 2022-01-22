package lapr.project.controller;

import lapr.project.data.DataBaseScripts.CountDaysEachShipScript;
import lapr.project.data.DatabaseConnection;

public class CountDaysEachShipScriptController {

    private final DatabaseConnection connection;

    /**
     * Constructor.
     */
    public CountDaysEachShipScriptController() {
        this.connection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the number of days a ship has been idle since the beginning of the current year.
     *
     * @param year the current year
     * @return the number of days a ship has been idle since the beginning of the current year
     */
    public String CountDaysEachShipScript(int year) {
        CountDaysEachShipScript countDaysEachShipScript = new CountDaysEachShipScript();

        return countDaysEachShipScript.CountDaysEachShip(connection, year);
    }
}

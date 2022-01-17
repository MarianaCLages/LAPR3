package lapr.project.ui;

import lapr.project.controller.CountDaysEachShipScriptController;

public class CountDaysEachScriptUI implements Runnable{

    public CountDaysEachScriptUI() {
        //Empty Constructor
    }

    @Override

    public void run() {

        CountDaysEachShipScriptController countDaysEachShipScriptController = new CountDaysEachShipScriptController();

        int year = Utils.readIntegerFromConsole("Year?");

        String s = countDaysEachShipScriptController.CountDaysEachShipScript(year);

        System.out.println(s);

    }
}

package lapr.project.ui;

import lapr.project.controller.CountDaysEachShipScriptController;

public class CountDaysEachScriptUI implements Runnable{

    public CountDaysEachScriptUI() {
        //Empty Constructor
    }

    @Override

    public void run() {

        CountDaysEachShipScriptController countDaysEachShipScriptController = new CountDaysEachShipScriptController();

            String s = countDaysEachShipScriptController.CountDaysEachShipScript(2022);
            System.out.println(s);

    }
}

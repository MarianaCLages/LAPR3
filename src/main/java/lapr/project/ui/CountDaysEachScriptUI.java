package lapr.project.ui;

import lapr.project.controller.CountDaysEachShipScriptController;

import java.util.Date;

public class CountDaysEachScriptUI implements Runnable{

    public CountDaysEachScriptUI() {
        //Empty Constructor
    }

    @Override

    public void run() {

        CountDaysEachShipScriptController countDaysEachShipScriptController = new CountDaysEachShipScriptController();
            Date date = new Date();
            String s = countDaysEachShipScriptController.CountDaysEachShipScript(2022);
            System.out.println("Year: 2022\n");
            System.out.println(s);

    }
}

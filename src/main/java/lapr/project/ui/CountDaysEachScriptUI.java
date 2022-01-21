package lapr.project.ui;

import lapr.project.controller.CountDaysEachShipScriptController;

public class CountDaysEachScriptUI implements Runnable{

    public CountDaysEachScriptUI() {
        //Empty Constructor
    }

    @Override

    public void run() {

        CountDaysEachShipScriptController countDaysEachShipScriptController = new CountDaysEachShipScriptController();

        try {
            int year = Utils.readIntegerFromConsole("Please, enter the year:");

            String s = countDaysEachShipScriptController.CountDaysEachShipScript(year);

            System.out.println(s);
        }catch (NumberFormatException ex){
            System.out.println("Please input a valid year!");
        }
    }
}

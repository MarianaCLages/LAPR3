package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class FleetManagerUI implements Runnable{


    @Override
    public void run() {

        List<MenuItem> options = new ArrayList<>();

        //Sprint 4
        options.add(new MenuItem("Count idle days for each ship", new CountDaysEachScriptUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nFleet Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }

}

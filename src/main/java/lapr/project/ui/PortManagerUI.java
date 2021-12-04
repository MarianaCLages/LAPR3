package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class PortManagerUI implements Runnable {

    public void run() {

        List<MenuItem> options = new ArrayList<>();

        //SPRINT 2
        options.add(new MenuItem("Import ports from a file", new ImportPortsUI()));


        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nPort Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}

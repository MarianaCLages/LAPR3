package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class ClientUI implements Runnable {

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Pls don't bug", new ShowTextUI("OLA")));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
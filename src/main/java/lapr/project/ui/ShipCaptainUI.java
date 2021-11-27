package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class ShipCaptainUI implements Runnable {

    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("List of containers to be offloaded in the next port", new OffloadedShipsUI()));
        options.add(new MenuItem("List of containers to be loaded in the next port", new LoadedShipsUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nTraffic Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}

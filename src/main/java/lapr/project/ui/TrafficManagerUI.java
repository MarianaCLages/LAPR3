package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class TrafficManagerUI implements Runnable {

    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Import Ships from a file", new ImportShipsUI()));
        options.add(new MenuItem("Search the details of a certain Ship", new SearchShipUI()));
        options.add(new MenuItem("Positional Message of a Ship", new PositionalMessageUI()));
        options.add(new MenuItem("Get a certain ship summary", new ShipSummaryUI()));
        options.add(new MenuItem("Get the list of ships", new ListShipsUI()));
        options.add(new MenuItem("Get the top N ships in a certain interval of dates", new TopNShipsUI()));
        options.add(new MenuItem("Show all pairs of ships", new PairsOfShipsUI()));


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

package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class TrafficManagerUI implements Runnable {

    public void run() {

        List<MenuItem> options = new ArrayList<>();

        //SPRINT 1
        options.add(new MenuItem("Import ships from a file", new ImportShipsUI()));
        options.add(new MenuItem("Search the details of a certain ship", new SearchShipUI()));
        options.add(new MenuItem("Positional messages of a ship", new PositionalMessageUI()));
        options.add(new MenuItem("Get a certain ship summary", new ShipSummaryUI()));
        options.add(new MenuItem("Get the list of ships", new ListShipsUI()));
        options.add(new MenuItem("Get the top-N ships in a certain interval of dates", new TopNShipsUI()));
        options.add(new MenuItem("Show all pairs of ships", new PairsOfShipsUI()));

        //SPRINT 2
        options.add(new MenuItem("Find the closest port from a certain ship", new ClosestPortUI()));
        options.add(new MenuItem("Which ships will be available on monday next week and their location", new FindAvailableShipsUI()));

        //SPRINT 3
        options.add(new MenuItem("Build a freight network", new FreightNetworkUI()));
        options.add(new MenuItem("Colour the map using as few colours as possible", new ColourGraphUI()));

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

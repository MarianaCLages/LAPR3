package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class PortManagerUI implements Runnable {

    public void run() {

        List<MenuItem> options = new ArrayList<>();

        //SPRINT 2
        options.add(new MenuItem("Import ports from a file", new ImportPortsUI()));
        options.add(new MenuItem("Occupation of the existing resources during a month", new FacilityResourcesUI()));

        //SPRINT 3
        options.add(new MenuItem("Occupancy rate of each warehouse and an estimate of the containers leaving the warehouse during the next 30 days", new FacilityOccupationRateUI()));
        options.add(new MenuItem("Associate a container to a cargo manifest", new CreateCargoManifestContainerUI()));
        options.add(new MenuItem("Check cargo manifest map for a port", new CheckForCargoManifestMapUI()));
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

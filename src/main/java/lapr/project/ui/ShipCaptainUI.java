package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class ShipCaptainUI implements Runnable {

    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("List of containers to be offloaded in the next port", new OffloadedContainersUI()));
        options.add(new MenuItem("List of containers to be loaded in the next port", new LoadedContainersUI()));
        options.add(new MenuItem("Cargo manifests in a certain year and average number of containers per manifest", new AverageCargoByYearUI()));
        options.add(new MenuItem("Occupancy rate of a ship by cargo manifest ID", new OccupancyRateGivenCargoManifestIDUI()));
        options.add(new MenuItem("Occupancy rate of a ship by cargo manifest date", new OccupancyRateGivenCargoManifestDateUI()));
        options.add(new MenuItem("Get Audit Trail", new GetAuditTrailUI()));
        options.add(new MenuItem("Auxiliary Power Equipment Needed", new AuxiliaryPowerNeededForVoyageUI()));
        options.add(new MenuItem("Determinate the center of a ship",new CalculateCenterUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nShip Captain Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}

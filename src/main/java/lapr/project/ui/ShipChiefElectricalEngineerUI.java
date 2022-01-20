package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class ShipChiefElectricalEngineerUI implements Runnable {


    public void run() {

        List<MenuItem> options = new ArrayList<>();

        // SPRINT 3
        options.add(new MenuItem("Materials for the temperature", new MaterialTemperatureUI()));
        options.add(new MenuItem("Container thermal resistance values", new ThermalResistanceUI()));

        // SPRINT 4
        options.add(new MenuItem("Know the total energy to be supplied to a set of containers in a trip", new TotalEnergySuppliedUI()));
        options.add(new MenuItem("Get energy consumption with different section", new EnergyNeededToSupplyUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nShip Chief Electrical Engineer Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);

    }
}

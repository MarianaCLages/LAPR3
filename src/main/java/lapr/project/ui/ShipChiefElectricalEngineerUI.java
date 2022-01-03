package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class ShipChiefElectricalEngineerUI implements Runnable{


    public void run(){

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Materials for the temperature", new MaterialTemperatureUI()));
        options.add(new MenuItem("Container thermal resistance values", new ThermalResistanceUI()));

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

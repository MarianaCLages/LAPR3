package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class ShipEletricalEngineerUI implements Runnable{


    public void run(){

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Materials for the temperature", new MaterialTemperatureUI()));
        options.add(new MenuItem("Container Thermal Value", new ThermalUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nShip Eletrical Engineer Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);

    }
}

package lapr.project.ui;

import lapr.project.controller.MaterialTemperatureController;
import lapr.project.shared.exceptions.MaterialTypeNullException;
import lapr.project.shared.exceptions.NoMaterialsForThatTemperatureException;
import lapr.project.shared.exceptions.NoMaterialsFoundException;

public class MaterialTemperatureUI implements Runnable {


    private final MaterialTemperatureController materialTemperatureController;

    public MaterialTemperatureUI() {
        materialTemperatureController = new MaterialTemperatureController();
    }

    @Override
    public void run() {

        int temperature;

        do {
            try {
                temperature = Utils.readIntegerFromConsole("Please enter the desired temperature.");
            } catch (IllegalArgumentException ex2) {
                System.out.println("Please enter a number! (Don't enter a letter nor symbol!)");
                temperature = -99;
            }
        } while (temperature == -99);

        try {
            System.out.println(materialTemperatureController.materialTemperatureController(temperature));
        } catch (MaterialTypeNullException | NoMaterialsFoundException | NoMaterialsForThatTemperatureException ex1) {
            System.out.println(ex1.getMessage());
        }
    }
}

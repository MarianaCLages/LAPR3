package lapr.project.ui;

import lapr.project.controller.ThermalResistanceController;
import lapr.project.shared.exceptions.ProportionalityConstantNullException;

public class ThermalResistanceUI implements Runnable {

    private final ThermalResistanceController thermalResistanceController;

    public ThermalResistanceUI() {
        this.thermalResistanceController = new ThermalResistanceController();
    }

    @Override
    public void run() {

        int id;

        do {
            try {
                id = Utils.readIntegerFromConsole("Please enter the container ID:");
                if (id < 0)
                    throw new NumberFormatException("Please enter a positive number! There is no negative ID!");
            } catch (NumberFormatException ex1) {
                System.out.println(ex1.getMessage());
                id = -1;
            } catch (IllegalArgumentException ex2) {
                System.out.println("Please enter a number! (Don't enter a letter nor symbol!)");
                id = -1;
            }
        } while (id == -1);

        try {
            System.out.println(thermalResistanceController.getMaterialThermalResistance(id));
        } catch (ProportionalityConstantNullException ex1) {
            System.out.println(ex1.getMessage());
        }
    }
}

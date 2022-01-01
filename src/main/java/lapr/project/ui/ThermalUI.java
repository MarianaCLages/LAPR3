package lapr.project.ui;

import lapr.project.controller.ThermalController;
import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.ProportionalityConstantNullException;

import java.io.IOException;
import java.sql.SQLException;

public class ThermalUI implements Runnable {

    private final ThermalController thermalController;

    public ThermalUI() {
        this.thermalController = new ThermalController();
    }

    @Override
    public void run() {

        int id;

        do {
            try {
                id = Utils.readIntegerFromConsole("Container ID:");
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
            System.out.println(thermalController.getMaterialThermalResistance(id));
        } catch (ProportionalityConstantNullException ex1) {
            System.out.println(ex1.getMessage());
        }
    }
}

package lapr.project.ui;

import lapr.project.controller.CalculateCenterController;

import java.text.DecimalFormat;
import java.util.List;

public class CalculateCenterUI implements Runnable {

    private final CalculateCenterController calculateCenterController;
    private final DecimalFormat df;
    private StringBuilder sb;

    public CalculateCenterUI() {
        calculateCenterController = new CalculateCenterController();
        df = new DecimalFormat("0.00");
        sb = new StringBuilder();
    }

    @Override
    public void run() {
        int vesselType = 0;

        do {
            try {
                vesselType = Utils.readIntegerFromConsole("\nPlease insert ship's vessel type (Available: 74,72,71): ");

                if (vesselType != 72 && vesselType != 74 && vesselType != 71) {
                    throw new NullPointerException("\nPlease enter one of the above options!!");
                }

                List<Double> doubleList = calculateCenterController.calculateCenterOfMass(vesselType);

                sb.append("\nVessel Type: " + vesselType).append("\nTotal center is (x: ").append(df.format(doubleList.get(0))).append("; y: ").append(df.format(doubleList.get(1))).append("; z: ").append(df.format(doubleList.get(2))).append(")\n");

                System.out.println(sb.toString());
                System.out.println("\nOperation Success!!\n");

            } catch (IllegalArgumentException ex1) {
                System.out.println("\nPlease enter a valid number! (Notice: Don't type letters/symbols or even large numbers!)");
                vesselType = 0;
            } catch (Exception ex2) {
                System.out.println(ex2.getMessage());
                vesselType = 0;
            }
        } while (vesselType != 72 && vesselType != 74 && vesselType != 71);

        sb = new StringBuilder();

    }
}
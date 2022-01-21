package lapr.project.ui;

import lapr.project.controller.TotalEnergySuppliedController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TotalEnergySuppliedUI implements Runnable {

    private final TotalEnergySuppliedController controller;

    public TotalEnergySuppliedUI() {
        this.controller = new TotalEnergySuppliedController();
    }

    public void run() {
        List<String> optionList = new ArrayList<>();

        optionList.add("-5 ºC");
        optionList.add("7 ºC");
        StringBuilder sb = new StringBuilder();

        try {
            int i = 0;

            int numberOfContainers = Utils.readIntegerFromConsole("Please enter the number of containers:");

            String index = (String) Utils.showAndSelectOne(optionList, "Please select the temperature of the containers:");
            if (index == null) {
                return;
            }

            do {
                try {
                    i = Utils.readIntegerFromConsole("Please enter the number of sections with different temperatures during the trip:");
                    if (i == 0) {
                        System.out.println("There must be at least 1 section!");
                    }
                } catch (IllegalArgumentException ex2) {
                    System.out.println("Please enter a number! (Don't enter a letter nor a symbol!)");
                    i = 0;
                }
            } while (i == 0);

            for (int j = 0; j < i; j++) {
                double temperature = 0;
                int duration = 0;

                do {
                    try {
                        temperature = Double.parseDouble(Utils.readLineFromConsole("Please enter the temperature of the section: " + (j + 1)));

                    } catch (NumberFormatException ex2) {
                        System.out.println("Please enter a number! (Don't enter a letter nor symbol!)");
                        temperature = -99;
                    }
                } while (temperature == -99);

                do {
                    try {
                        duration = duration + Utils.readIntegerFromConsole("Please enter the hour duration of the section: " + (j + 1)) * 3600;
                        duration = duration + Utils.readIntegerFromConsole("Please enter the minute duration of the section: " + (j + 1)) * 60;
                        duration = duration + Utils.readIntegerFromConsole("Please enter the second duration of the section: " + (j + 1));
                        System.out.println();

                    } catch (NumberFormatException ex2) {
                        System.out.println("Please enter a number! (Don't enter a letter nor symbol!)");
                        duration = -99;
                    }
                } while (duration == -99);

                if (Objects.requireNonNull(index).equals("-5 ºC")) {
                    sb.append("\n");
                    sb.append("Section ").append(j + 1).append("\n");
                    sb.append(controller.calculationToMinus5(numberOfContainers, temperature, duration));
                } else {
                    sb.append("\n");
                    sb.append("Section ").append(j + 1).append("\n");
                    sb.append(controller.calculationTo7(numberOfContainers, temperature, duration));
                }
                sb.append("\n");
            }

            System.out.println(sb);
            System.out.println("\nOperation Success!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("\nOperation failed! Please try again.");
        }
    }
}

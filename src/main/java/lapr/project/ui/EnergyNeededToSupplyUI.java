package lapr.project.ui;

import lapr.project.controller.EnergyNeededToSupplyController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class EnergyNeededToSupplyUI implements Runnable {
    private final EnergyNeededToSupplyController controller;


    public EnergyNeededToSupplyUI() {
        this.controller = new EnergyNeededToSupplyController();
    }

    @Override
    public void run() {
        List<String> optionList = new ArrayList<>();

        optionList.add("-5 ºC");
        optionList.add("7 ºC");
        LinkedHashMap<Integer, Double> section = new LinkedHashMap<>();
        try {
            int i = 0;

            String index = (String) Utils.showAndSelectOne(optionList, "Please select the temperature of the containers");
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
                    System.out.println("Please enter a number! (Don't enter a letter nor symbol!)");
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
                        System.out.println("Please enter a number! (Don't enter a letter nor a symbol!)");
                        temperature = -99;
                    }
                } while (temperature == -99);

                do {
                    try {
                        duration = duration + Utils.readIntegerFromConsole("Please enter hour duration of the section ." + (j + 1)) * 3600;
                        duration = duration + Utils.readIntegerFromConsole("Please enter minute duration of the section ." + (j + 1)) * 60;
                        duration = duration + Utils.readIntegerFromConsole("Please enter second duration of the section ." + (j + 1));

                    } catch (NumberFormatException ex2) {
                        System.out.println("Please enter a number! (Don't enter a letter nor a symbol!)");
                        duration = -99;
                    }
                } while (duration == -99);

                section.put(duration, temperature);
            }

            int front = 0;
            do {
                try {
                    front = Utils.readIntegerFromConsole("Please enter the number of front faces exposed to the ambient:");
                } catch (IllegalArgumentException ex2) {
                    System.out.println("Please enter a number! (Don't enter a letter nor a symbol!)");
                    front = -99;
                }
            } while (front == -99);


            int side = 0;
            do {
                try {
                    side = Utils.readIntegerFromConsole("Please enter the number of side faces exposed to the ambient:");
                } catch (IllegalArgumentException ex2) {
                    System.out.println("Please enter a number! (Don't enter a letter nor a symbol!)");
                    side = -99;
                }
            } while (side == -99);

            int top = 0;
            do {
                try {
                    top = Utils.readIntegerFromConsole("Please enter the number of top faces exposed to the ambient:");
                } catch (IllegalArgumentException ex2) {
                    System.out.println("Please enter a number! (Don't enter a letter nor a symbol!)");
                    top = -99;
                }
            } while (top == -99);

            if (Objects.requireNonNull(index).equals("-5 ºC")) {
                System.out.println(controller.calculateToMinus5(section, front, side, top));
            } else {
                System.out.println(controller.calculateTo7(section, front, side, top));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

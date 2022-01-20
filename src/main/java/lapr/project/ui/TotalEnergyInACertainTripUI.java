package lapr.project.ui;

import lapr.project.controller.TotalEnergyInACertainTripController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TotalEnergyInACertainTripUI implements Runnable {

    private final TotalEnergyInACertainTripController controller;

    public TotalEnergyInACertainTripUI() {
        this.controller = new TotalEnergyInACertainTripController();
    }

    public void run() {
        List<String> optionList = new ArrayList<>();
        optionList.add("-5 ºC");
        optionList.add("7 ºC");
        StringBuilder sb = new StringBuilder();

        String option = null;

        try {
            System.out.println("\n\nAll valid trips: \n");
            System.out.println(controller.getAllTripList());

            do {
                try {
                    option = Utils.readLineFromConsole("Please enter one of the valid trip ID (Notice enter the TRIP ID not the trip number!!)");

                    if (option == null || option.trim().equals("")) {
                        throw new IllegalArgumentException("Invalid Trip! Please enter a valid trip (See the trip list above)");
                    }

                    if (!controller.verifyTrip(option)) {
                        throw new IllegalArgumentException("Invalid Trip! Please enter a valid trip (See the trip list above)");
                    }

                } catch (SQLException ex1) {
                    System.out.println("Please enter a valid trip! (Notice: enter a number not a invalid character or a set of invalid characters!) \n");
                    option = null;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    option = null;
                }

            } while (option == null);

            String index = (String) Utils.showAndSelectOne(optionList, "\nPlease select the temperature of the containers:\n");

            if (index == null) {
                return;
            }

            if (Objects.requireNonNull(index).equals("-5 ºC")) {
                sb.append("\n");
                sb.append(controller.calculationToMinus5Degrees());
            } else {
                sb.append("\n");
                sb.append(controller.calculationTo7Degrees());
            }
            sb.append("\n");

            System.out.println(sb.toString());
            System.out.println("\nOperation Success!");

        } catch (Exception e) {
            System.out.println(sb);
        }
    }
}
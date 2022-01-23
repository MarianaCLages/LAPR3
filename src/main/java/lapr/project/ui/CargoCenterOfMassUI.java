package lapr.project.ui;

import lapr.project.controller.CargoCenterOfMassController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CargoCenterOfMassUI implements Runnable {

    private final CargoCenterOfMassController controller;
    private final DecimalFormat df;

    public CargoCenterOfMassUI() {
        controller = new CargoCenterOfMassController();
        df = new DecimalFormat("0.00");
    }

    @Override
    public void run() {

        List<Double> listOfContainersPerN = new ArrayList<>();
        String option = "Length";
        String optionCoord = "x";
        StringBuilder sb = new StringBuilder();
        sb.append("\nVessel Type: 71").append("\nTotal center of the cargo (given the containers) is (");
        int nContainers = 0;
        double position = 0;

        do {
            try {
                nContainers = Utils.readIntegerFromConsole("\nPlease enter the maximum number of containers that you wish to have (by coordinate) inside the cargo manifest ");

                if (nContainers < 0) {
                    throw new NullPointerException("\nYou can not enter negative values! Or even a nContainers that surpasses 100 (According to the acceptance criteira)!");
                }

            } catch (IllegalArgumentException exception) {
                System.out.println("Please do not enter a letter of a symbol! (Notice we are reading a integer number for the max containers!)");
                nContainers = 0;
            } catch (NullPointerException ex2) {
                System.out.println("There was an error while trying to read the coordinates! Please try again!");
                nContainers = 0;
            } catch (Exception ex1) {
                System.out.println("There was an error while trying to read the coordinates! Please try again!");
                nContainers = 0;
            }

        } while (nContainers == 0);

        for (int j = 0; j < 3; j++) {
            if (j == 1) option = "Width";
            else if (j == 2) option = "Height";

            for (int i = 0; i < nContainers; i++) {
                try {
                    position = Utils.readIntegerFromConsole("\nPlease enter the container that you wish to have for the cargo manifest ( " + option + "):");

                    listOfContainersPerN.add(position);

                } catch (IllegalArgumentException | NullPointerException exception) {
                    System.out.println(exception.getMessage());
                } catch (Exception ex) {
                    System.out.println("\nThere was an error while trying to read the coordinates! Please try again!");
                    nContainers = 0;

                }

            }

            if (j == 1) optionCoord = "y";
            else if (j == 2) optionCoord = "z";
            else optionCoord = "x";
            sb.append(" " + optionCoord + ":");
            sb.append(df.format(controller.calculateCenter(listOfContainersPerN)));
            if (j != 2) {
                sb.append(",");
            } else {
                sb.append(")");
            }
            listOfContainersPerN = new ArrayList<>();
        }


        try {
            System.out.println(sb.toString());
        } catch (NullPointerException e) {
            System.out.println("\nThere was en error calculating the center of the mass! Please try again!");
            System.out.println("\nOperation failed!!");
        }

        sb = new StringBuilder();

    }
}

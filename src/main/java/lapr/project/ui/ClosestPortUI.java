package lapr.project.ui;

import lapr.project.controller.ClosestPortController;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClosestPortUI implements Runnable {

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

    private final ClosestPortController closestPortController = new ClosestPortController();

    public ClosestPortUI() {
        //
    }

    @Override
    public void run() {

        String date = null;
        LocalDateTime dateS = null;

        String callSign = Utils.readLineFromConsole("Please enter the callSign");

        do {
            try {

                date = Utils.readLineFromConsole("Please, insert the desired date (dd-MM-yy HH:mm): ");
                dateS = LocalDateTime.parse(date, dateFormat);

            } catch (IllegalArgumentException | DateTimeParseException e) {
                date = null;
                System.out.println("Invalid date! Please enter another one! \n");

            }

        } while (date == null);

        try {

            closestPortController.getNearestPortByCallSign(callSign, dateS);

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid Position! Impossible to get the nearest port from a non exist position");

        }

    }

}
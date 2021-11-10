package lapr.project.ui;

import lapr.project.controller.PositionalMessageController;
import lapr.project.utils.Utils;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PositionalMessageUI implements Runnable {

    private PositionalMessageController positionalMessageController = new PositionalMessageController();


    public void run() {


        int mmsi;
        Scanner read = new Scanner(System.in);
        String date;


        System.out.println("Which ship will you read the positional messages?");
        mmsi = read.nextInt();


        System.out.println("Inital date? yyyy-mm-dd HH:mm:ss");
        date = Utils.readLineFromConsole("");


        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datei = LocalDateTime.parse(date, dateFormat);


        System.out.println("Final date? yyyy-mm-dd HH:mm:ss");
        date = Utils.readLineFromConsole("");
        try {

            LocalDateTime datef = LocalDateTime.parse(date, dateFormat);


        if (!positionalMessageController.getPositionalMessages(mmsi, datei, datef)) {
            System.out.println(mmsi + " doesn't exist!");
        }
        }catch (DateTimeParseException e){
            System.out.println("Invalid date");
        }

    }

}

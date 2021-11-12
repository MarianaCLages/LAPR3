package lapr.project.ui;

import lapr.project.controller.PositionalMessageController;

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

        try {
        System.out.println("Inital date? yyyy-mm-dd HH:mm:ss");
        date = Utils.readLineFromConsole("");


        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datei = LocalDateTime.parse(date, dateFormat);


        System.out.println("Final date? yyyy-mm-dd HH:mm:ss");
        date = Utils.readLineFromConsole("");


            LocalDateTime datef = LocalDateTime.parse(date, dateFormat);

        try{

            String posMessage = positionalMessageController.getPositionalMessages(mmsi,datei,datef);

             if(posMessage != null){
                System.out.println(posMessage);
            }

        }
        catch (NullPointerException ex){
            System.out.println("Ship doesn't exist or doesn't have positions in that period of time!");}


        }catch (DateTimeParseException e){
            System.out.println("Invalid date");
        }

    }

}

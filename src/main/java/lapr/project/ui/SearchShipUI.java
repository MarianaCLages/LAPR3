package lapr.project.ui;

import lapr.project.controller.SearchShipController;
import lapr.project.model.Ship;
import lapr.project.ui.Utils;


import java.util.Scanner;

public class SearchShipUI implements Runnable {

    private final SearchShipController ctrl = new SearchShipController();

    public SearchShipUI(){

    }

    @Override
    public void run() {

        Scanner in = new Scanner(System.in);
        Ship result = null;
        int op;
        String parameter;

        do {
            try {
                System.out.print(
                        "Select which parameter you wish to search a ship by: \n" +
                                "1 - MMSI\n" + "2 - IMO\n" + "3 - Call Sign\n"+ "\n" + "0 - cancel" );
                op = Utils.readIntegerFromConsole(in.nextLine().trim());
                if (op > 3) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid option!");
                op = 0;
            }
        } while (op == 0);

        switch (op){
            case 1:
                do {
                    try {
                        op = Utils.readIntegerFromConsole("Enter MMSI: \n");
                    } catch (NumberFormatException err) {
                        System.out.println("Please enter a valid MMSI!");
                        op = 0;
                    }
                } while (op == 0);
                try {
                    result = ctrl.searchShipByMMSI(op);
                    break;
                } catch (IllegalArgumentException err1) {
                    System.out.println(err1.getMessage());
                    break;
                } catch (NullPointerException err2){
                    System.out.println("Failed to find any ships with those parameters!");
                    break;
                }
            case 2:
                do {
                    try {
                        parameter = Utils.readLineFromConsole("Enter IMO: \n");
                    } catch (NumberFormatException err) {
                        System.out.println("Please enter a valid IMO!");
                        parameter = null;
                    }
                } while (parameter == null);
                try {
                    result = ctrl.searchShipByIMO(parameter);
                    break;
                } catch (IllegalArgumentException err1) {
                    System.out.println(err1.getMessage());
                    break;
                } catch (NullPointerException err2){
                    System.out.println("Failed to find any ships with those parameters!");
                    break;
                }
            case 3:
                do {
                    try {
                        parameter = Utils.readLineFromConsole("Enter Call Sign: \n");
                    } catch (NumberFormatException err) {
                        System.out.println("Please enter a valid Call Sign!");
                        parameter = null;
                    }
                } while (parameter == null);
                try {
                    result = ctrl.searchShipByCallSign(parameter);
                    break;
                } catch (IllegalArgumentException err1) {
                    System.out.println(err1.getMessage());
                    break;
                } catch (NullPointerException err2){
                    System.out.println("Failed to find any ships with those parameters!");
                    break;
                }
            default:
                System.out.println("Invalid input option!");

        }

        if (result == null) System.out.println("Failed to find any ships with those parameters!");
        else {
            System.out.println(result);
        }
    }
}


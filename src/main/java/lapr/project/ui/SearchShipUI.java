package lapr.project.ui;

import lapr.project.controller.SearchShipController;
import lapr.project.model.Ship;
import lapr.project.ui.Utils;


import java.util.Scanner;

public class SearchShipUI implements Runnable {

    private SearchShipController ctrl = new SearchShipController();

    @Override
    public void run() {

        Scanner in = new Scanner(System.in);
        Ship result = null;
        int op = 0;
        String para;

        System.out.print(
                "Select which parameter you wish to search a ship by: \n" +
                        "1 - MMSI\n" + "2 - IMO\n" + "3 - Call Sign\n"+ "\n" + "0 - cancel" );
        op = Utils.readIntegerFromConsole(in.nextLine().trim());

        switch (op){
            case 1:
                System.out.print("MMSI: \n");
                op = in.nextInt();
                result = ctrl.searchShipByMMSI(op);
                break;
            case 2:
                System.out.print("IMO: \n");
                para = in.nextLine();
                result = ctrl.searchShipByIMO(para);
                break;
            case 3:
                System.out.print("Call Sign: \n");
                para = in.nextLine();
                result = ctrl.searchShipByCallSign(para);
                break;
            default:
                System.out.println("Invalid input option!");

        }

        if (result == null) System.out.println("Failed to find any ships with those parameters!");
        else {
            System.out.println(result);
        }
    }
}


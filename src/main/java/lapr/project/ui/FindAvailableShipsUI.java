package lapr.project.ui;

import lapr.project.controller.FindAvailableShipsController;
import lapr.project.model.Ship;

import java.util.Date;
import java.util.List;

public class FindAvailableShipsUI implements Runnable{

    FindAvailableShipsController ctrl = new FindAvailableShipsController();
    private Date cDate = new Date();
    private Date pDate = cDate;


    @Override
    public void run() {

        int i = 1;

        do{                                         //loop to get date of next desired day of the week
            pDate.setDate(cDate.getDate() + i);
            i++;
        } while (pDate.getDay() != 1);              //change value of '1' to change day of the week

        try {
            List<Ship> rShip = ctrl.getAvailableShipsAtDate(pDate);

            if(rShip.isEmpty()){
                throw new NullPointerException("There are no available ships at " + pDate.toString() + "!!");
            } else {
                System.out.println("The following ships will be available at " + pDate.toString() + " : ");
                for (Ship s : rShip){
                    System.out.println(s.getCallSign());
                }
            }

        } catch (NullPointerException e) {
            System.out.print("Operation Failed!\n" +
                    "Try changing the day of the week.\n");
        }
    }
}

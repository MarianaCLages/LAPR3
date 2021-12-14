package lapr.project.ui;

import lapr.project.controller.FindAvailableShipsController;
import lapr.project.model.Ship;

import java.util.List;

public class FindAvailableShipsUI implements Runnable {

    FindAvailableShipsController ctrl = new FindAvailableShipsController();

    @Override
    public void run() {

        try {
            List<Ship> rShip = ctrl.getAvailableShips();

            if (rShip.isEmpty()) {
                throw new NullPointerException("There are no available ships on next Monday!");
            } else {
                System.out.println("\nThe following ships will be available on next Monday: \n");
                for (Ship s : rShip) {
                    System.out.println(s.getCallSign());
                }
            }

        } catch (NullPointerException e) {
            System.out.print("There are no available ships on next Monday!");
        }
    }
}

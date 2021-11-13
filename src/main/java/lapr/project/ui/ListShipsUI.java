package lapr.project.ui;

import lapr.project.controller.ListShipsController;

public class ListShipsUI implements Runnable {
    ListShipsController ctrl;

    public ListShipsUI() {
        ctrl = new ListShipsController();
    }

    public void run() {
        try {
            ctrl.getShipList();
        } catch (Exception e) {
            System.out.println("Ship list empty!");
            return;
        }

        System.out.println("### SHIP LIST ###");
        System.out.println(ctrl.getShipListDTO1());
    }
}

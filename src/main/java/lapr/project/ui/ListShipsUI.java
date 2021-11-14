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
        if(ctrl.getShipStore().transformAVLintoListMMSI().size()>10) System.out.println("(This operation might take a while!)\n\n");
        System.out.println(ctrl.getShipListDTO());
    }
}

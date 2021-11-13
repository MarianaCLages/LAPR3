package lapr.project.ui;

import lapr.project.controller.PairsOfShipsController;

public class PairsOfShipsUI implements Runnable {

    public PairsOfShipsUI() {
        //
    }

    private final PairsOfShipsController pairsOfShipsController = new PairsOfShipsController();

    @Override
    public void run() {

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       #PAIRS OF SHIPS#");

        if(pairsOfShipsController.getSize()>5) System.out.println("(This operation might take a while!)\n\n");

        System.out.println(pairsOfShipsController.getPairs());

    }

}

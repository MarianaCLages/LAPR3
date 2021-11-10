package lapr.project.ui;

import lapr.project.controller.PairsOfShipsController;

public class PairsOfShipsUI implements Runnable {

    public PairsOfShipsUI() {
        //
    }

    private final PairsOfShipsController pairsOfShipsController = new PairsOfShipsController();

    @Override
    public void run() {

        System.out.println(pairsOfShipsController.getPairs());

    }

}

package lapr.project.ui;


import lapr.project.controller.ShipWaterPhysicsController;
import lapr.project.data.CargoManifest;

import java.util.List;

public class ShipWaterPhysicsUI implements Runnable {

    ShipWaterPhysicsController ctrl = new ShipWaterPhysicsController();

    @Override
    public void run() {

        String shipCallSign = Utils.readLineFromConsole("Enter the ship's Call Sign: ");
        ctrl.getShip(shipCallSign);

        List<CargoManifest> lCargoManifest = ctrl.getShipCargoManifests();

        CargoManifest  cargoManifest = (CargoManifest) Utils.selectsObject(lCargoManifest);

        ctrl.calculateData(cargoManifest);

        System.out.println(ctrl.toString());


    }
}

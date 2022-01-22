package lapr.project.ui;

import lapr.project.controller.ShipWaterPhysicsController;
import lapr.project.model.Ship;
import lapr.project.shared.exceptions.InvalidDataException;

import java.sql.SQLException;
import java.util.List;

public class ShipWaterPhysicsUI implements Runnable {

    ShipWaterPhysicsController ctrl = new ShipWaterPhysicsController();

    @Override
    public void run() {

        List<Ship> lShip = null;
        List<String> lCargoManifest = null;

        try {
            lShip = this.ctrl.getShiplist();
            Ship ship = (Ship) Utils.showAndSelectOne(lShip, "Select one ship: ");
            lCargoManifest = this.ctrl.getShipCargoManifests(ship.getMmsi());
            String cargoManifest = (String) Utils.showAndSelectOne(lCargoManifest, "Select one: ");
            this.ctrl.calculateData(cargoManifest, ship);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InvalidDataException e) {

        }

        System.out.println(this.ctrl.SummaryString());


    }
}

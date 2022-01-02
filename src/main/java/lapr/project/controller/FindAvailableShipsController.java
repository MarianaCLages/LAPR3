package lapr.project.controller;

import lapr.project.data.DataBaseScripts.AvailableShipsOnMondayScript;
import lapr.project.data.ShipStoreData;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

import java.util.ArrayList;
import java.util.List;

public class FindAvailableShipsController {

    private final ShipStore shipStore;
    private final ShipStoreData shipStoreData;
    AvailableShipsOnMondayScript script = new AvailableShipsOnMondayScript(App.getInstance().getDatabaseConnection());

    /**
     * Constructor
     */
    public FindAvailableShipsController() {

        Company company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
        shipStoreData = company.getShipStoreData();

    }

    /**
     * Gets all available ships on next monday
     *
     * @return list of available ships
     */
    public List<Ship> getAvailableShips() {
        ArrayList<String> lShipID = script.get();
        List<Ship> rlShip = null;

        for (String shipID : lShipID) {
            rlShip.add(shipStore.getShipByCallSign(shipID));
        }

        return rlShip;
    }
}

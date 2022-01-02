package lapr.project.controller;

import lapr.project.data.CargoManifestStoreData;
import lapr.project.data.DataBaseScripts.GetClientsContainerScript;
import lapr.project.model.*;

import java.util.ArrayList;

public class SearchContainerLocationForClientController {

    private final CargoManifestStoreData cargoManifestStore;
    private final GetClientsContainerScript script;

    /**
     * Constructor
     */
    public SearchContainerLocationForClientController() {
        Company company = App.getInstance().getCompany();
        this.cargoManifestStore = company.getCargoManifestStoreData();
        script = new GetClientsContainerScript(App.getInstance().getDatabaseConnection());
    }

    /**
     * Gets a list of containers from a certain client
     *
     * @param clientID client's ID
     * @return list of containers
     */
    public ArrayList<String> getClientContainers(String clientID) {
        return script.getClientContainers(clientID);
    }

    /**
     * Finds the last cargo manifest that the desired container was present
     *
     * @param rContainer Container
     * @return Last cargo manifest in which the container was present
     */
    public CargoManifest findContainerVessel(Container rContainer) {
        CargoManifest rCargoManifest = null;
        for (CargoManifest cm : cargoManifestStore.getListCargoManifest(App.getInstance().getDatabaseConnection())) {
            if (cm.getLoaded().find(rContainer).equals(rContainer) && rCargoManifest == (null)) {
                rCargoManifest = cm;
            }
        }
        return rCargoManifest;
    }

    /**
     * Returns a string with the information of the last coordinates of the container's vessel (Ship / Port)
     *
     * @param rCargoManifest Cargo manifest where the container is loaded
     * @return information on the last coordinates of the container in a String
     */
    public String printLocation(CargoManifest rCargoManifest) {
        if (rCargoManifest.getInTransport()) {
            return ("Container is aboard the: " + rCargoManifest.getShip().getCallSign() + " \n" +
                    "Coordinates: \n" +
                    "   Lat : " + rCargoManifest.getShip().getSmallestPosition().getLatitude() + "\n" +
                    "   Long: " + rCargoManifest.getShip().getSmallestPosition().getLongitude() + "\n");
        } else {
            return ("Container is currently stored in: " + rCargoManifest.getPort().getIdentification() + " \n" +
                    "Coordinates: \n" +
                    "   Lat : " + rCargoManifest.getPort().getLocation().getLatitude() + "\n" +
                    "   Long: " + rCargoManifest.getPort().getLocation().getLongitude() + "\n");
        }
    }
}

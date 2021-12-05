package lapr.project.controller;

import lapr.project.data.CargoManifestStoreData;
import lapr.project.data.ContainerStoreData;
import lapr.project.data.DataBaseScripts.GetClientsContainerScript;
import lapr.project.model.*;
import lapr.project.model.stores.CargoManifestStore;

import java.util.ArrayList;

public class SearchContainerLocationForClientController {

    private final CargoManifestStoreData cargoManifestStore;
    private GetClientsContainerScript script = new GetClientsContainerScript(App.getInstance().getDatabaseConnection());

    public SearchContainerLocationForClientController() {
        Company company = App.getInstance().getCompany();
        this.cargoManifestStore = company.getCargoManifestStoreData();
    }

    public ArrayList<String> getClientContainers(String clientID) {
        ArrayList<String> lResult = script.getClientContainers(clientID);

        return lResult;
    }

    public CargoManifest findContainerVessel(Container rContainer) {
        CargoManifest rCargoManifest = null;
        for(CargoManifest cm: cargoManifestStore.getListCargoManifest(App.getInstance().getDatabaseConnection())){
            if (cm.getLoaded().find(rContainer).equals(rContainer) && rCargoManifest.equals(null)){
                rCargoManifest = cm;
            } else if(cm.getDate().after(rCargoManifest.getDate())){
                rCargoManifest = cm;
            }
        }
        return rCargoManifest;
    }
}

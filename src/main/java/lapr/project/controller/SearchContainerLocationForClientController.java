package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.stores.CargoManifestStore;
import lapr.project.model.stores.ContainerStore;
import lapr.project.utils.auth.domain.store.UserStore;

import java.util.List;

public class SearchContainerLocationForClientController {

    private final UserStore userStore;
    private final ContainerStore containerStore;
    private final CargoManifestStore cargoManifestStore;

    public SearchContainerLocationForClientController() {
        Company company = App.getInstance().getCompany();
        this.userStore = company.getUserStore();
        this.containerStore = company.getContainerStore();
        this.cargoManifestStore = company.getCargoManifestStore();
    }

    public List<Container> getClientContainers(Client client) { //por enquanto parametro client nao faz nada
        List<Container> lResult = null;
        for(Container c : containerStore.containerByAVL.inOrder()){
            //condicao para filtrar containers do client
        }

        return lResult;
    }

    public CargoManifest findContainerVessel(Container rContainer) {
        for(CargoManifest cm: cargoManifestStore.getCargoManifestByAVL().inOrder()){
            if (cm.getLoaded().find(rContainer).equals(rContainer)){
                return cm;
            }
        }
        return null;
    }

    /*public Client getClientFromClientID(String clientID){
        return usrStore.getById(clientID);
    }*/
}

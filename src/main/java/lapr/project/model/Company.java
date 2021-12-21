package lapr.project.model;

import lapr.project.data.CargoManifestStoreData;
import lapr.project.data.ContainerStoreData;
//import lapr.project.data.PortStoreData;
import lapr.project.data.PortStoreData;
import lapr.project.data.ShipStoreData;
import lapr.project.model.stores.CargoManifestStore;
import lapr.project.model.stores.ContainerStore;
import lapr.project.model.stores.PortStore;
import lapr.project.model.stores.ShipStore;
import lapr.project.utils.auth.AuthFacade;
import lapr.project.utils.auth.domain.store.OrgRoleStore;
import lapr.project.utils.auth.domain.store.UserStore;
import org.apache.commons.lang3.StringUtils;

public class Company {

    private final AuthFacade authFacade;
    private final OrgRoleStore orgRoleStore;
    private final ShipStore shipStore;
    private final PortStore portStore;
    private final ContainerStore containerStore;
    private final CargoManifestStore cargoManifestStore;
    private final UserStore userStore;
    private final ShipStoreData shipStoreData;
    private final PortStoreData portStoreData;
    private final CargoManifestStoreData cargoManifestStoreData;
    private final ContainerStoreData containerStoreData;

    /**
     * Constructor.
     *
     * @param designation the company's designation
     */
    public Company(String designation) {
        if (StringUtils.isBlank(designation)) {
            throw new IllegalArgumentException("Designation cannot be blank.");
        }
        this.authFacade = new AuthFacade();
        this.shipStore = new ShipStore();
        this.orgRoleStore = new OrgRoleStore();
        this.portStore = new PortStore();
        this.containerStore = new ContainerStore();
        this.cargoManifestStore = new CargoManifestStore();
        this.userStore = new UserStore();
        this.shipStoreData = new ShipStoreData();
        this.portStoreData = new PortStoreData();
        this.cargoManifestStoreData = new CargoManifestStoreData();
        this.containerStoreData = new ContainerStoreData();
    }

    /**
     * Gets the Auth Facade.
     *
     * @return the Auth Facade
     */
    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * Gets the Ship Store.
     *
     * @return the Ship Store
     */
    public ShipStore getShipStore() {
        return this.shipStore;
    }

    /**
     * Gets the Port Store.
     *
     * @return the Port Store
     */
    public PortStore getPortStore() {
        return this.portStore;
    }

    /**
     * Gets the Container Store.
     *
     * @return the Container Store
     */
    public ContainerStore getContainerStore() {
        return this.containerStore;
    }

    /**
     * Gets the CargoManifest Store.
     *
     * @return the CargoManifest Store
     */
    public CargoManifestStore getCargoManifestStore() {
        return this.cargoManifestStore;
    }

    public UserStore getUserStore() {
        return this.userStore;
    }

    /**
     * Gets the Org Role Store.
     *
     * @return the Org Role Store
     */
    public OrgRoleStore getOrgRoleStore() {
        return this.orgRoleStore;
    }

    /**
     * Gets the Ship Store Data.
     *
     * @return the Ship Store Data
     */
    public ShipStoreData getShipStoreData() {
        return shipStoreData;
    }

    /**
     * Gets the Port Store Data.
     *
     * @return the Port Store Data
     */
//    public PortStoreData getPortStoreData() {
//        return portStoreData;
//    }

    public CargoManifestStoreData getCargoManifestStoreData() {
        return cargoManifestStoreData;
    }

    public ContainerStoreData getContainerStoreData() {
        return containerStoreData;
    }

    public PortStoreData getPortStoreData() {
        return portStoreData;

    }
}

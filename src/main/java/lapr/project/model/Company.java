package lapr.project.model;

import lapr.project.model.stores.ShipStore;
import lapr.project.utils.auth.AuthFacade;
import lapr.project.utils.auth.domain.store.OrgRoleStore;
import org.apache.commons.lang3.StringUtils;

public class Company {

    private final AuthFacade authFacade;
    private final OrgRoleStore orgRoleStore;
    private final ShipStore shipStore;

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
     * Gets the Org Role Store.
     *
     * @return the Org Role Store
     */
    public OrgRoleStore getOrgRoleStore() {
        return this.orgRoleStore;
    }
}

package lapr.project.model;

import lapr.project.model.stores.ShipStore;
import lapr.project.utils.auth.AuthFacade;
import lapr.project.utils.auth.domain.store.OrgRoleStore;
import org.apache.commons.lang3.StringUtils;

public class Company {

    private final AuthFacade authFacade;
    private final OrgRoleStore orgRoleStore;
    private final ShipStore shipStore;

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.shipStore = new ShipStore();
        this.authFacade = new AuthFacade();
        this.orgRoleStore = new OrgRoleStore();

    }

    public ShipStore getShipStore() {
        return this.shipStore;
    }


    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public OrgRoleStore getOrgRoleStore() {
        return this.orgRoleStore;
    }
}

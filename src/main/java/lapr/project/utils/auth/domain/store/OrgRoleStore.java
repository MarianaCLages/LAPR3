package lapr.project.utils.auth.domain.store;


import lapr.project.utils.auth.domain.OrgRole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrgRoleStore {
    private List<OrgRole> lor = new ArrayList();

    public OrgRoleStore() {
        //Empty
    }

    public void addOrgRole(OrgRole role) {
        this.lor.add(role);
    }

    public List<OrgRole> getOrgRoles() {
        if (this.lor != null) {
            if (this.lor.isEmpty()) {
                throw new IllegalArgumentException("Organization Roles list is empty.");
            } else {
                return this.lor;
            }
        } else {
            return null;
        }
    }

    public OrgRole getRoleById(String id) {
        Iterator var2 = this.lor.iterator();

        OrgRole role;
        do {
            if (!var2.hasNext()) {
                throw new IllegalArgumentException("There is no Organization Role with that Id.");
            }

            role = (OrgRole) var2.next();
        } while (!role.getId().equals(id));

        return role;
    }

    public void setLor(List<OrgRole> lor) {
        this.lor = lor;
    }
}


package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;

public class Client extends Employee {

    public Client(OrgRole role, String employeeId, String name) {
        super(role, employeeId, name);
    }
}
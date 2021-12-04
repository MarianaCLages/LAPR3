package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;

public class PortManager extends Employee {

    /**
     * Constructor.
     *
     * @param role       the port manager's role
     * @param employeeId the port manager's ID
     * @param name       the port manager's name
     */
    public PortManager(OrgRole role, String employeeId, String name) {
        super(role, employeeId, name);
    }
}

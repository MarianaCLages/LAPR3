package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;

public class PortStaff extends Employee {

    /**
     * Constructor.
     *
     * @param role       the port staff's role
     * @param employeeId the port staff's ID
     * @param name       the port staff's name
     */
    public PortStaff(OrgRole role, String employeeId, String name) {
        super(role, employeeId, name);
    }
}

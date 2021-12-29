package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;

public class ShipEletricalEngineer extends Employee {

    /**
     * Constructor.
     *
     * @param role       the client's role
     * @param employeeId the client's ID
     * @param name       the client's name
     */
    public ShipEletricalEngineer(OrgRole role, String employeeId, String name) {
        super(role, employeeId, name);
    }
}

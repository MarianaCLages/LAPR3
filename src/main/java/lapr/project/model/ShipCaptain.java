package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;

public class ShipCaptain extends Employee {

    /**
     * Constructor.
     *
     * @param role       the ship captain's role
     * @param employeeId the ship captain's ID
     * @param name       the ship captain's name
     */
    public ShipCaptain(OrgRole role, String employeeId, String name) {
        super(role, employeeId, name);
    }
}


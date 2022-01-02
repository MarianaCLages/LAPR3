package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;

public class ShipChiefElectricalEngineer extends Employee {

    /**
     * Constructor.
     *
     * @param role       the ship chief electrical engineer's role
     * @param employeeId the ship chief electrical engineer's ID
     * @param name       the ship chief electrical engineer's name
     */
    public ShipChiefElectricalEngineer(OrgRole role, String employeeId, String name) {
        super(role, employeeId, name);
    }
}

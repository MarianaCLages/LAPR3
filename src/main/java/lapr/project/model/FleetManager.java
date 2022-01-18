package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;

public class FleetManager extends Employee {

    /**
     * Constructor.
     *
     * @param role       the fleet manager's role
     * @param employeeId the fleet manager's ID
     * @param name       the fleet manager's name
     */
    public FleetManager(OrgRole role, String employeeId, String name) {
        super(role, employeeId, name);
    }
}

package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;

public class TrafficManager extends Employee {

    /**
     * Constructor.
     *
     * @param role       the traffic manager's role
     * @param employeeId the traffic manager's ID
     * @param name       the traffic manager's name
     */
    public TrafficManager(OrgRole role, String employeeId, String name) {
        super(role, employeeId, name);
    }
}

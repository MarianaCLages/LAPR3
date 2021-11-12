package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;

public class Employee {
    private final String email;
    private final String employeeId;
    private final OrgRole role;
    private final String name;

    /**
     * Constructor.
     *
     * @param role       the employee's role
     * @param employeeId the employee's ID
     * @param name       the employee's name
     */
    public Employee(OrgRole role, String employeeId, String name) {
        this.employeeId = employeeId;
        this.role = role;
        this.name = name;
        this.email = this.generateEmail(employeeId);
    }

    /**
     * Generates an email.
     *
     * @param id the employee's ID
     * @return the generated email
     */
    public String generateEmail(String id) {
        return id != null ? id + "" + "@lei.pt" : null;
    }

    /**
     * Gets the employee's name.
     *
     * @return the employee's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the employee's email.
     *
     * @return the employee's email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the textual description of the employee info in the format: identification, email, name.
     *
     * @return the employee's characteristics
     */
    public String toString() {
        return "\n" + this.role.getId() + " - employeeId: " + this.employeeId + ", email: " + this.email + ", name: " + this.name + "";
    }
}

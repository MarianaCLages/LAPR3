package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;

public class Employee {

    private String email;
    private String employeeId;
    private OrgRole role;
    private String name;

    public Employee(OrgRole role, String employeeId, String name) {
        this.employeeId = employeeId;
        this.role = role;
        this.name = name;
        this.email = this.generateEmail(employeeId);
    }

    public String generateEmail(String id) {
        return id != null ? id + "" + "@lei.pt" : null;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String toString() {
        return "\n" + this.role.getId() + " - employeeId: " + this.employeeId + ", email: " + this.email + ", name: " + this.name + "";
    }
}

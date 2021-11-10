package lapr.project.model;

import lapr.project.utils.auth.domain.OrgRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee employee = new Employee(new OrgRole("1AB", "SoftWare Engineer"), "AA1B", "Tiago");

    @Test
    void generateEmailNull() {


        //Arrange
        String expected = null;
        //Act
        String actual = employee.generateEmail(null);
        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void generateEmailNullWithoutExpected() {


        //Arrange + Act
        String actual = employee.generateEmail(null);
        //Assert
        assertNull(actual);

    }

    @Test
    void testToString() {

        //Arrange
        String expected = "\n1AB - employeeId: AA1B, email: AA1B@lei.pt, name: Tiago";

        //Act
        String actual = employee.toString();

        //Assert
        assertEquals(expected, actual);

    }
}
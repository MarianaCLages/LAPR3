package lapr.project.utils.auth.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleTest {

    UserRole userRole = new UserRole("Trolley", "Construção");

    @Test
    void userRole() {
        //Arrange + Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            UserRole null_userRole = new UserRole(null, null);
        });
    }

    @Test
    void getId() {
        //Arrange
        String expected = "TROLLEY";
        //Act
        String actual = userRole.getId();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getDescription() {
        //Arrange
        String expected = "Construção";
        //Act
        String actual = userRole.getDescription();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void hasId() {
        //Arrange
        boolean expected = false;
        //Act
        boolean actual = userRole.hasId(null);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void testEqualsTrue() {

        //Arrange
        UserRole expected = new UserRole("Trolley", "Construção");
        //Act + Arrange
        assertTrue(userRole.equals(expected));

    }

    @Test
    void testEqualsFalse() {

        //Arrange
        UserRole expected = new UserRole("FashionDesigner", "Roupa para o Edu");
        //Act + Arrange
        assertFalse(userRole.equals(expected));

    }

    @Test
    void testEqualsSameObject() {

        //Arrange + Act
        UserRole actual = userRole;

        //Assert
        assertTrue(userRole.equals(actual));

    }

    @Test
    void testEqualsNull() {

        //Act + Arrange
        UserRole null_email = null;
        //Assert
        assertFalse(userRole.equals(null_email));

    }

    @Test
    void testEqualsOtherObject() {

        //Act + Arrange
        Object o = new Object();
        //Assert
        assertFalse(userRole.equals(o));

    }

    @Test
    void testToString() {
        //Arrange
        String expected = "TROLLEY - Construção";
        //Act
        String actual = userRole.toString();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void hashCodeTest() {

        int expected = -336214042;

        int actual = userRole.hashCode();

        if(actual==0) fail();

        assertEquals(expected, actual);

    }

}
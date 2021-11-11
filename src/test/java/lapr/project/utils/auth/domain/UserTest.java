package lapr.project.utils.auth.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");

    @Test
    void user() {
        //Arrange + Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User null_user = new User(null, null, null);
        });
    }

    @Test
    void getId() {

        //Arrange
        Email expected = new Email("1201487@isep.ipp.pt");
        //Act
        Email actual = user.getId();
        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void hasID() {

        //Arrange
        Email expected = new Email("1201487@isep.ipp.pt");
        //Act
        boolean actual = user.hasId(new Email("111@isep.ipp.pt"));
        //Assert
        if (actual) fail();
        assertNotEquals(expected, actual);

    }

    @Test
    void hasPasswordMutant() {

        //Arrange
        Password expected = new Password("fEgh");
        //Act
        boolean actual = user.hasPassword("");
        //Assert
        if (actual) fail();

    }

    @Test
    void getName() {
        //Arrange
        String expected = "Miguel";
        //Act
        String actual = user.getName();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void hasId() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = true;
        //Act
        boolean actual = user_Local.hasId(user.getId());
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void hasPassword() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = true;
        //Act
        boolean actual = user_Local.hasPassword("fEgh");
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void addRoleNotNull() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = true;
        UserRole userRole = new UserRole("Trolley", "Construir aplicações sem design");
        //Act
        boolean actual = user_Local.addRole(userRole);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void addRoleNotNullMutant() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = false;
        UserRole userRole = new UserRole("Trolley", "Construir aplicações sem design");
        //Act
        user_Local.addRole(userRole);
        boolean actual = user_Local.addRole(userRole);
        if (actual) fail();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void addRoleNull() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = false;
        UserRole userRole = null;
        //Act
        boolean actual = user_Local.addRole(userRole);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void removeRoleNull() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = false;
        UserRole userRole = new UserRole("Trolley", "Construir aplicações sem design");
        //Act
        boolean actual = user_Local.removeRole(userRole);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void removeRoleNullCase1() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = false;
        //Act
        boolean actual = user_Local.removeRole(null);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void removeRoleNullCase2() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = false;
        //Act
        boolean actual = user_Local.removeRole(null);
        if (actual) fail();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void removeRoleNotNull() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = false;
        UserRole userRole = null;
        //Act
        boolean actual = user_Local.removeRole(userRole);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void removeRoleNotNullMutant() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = false;
        UserRole userRole = null;
        //Act
        user_Local.removeRole(userRole);
        boolean actual = user_Local.removeRole(userRole);
        if (actual) fail();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void hasRole() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = false;
        UserRole userRole = new UserRole("Trolley", "Construir aplicações sem design");
        //Act
        boolean actual = user_Local.hasRole(userRole);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void hasRoleMutant() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = false;
        //Act
        boolean actual = user_Local.hasRole(new UserRole("a", "a"));
        if(actual) fail();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void hasRoleinListTrue() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = true;
        UserRole userRole = new UserRole("Trolley", "Construir aplicações sem design");
        user.addRole(userRole);
        //Act
        boolean actual = user.hasRole("Trolley");
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void hasRoleinListFalse() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        boolean expected = false;
        UserRole userRole = new UserRole("Brick placement", "Yes");
        user.addRole(userRole);
        //Act
        boolean actual = user.hasRole("Trolley");
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getRolesTrue() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        UserRole userRole = new UserRole("Brick placement", "Yes");
        user.addRole(userRole);
        List<UserRole> expected = new ArrayList<>();
        expected.add(userRole);
        //Act
        List<UserRole> actual = user.getRoles();
        // Assert
        assertTrue(expected.equals(actual));
    }

    @Test
    void getRolesFalse() {
        //Arrange
        User user_Local = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        UserRole userRole = new UserRole("Brick placement", "Yes");
        user.addRole(userRole);
        List<UserRole> expected = new ArrayList<>();
        //Act
        List<UserRole> actual = user.getRoles();
        // Assert
        assertFalse(expected.equals(actual));
    }

    @Test
    void testHashCode() {
        //Arrange
        int expected = -574791413;
        //Act
        int actual = user.hashCode();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void testEqualsTrue() {

        //Arrange
        User expected = new User(new Email("1201487@isep.ipp.pt"), new Password("fEgh"), "Miguel");
        //Act + Arrange
        assertTrue(user.equals(expected));

    }

    @Test
    void testEqualsFalse() {

        //Arrange
        User expected = new User(new Email("1200902@isep.ipp.pt"), new Password("iDggh"), "Sus");
        //Act + Arrange
        assertFalse(user.equals(expected));

    }

    @Test
    void testEqualsSameObject() {

        //Arrange + Act
        User actual = user;

        //Assert
        assertTrue(user.equals(actual));

    }

    @Test
    void testEqualsNull() {

        //Act + Arrange
        User null_email = null;
        //Assert
        assertFalse(user.equals(null_email));

    }

    @Test
    void testEqualsOtherObject() {

        //Act + Arrange
        Object o = new Object();
        //Assert
        assertFalse(user.equals(o));

    }

    @Test
    void testToString() {
        //Arrange
        String expected = "1201487@isep.ipp.pt - Miguel";
        //Act
        String actual = user.toString();
        //Assert
        assertEquals(expected, actual);
    }
}
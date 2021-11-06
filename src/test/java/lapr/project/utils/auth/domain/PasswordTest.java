package lapr.project.utils.auth.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    Password password = new Password("DM1eS");

    @Test
    void checkPasswordNull() {

        //Arrange + Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Password null_password = new Password(null);
        });

    }

    @Test
    void validatePasswordNotNull() {

        //Arrange
        Password password_Null = new Password("ABDE");
        boolean expected = false;
        //Act
        boolean actual = password_Null.checkPassword(null);
        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void validatePasswordNull() {

        //Arrange
        Password password_Null = new Password("ABDE");
        boolean expected = true;
        //Act
        boolean actual = password_Null.checkPassword("AAAAA");
        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void validatePasswordBlank() {

        //Arrange
        Password password_Null = new Password("ABDE");
        boolean expected = false;
        //Act
        boolean actual = password_Null.checkPassword("                          ");
        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void testHashCode() {

        //Arrange
        int expected = 65143687;
        //Act
        int actual = password.hashCode();
        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void testEqualsTrue() {

        //Arrange
        Password expected = new Password("DM1eS");
        //Act + Arrange
        assertTrue(password.equals(expected));

    }

    @Test
    void testEqualsFalse() {

        //Arrange
        Password expected = new Password("DED");
        //Act + Arrange
        assertFalse(password.equals(expected));

    }

    @Test
    void testEqualsSameObject() {

        //Arrange + Act
        Password actual = password;

        //Assert
        assertTrue(password.equals(actual));

    }

    @Test
    void testEqualsNull() {

        //Act + Arrange
        Password null_email = null;
        //Assert
        assertFalse(password.equals(null_email));

    }

    @Test
    void testEqualsOtherObject() {

        //Act + Arrange
        Object o = new Object();
        //Assert
        assertFalse(password.equals(o));

    }

}
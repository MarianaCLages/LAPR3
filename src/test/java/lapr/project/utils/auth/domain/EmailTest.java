package lapr.project.utils.auth.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    Email email = new Email("1201487@isep.ipp.pt");

    @Test
    void getEmail() {

        //Arrange
        String expected = "1201487@isep.ipp.pt";
        //Act
        String actual = email.getEmail();
        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void invalidEmail() {

        //Arrange + Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Email null_email = new Email(null);
        });
    }

    @Test
    void testHashCode() {

        //Arrange
        long expected = -574791574;
        //Act
        long actual = email.hashCode();
        //Arrange
        assertEquals(expected, actual);

    }

    @Test
    void testEqualsTrue() {

        //Arrange
        Email expected = new Email("1201487@isep.ipp.pt");
        //Act + Arrange
        assertTrue(email.equals(expected));

    }

    @Test
    void testEqualsFalse() {

        //Arrange
        Email expected = new Email("120902@isep.ipp.pt");
        //Act + Arrange
        assertFalse(email.equals(expected));

    }

    @Test
    void testEqualsSameObject() {

        //Arrange + Act
        Email actual = email;

        //Assert
        assertTrue(email.equals(actual));

    }

    @Test
    void testEqualsNull() {

        //Act + Arrange
        Email null_email = null;
        //Assert
        assertFalse(email.equals(null_email));

    }

    @Test
    void testEqualsOtherObject() {

        //Act + Arrange
        Object o = new Object();
        //Assert
        assertFalse(email.equals(o));

    }

    @Test
    void testToString() {
        //Arrange
        String expected = "1201487@isep.ipp.pt";
        //Act
        String actual = email.toString();
        //Arrange
        assertEquals(expected, actual);
    }
}
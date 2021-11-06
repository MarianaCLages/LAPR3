package lapr.project.utils.auth.domain;

import lapr.project.utils.auth.domain.OrgRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrgRoleTest {

    OrgRole orgRole = new OrgRole("13DE","TEST");

    @Test
    void invalidCreationOrgRole(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrgRole orgrole_null = new OrgRole(null,null);
        });
    }

    @Test
    void getId() {
        //Arrange
        String expected = "13DE";
        //Act
        String actual = orgRole.getId();
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void getDesignation() {
        //Arrange
        String expected = "TEST";
        //Act
        String actual = orgRole.getDesignation();
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void setId() {
        //Arrange
        String expected = "13DF";
        //Act
        orgRole.setId(expected);
        String actual = orgRole.getId();
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void setDesignation() {
        //Arrange
        String expected = "TESF";
        //Act
        orgRole.setDesignation(expected);
        String actual = orgRole.getDesignation();
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void testToString() {
        //Arrange
        String expected = "OrgRole{id='13DE', designation='TEST'}";
        //Act
        String actual = orgRole.toString();
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void testHashCode() {
        //Arrange
        int expected = 81225618;
        //Act
        int actual = orgRole.hashCode();
        //Assert
        assertEquals(expected,actual);

    }
}
package lapr.project.utils.auth.domain.store;

import lapr.project.utils.auth.domain.UserRole;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleStoreTest {

    UserRoleStore userRoleStore = new UserRoleStore();

    @Test
    void create() {
        //Arrange + Act + Assert
        assertNotNull(userRoleStore.create("Traffic Managaer","Controls the position of a ship"));

    }

    @Test
    void addTrue() {

        //Arrange
        UserRole userRole = new UserRole("Trolley","Construção");
        //Act
        boolean actual = userRoleStore.add(userRole);
        //Assert
        assertTrue(actual);

    }

    @Test
    void addFalse() {

        //Arrange
        UserRole userRole = new UserRole("Trolley","Construção");
        userRoleStore.add(userRole);
        //Act
        boolean actual = userRoleStore.add(userRole);
        if(actual) fail();
        //Assert
        assertFalse(actual);

    }

    @Test
    void removeTrue() {

        //Arrange
        UserRole userRole = new UserRole("Trolley","Construção");
        userRoleStore.add(userRole);
        //Act
        boolean actual = userRoleStore.remove(userRole);
        //Assert
        assertTrue(actual);

    }

    @Test
    void removeFalse() {

        //Arrange
        UserRole userRole = new UserRole("Trolley","Construção");
        //Act
        boolean actual = userRoleStore.remove(userRole);
        //Assert
        assertFalse(actual);

    }

    @Test
    void removeNull() {

        //Arrange + Act
        boolean actual = userRoleStore.remove(null);
        //Assert
        assertFalse(actual);

    }

    @Test
    void getByIdNotNull() {

        //Arrange
        UserRole userRole = new UserRole("Trolley","Construção");
        userRoleStore.add(userRole);
        //Act
        Optional<UserRole> actual = userRoleStore.getById("Trolley");
        //Assert
        assertNotNull(actual);

    }

    @Test
    void getByIdNull() {

        //Arrange
        UserRole userRole = new UserRole("Trolley","Construção");
        userRoleStore.add(userRole);
        //Act
        Optional<UserRole> actual = userRoleStore.getById("Sus");
        //Assert
       assertNotEquals(userRole,actual);

    }

    @Test
    void exists() {

        //Arrange
        UserRole userRole = new UserRole("Trolley","Construção");
        userRoleStore.add(userRole);
        //Act
        boolean actual = userRoleStore.exists("Trolley");
        //Assert
        assertTrue(actual);

    }

    @Test
    void existsMutant() {

        //Arrange
        UserRole userRole = new UserRole("Trolley","Construção");
        userRoleStore.add(userRole);
        //Act
        boolean actual = userRoleStore.exists("a");
        if(actual) fail();
        //Assert
        assertFalse(actual);

    }

    @Test
    void existsMutantUserRole() {

        //Arrange
        UserRole userRole = new UserRole("Trolley","Construção");
        //Act
        boolean actual = userRoleStore.exists(userRole);
        if(actual) fail();
        //Assert
        assertFalse(actual);

    }

    @Test
    void existsMutantUserRole1() {

        //Arrange
        UserRole userRole = new UserRole("Trolley","Construção");
        UserRole userRol2e = new UserRole("Troy","a");
        userRoleStore.add(userRole);
        //Act
        boolean actual = userRoleStore.exists(userRol2e);
        if(actual) fail();
        //Assert
        assertFalse(actual);

    }

}
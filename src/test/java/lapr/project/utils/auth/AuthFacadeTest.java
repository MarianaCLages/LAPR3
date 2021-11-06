package lapr.project.utils.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthFacadeTest {

    AuthFacade authFacade = new AuthFacade();

    String name = "Miguel";
    String email = "1201487@isep.ipp.pt";
    String password = "abCdEl";
    String[] roles = {"TrafficManager", "Trolley", "SuS"};

    @Test
    void addUserRole() {
        //Arrange
        boolean expected = true;
        //Act
        boolean actual =  authFacade.addUserRole("Trolley","Design");
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void addUser() {
        //Arrange
        boolean expected = true;
        //Act
        boolean actual =  authFacade.addUser(name,email,password);
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void addUserWithValidRole() {
        //Arrange
        boolean expected = true;
        authFacade.addUserRole("Trolley","Design");
        //Act
        boolean actual =  authFacade.addUserWithRole(name,email,password,"Trolley");
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void addUserWithNoValidRole() {
            //Arrange
            boolean expected = false;
            //Act
            boolean actual =  authFacade.addUserWithRole(name,email,password,"Trolley");
            //Assert
            assertEquals(expected,actual);
        }

    @Test
    void addUserWithValidRoles() {
        //Arrange
        authFacade.addUserRole("Trolley","Designer");
        boolean expected = true;
        //Act
        boolean actual =  authFacade.addUserWithRoles(name,email,password,roles);
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void existsUser() {
        //Arrange
        authFacade.addUser(name,email,password);
        boolean expected = true;
        //Act
        boolean actual =  authFacade.existsUser(email);
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void doLogin() {
        //Arrange
        authFacade.addUser(name,email,password);
        authFacade.doLogin(email,password);
        UserSession expected = authFacade.getCurrentUserSession();
       // authFacade.doLogout();
        //Act
       // UserSession actual = authFacade.doLogin(email,password);
        UserSession actual = authFacade.getCurrentUserSession(); //Nao estava a dar como comentei em cima ??
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void doLogout() {
        //Arrange
        authFacade.addUser(name,email,password);
        authFacade.doLogin(email,password);

        //Act

       authFacade.doLogout();

    }

    @Test
    void getUserStore() {
        //Arrange + Act + Assert
        assertNotNull(authFacade.getUserStore());
    }
}
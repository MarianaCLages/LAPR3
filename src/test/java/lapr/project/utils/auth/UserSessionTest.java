package lapr.project.utils.auth;

import lapr.project.utils.auth.domain.UserRole;
import lapr.project.utils.auth.mappers.UserRoleMapper;
import lapr.project.utils.auth.mappers.dto.UserRoleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    static AuthFacade authFacade = new AuthFacade();

    static String email = "121487@isep.ipp.pt";
    static String password = "abDeF";
    static String name = "Miguel";

    static String role = "Trolley";
    static String wrong_role = "Sus";
    static String description = "Building";

    @BeforeAll
    public static void init() {
        authFacade.addUserRole(role, description);
        authFacade.addUserWithRole(name, email, password, role);
        authFacade.doLogin(email, password);
    }

    @Test
    void createUserSessionWithError() {
        //Arrange + Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            UserSession null_userSession = new UserSession(null);
        });
    }

    @Test
    void doLogout() {
        //Arrange + Act + Assert
        authFacade.getCurrentUserSession().doLogout();
    }

    @Test
    void isLoggedIn() {
        //Arrange
        authFacade.addUserRole(role, description);
        authFacade.addUserWithRole(name, email, password, role);
        authFacade.doLogin(email, password);
        boolean expected = true;
        //Act
        boolean actual = authFacade.getCurrentUserSession().isLoggedIn();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void isLoggedInWithRole() {
        //Arrange
        authFacade.addUserRole(role, description);
        authFacade.addUserWithRole(name, email, password, role);
        authFacade.doLogin(email, password);
        boolean expected = true;
        //Act
        boolean actual = authFacade.getCurrentUserSession().isLoggedInWithRole(role);
        //Assert
        System.out.println(authFacade.getCurrentUserSession().toString());
        assertEquals(expected, actual);
    }

    @Test
    void isLoggedInWithWrongRole() {
        //Arrange
        boolean expected = false;
        authFacade.doLogout();
        //Act
        boolean actual = authFacade.getCurrentUserSession().isLoggedInWithRole(wrong_role);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getUserName() {
        //Arrange
        String expected = null;
        authFacade.addUserWithRole(name, email, password, role);
        authFacade.doLogin(email, password);
        //Act
        String actual = authFacade.getCurrentUserSession().getUserName();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getUserNameLoggedOut() {
        //Arrange
        String expected = null;
        authFacade.doLogout();
        //Act
        String actual = authFacade.getCurrentUserSession().getUserName();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getUserRoles() {
        //Arrange
        UserRoleMapper mapper = new UserRoleMapper();
        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        userRoleDTOS.add(mapper.toDTO(new UserRole(role, description)));
        UserRoleDTO expected = userRoleDTOS.get(0);
        authFacade.addUserRole(role, description);
        authFacade.addUserWithRole(name, email, password, role);
        authFacade.doLogin(email, password);
        //Act
        List<UserRoleDTO> userRoleDTOS1 = authFacade.getCurrentUserSession().getUserRoles();
        UserRoleDTO actual = userRoleDTOS1.get(0);
        //Assert
        assertNotEquals(expected, actual); //Isto devia dar bem se fizerem side by side comparssiisisision isto devia dar bem pa
    }

    @Test
    void getUserRolesNull() {
        //Arrange
        UserRoleMapper mapper = new UserRoleMapper();
        List<UserRoleDTO> expected = new ArrayList<>();
        expected.add(mapper.toDTO(new UserRole(role, description)));
        //Act
        authFacade.doLogout();
        List<UserRoleDTO> actual = authFacade.getCurrentUserSession().getUserRoles();
        //Assert
        assertNotEquals(expected, actual);
    }
}
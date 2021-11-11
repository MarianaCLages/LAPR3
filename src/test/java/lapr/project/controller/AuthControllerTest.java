package lapr.project.controller;

import lapr.project.utils.auth.domain.User;
import lapr.project.utils.auth.mappers.dto.UserRoleDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    AuthController authController = new AuthController();

    @Test
    void doLogin() {

        //Arrange
        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem design");
        authController.getApp().getAuthFacade().addUserWithRole("Mariana", "1200902@isep.ipp.pt", "69Sus", "Trolley");
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        boolean expected = true;
        //Act
        boolean actual = authController.getApp().getCurrentUserSession().isLoggedIn();
        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void getUserRoles() {

        //Arrange + Act
        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
        authController.getApp().getAuthFacade().addUserWithRole("Mariana", "1200902@isep.ipp.pt", "69Sus", "Trolley");
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        List<UserRoleDTO> list = authController.getUserRoles();
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        //Assert
        assertNotNull(list);

    }


    @Test
    void getUserRolesNull() {

        //Arrange + Act
        authController.doLogout();
        List<UserRoleDTO> list = authController.getUserRoles();
        //Assert
        assertEquals(list, null);

    }

    @Test
    void doLogout() {
        //Arrange
        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
        authController.getApp().getAuthFacade().addUserWithRole("Mariana", "1200902@isep.ipp.pt", "69Sus", "Trolley");
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        User expected = null;
        authController.doLogout();
        //Act
        User actual = authController.getApp().getCurrentUserSession().getUser();
        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void getCompany() {
        //Arrange + Act + Assert
        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
        authController.getApp().getAuthFacade().addUserWithRole("Mariana", "1200902@isep.ipp.pt", "69Sus", "Trolley");
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        assertNotNull(authController.getApp().getCompany());
    }

    @Test
    void addUserWithRoleMutant() {
        //Arrange + Act + Assert
        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
        authController.getApp().getAuthFacade().addUserWithRole("Mariana", "1200902@isep.ipp.pt", "69Sus", "Trolley");
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        boolean actual = authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
        if (actual) fail();
        assertFalse(actual);
    }

    @Test
    void addUserMutant() {
        //Arrange + Act + Assert
        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
        authController.getApp().getAuthFacade().addUserWithRole("Mariana", "1200902@isep.ipp.pt", "69Sus", "Trolley");
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        boolean actual = authController.getApp().getAuthFacade().addUser("Mariana", "1200902@isep.ipp.pt", "69Sus");
        if (actual) fail();
        assertFalse(actual);
    }

    @Test
    void addUserWithRoleMutant1() {
        //Arrange + Act + Assert
        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
        authController.getApp().getAuthFacade().addUserWithRole("Mariana", "1200902@isep.ipp.pt", "69Sus", "Trolley");
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        boolean actual = authController.getApp().getAuthFacade().addUserWithRole("Mariana", "1200902@isep.ipp.pt", "69Sus", "Trolley");
        if (actual) fail();

        if (actual) fail();

        assertFalse(actual);
    }

    @Test
    void addUserWithRolesMutant1() {
        //Arrange + Act + Assert

        String[] roles = {"Engineer", "Design", "Trolley"};

        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
        authController.getApp().getAuthFacade().addUserRole("Design", "Conoftware sem desing");
        authController.getApp().getAuthFacade().addUserRole("Engineer", "C sem desing");

        authController.getApp().getAuthFacade().addUserWithRoles("Mariana", "1200902@isep.ipp.pt", "69Sus", roles);
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        boolean actual = authController.getApp().getAuthFacade().addUserWithRoles("Mariana", "1200902@isep.ipp.pt", "69Sus", roles);
        if (actual) fail();

        assertFalse(actual);
    }

    @Test
    void existsUserMutant() {
        //Arrange + Act + Assert

        String[] roles = {"Engineer", "Design", "Trolley"};

        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
        authController.getApp().getAuthFacade().addUserRole("Design", "Conoftware sem desing");
        authController.getApp().getAuthFacade().addUserRole("Engineer", "C sem desing");

        authController.getApp().getAuthFacade().addUserWithRoles("Mariana", "1200902@isep.ipp.pt", "69Sus", roles);
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        boolean actual = authController.getApp().getAuthFacade().existsUser("a@a.pt");
        if (actual) fail();

        assertFalse(actual);
    }

}
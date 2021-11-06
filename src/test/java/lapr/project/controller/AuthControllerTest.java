package lapr.project.controller;

import lapr.project.utils.auth.domain.User;
import lapr.project.utils.auth.mappers.dto.UserRoleDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    static AuthController authController = new AuthController();

    @Test
    public void doLogin() {

        //Arrange
        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
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
    public void getUserRoles() {

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
    public void doLogout() {
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
    public void getCompany() {
        //Arrange + Act + Assert
        authController.getApp().getAuthFacade().addUserRole("Trolley", "Construir software sem desing");
        authController.getApp().getAuthFacade().addUserWithRole("Mariana", "1200902@isep.ipp.pt", "69Sus", "Trolley");
        authController.doLogin("1200902@isep.ipp.pt", "69Sus");
        assertNotNull(authController.getApp().getCompany());
    }

}
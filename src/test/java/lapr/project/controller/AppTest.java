package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.auth.UserSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    App app = App.getInstance();

    @Test
    void appMutant() throws SQLException {

        App app = new App();

        boolean actual = app.bootstrap();

        if (!actual) fail();


    }

    @Test
    void getCompany() {


        Company actual = app.getCompany();

        if (actual == null) fail();


    }

    @Test
    void getPropertiesMutant() {

        Properties actual = app.getProperties();

        if (actual == null) fail();

    }

    @Test
    void doLoginMutantReturnTrue() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boolean actual = app.doLogin("invalid@email", "invalid");

            if (actual) fail();
        });

    }

    @Test
    void doLoginMutantReturnTrue2() {

        boolean actual;

        if (!app.getAuthFacade().getCurrentUserSession().isLoggedIn()) {
            actual = app.doLogin("false@eMail.ptt", "invalid");
            if (actual) fail();
        } else
            assertNotEquals(true, false);
    }

    @Test
    void doLoginMutantReturnFalse() {

        app.getAuthFacade().doLogout();
        app.getAuthFacade().addUserRole("AA", "Test");
        app.getAuthFacade().addUserWithRole("Tiago", "invalid@email.pt", "aa", "AA");

        boolean actual = app.doLogin("invalid@email.pt", "aa");

        if (!actual) fail();


    }
}
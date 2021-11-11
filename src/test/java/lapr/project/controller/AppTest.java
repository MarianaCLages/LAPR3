package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    App app = App.getInstance();

    @Test
    void appMutant() {

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
    void getInstance() {
    }

    @Test
    void getCurrentUserSession() {
    }

    @Test
    void doLogin() {
    }

    @Test
    void doLogout() {
    }

    @Test
    void getAuthFacade() {
    }
}
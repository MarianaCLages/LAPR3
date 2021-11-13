package Integration.US101;

import lapr.project.controller.App;
import lapr.project.utils.auth.UserSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class US101IntegrationTest {

    App app = App.getInstance();

    @Test
    void runUS101() {
        UserSession verifyLogin = app.getAuthFacade().doLogin("TM00001@lei.pt", "495");

        if (verifyLogin == null) {
            fail(); //Verifies if the login was successful (since its necessary to be logged on as Traffic Manager to be able to run the US)
        }

    }
}

/*package Integration.US101;

import lapr.project.controller.App;
import lapr.project.controller.ImportShipsController;
import lapr.project.model.Company;
import lapr.project.model.stores.ShipStore;
import lapr.project.shared.exceptions.InvalidLineException;
import lapr.project.utils.auth.UserSession;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class US101IntegrationTestBigFile {

    App app = App.getInstance();
    Company company = app.getCompany();

    ShipStore shipStore = company.getShipStore();
    ImportShipsController importShipsController = new ImportShipsController();

    @Test
    void runUS101() throws InvalidLineException, FileNotFoundException {
        UserSession verifyLogin = app.getAuthFacade().doLogin("TM00001@lei.pt", "495");

        if (verifyLogin == null) {
            fail(); //Verifies if the login was successful (since its necessary to be logged on as Traffic Manager to be able to run the US)
        }

        importShipsController.importShips("data-ships&ports/bships.csv");
        assertNotNull(shipStore.shipByMmsiAVL);
        assertNotNull(shipStore.shipByIMOAVL);
        assertNotNull(shipStore.shipByCallSignAVL);

        assertEquals(shipStore.transformAVLintoListMMSI().size(), 133);
        assertEquals(shipStore.getShipByMmsi(316001267).getPosDate().getSize(), 1210);

        assertEquals(shipStore.getShipByMmsi(257881000).getPosDate().getSize(), 6);


    }
}*/

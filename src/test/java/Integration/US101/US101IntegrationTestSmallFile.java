package Integration.US101;

import lapr.project.controller.App;
import lapr.project.controller.ImportShipsController;
import lapr.project.model.Company;
import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.model.ShipByMmsi;
import lapr.project.model.stores.ShipStore;
import lapr.project.shared.exceptions.InvalidLineException;
import lapr.project.utils.auth.UserSession;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class US101IntegrationTestSmallFile {
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
        importShipsController.importShips("data-ships&ports/sships.csv");

        List<Ship> list = new ArrayList<>();
        Ship ship1 = new ShipByMmsi(210950000, null, "IMO9395044", "C4SQ2", null, 0, 0, 0, null, ' ');
        list.add(ship1);
        Ship ship2 = new ShipByMmsi(212180000, null, "IMO9643544", "5BBA4", null, 0, 0, 0, null, ' ');
        list.add(ship2);
        Ship ship3 = new ShipByMmsi(212351000, null, "IMO9305685", "5BZP3", null, 0, 0, 0, null, ' ');
        list.add(ship3);
        Ship ship4 = new ShipByMmsi(228339600, null, "IMO9450648", "FLSU", null, 0, 0, 0, null, ' ');
        list.add(ship4);
        Ship ship5 = new ShipByMmsi(229767000, null, "IMO9645970", "9HA3589", null, 0, 0, 0, null, ' ');
        list.add(ship5);
        Ship ship6 = new ShipByMmsi(229857000, null, "IMO9224726", "9HA3667", null, 0, 0, 0, null, ' ');
        list.add(ship6);
        Ship ship7 = new ShipByMmsi(229961000, null, "IMO9700122", "9HA3752", null, 0, 0, 0, null, ' ');
        list.add(ship7);
        Ship ship8 = new ShipByMmsi(235092459, null, "IMO9517575", "2FMJ5", null, 0, 0, 0, null, ' ');
        list.add(ship8);
        Ship ship9 = new ShipByMmsi(249047000, null, "IMO9192387", "9HJC9", null, 0, 0, 0, null, ' ');
        list.add(ship9);
        Ship ship10 = new ShipByMmsi(256304000, null, "IMO9344564", "9HA3880", null, 0, 0, 0, null, ' ');
        list.add(ship10);
        Ship ship11 = new ShipByMmsi(256888000, null, "IMO9473028", "9HA2954", null, 0, 0, 0, null, ' ');
        list.add(ship11);
        Ship ship12 = new ShipByMmsi(257881000, null, "IMO9701920", "LATO7", null, 0, 0, 0, null, ' ');
        list.add(ship12);
        Ship ship13 = new ShipByMmsi(258692000, null, "IMO9321677", "LAJB6", null, 0, 0, 0, null, ' ');
        list.add(ship13);
        Ship ship14 = new ShipByMmsi(303221000, null, "IMO7819216", "WDG5171", null, 0, 0, 0, null, ' ');
        list.add(ship14);
        Ship ship15 = new ShipByMmsi(303267000, null, "IMO6421086", "WNGW", null, 0, 0, 0, null, ' ');
        list.add(ship15);
        Ship ship16 = new ShipByMmsi(305176000, null, "IMO9344394", "V2DD5", null, 0, 0, 0, null, ' ');
        list.add(ship16);
        Ship ship17 = new ShipByMmsi(305373000, null, "IMO9488645", "V2EB3", null, 0, 0, 0, null, ' ');
        list.add(ship17);
        Ship ship18 = new ShipByMmsi(305776000, null, "IMO9506758", "V2FR9", null, 0, 0, 0, null, ' ');
        list.add(ship18);
        Ship ship19 = new ShipByMmsi(309416000, null, "IMO9417086", "C6XC6", null, 0, 0, 0, null, ' ');
        list.add(ship19);
        Ship ship20 = new ShipByMmsi(636019825, null, "IMO9222285", "D5WI6", null, 0, 0, 0, null, ' ');
        list.add(ship20);
        Ship ship21 = new ShipByMmsi(636091400, null, "IMO9373486", "A8ND5", null, 0, 0, 0, null, ' ');
        list.add(ship21);
        Ship ship22 = new ShipByMmsi(636092932, null, "IMO9225641", "D5VK6", null, 0, 0, 0, null, ' ');
        list.add(ship22);

        Collections.sort(list, new Comparator<Ship>() {
            @Override
            public int compare(Ship o1, Ship o2) {
                if (o1.getMmsi() > o2.getMmsi()) return 1;
                if (o1.getMmsi() < o2.getMmsi()) return -1;
                return 0;
            }
        });
        ListIterator<Ship> listIterator = list.listIterator();
        for (ShipByMmsi s : shipStore.shipByMmsiAVL.inOrder()) {

            assertEquals(s.compareTo((ShipByMmsi) listIterator.next()), 0);

        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Ship ship = new Ship();

        ship.setMmsi(212180000);
        ship.createPosition(LocalDateTime.parse("31/12/2020 19:37", formatter), 24.34573, -85.12394, 11.7, 119.9, 117);
        ship.createPosition(LocalDateTime.parse("31/12/2020 21:49", formatter), 24.14301, -85.72268, 11.7, 116.6, 114);
        ship.createPosition(LocalDateTime.parse("31/12/2020 19:37", formatter), 24.28016, -85.00316, 11.3, 120.8, 118);
        ship.createPosition(LocalDateTime.parse("31/12/2020 19:37", formatter), 24.20221, -85.85411, 11.3, 116.8, 117);
        ship.createPosition(LocalDateTime.parse("31/12/2020 19:37", formatter), 24.11445, -85.65529, 11.6, 113.3, 110);

        Ship shipToCompare = shipStore.getShipByMmsi(212180000);
        ListIterator<Position> listIterator1 = shipToCompare.getPosDate().getInOrderList().listIterator();
        for (Position p : ship.getPosDate().getInOrderList()) {
            assertEquals(p,listIterator1.next());
        }

    }
}

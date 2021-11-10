package lapr.project.shared;

import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PairOfShipsTest {
    Ship ship1 = new Ship(210950000, "VARAMO", "IMO9395044", "C4SQ2", "70", 166, 25, 9.5, "NA", 'B');
    Ship ship2 = new Ship(228339600, "CMA CGM ALMAVIVA", "IMO9450648", "FLSU", "70", 334, 42, 15, "79", 'B');

    @Test
    void newObjectTest() {
        PairOfShips pairOfShips = new PairOfShips(ship1, ship2);
        assertNotNull(pairOfShips);
    }


    @Test
    void getLeft() {
        PairOfShips pairOfShips = new PairOfShips(ship1, ship2);
        assertEquals(ship2, pairOfShips.getLeft());
    }

    @Test
    void getRight() {
        PairOfShips pairOfShips = new PairOfShips(ship1, ship2);
        assertEquals(ship1, pairOfShips.getRight());
    }

    @Test
    void testCheckShips() {
    }


    @Test
    void compareTo1() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        ship1.insertPosition(ship1.createPosition(LocalDateTime.parse("31-12-2020 23:16", formatter), 0, 0, 0, 0, 1));
        ship1.insertPosition(ship1.createPosition(LocalDateTime.parse("01-01-2021 00:16", formatter), 1, 0, 0, 0, 1));
        ship2.insertPosition(ship2.createPosition(LocalDateTime.parse("02-01-2021 00:16", formatter), 0, 0, 0, 0, 1));
        ship2.insertPosition(ship2.createPosition(LocalDateTime.parse("02-01-2021 01:16", formatter), 3, 0, 0, 0, 1));
        PairOfShips pairOfShips = new PairOfShips(ship1, ship2);
        Ship ship3 = new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", "9HA2954", "70", 334, 42, 14.7, "70", 'B');
        Ship ship4 = new Ship(257881000, "SPAR ARIES", "IMO9701920", "LATO7", "70", 199, 32, 13.3, "NA", 'B');
        ship3.insertPosition(ship3.createPosition(LocalDateTime.parse("03-01-2021 01:16", formatter), 0, 0, 0, 0, 1));
        ship3.insertPosition(ship3.createPosition(LocalDateTime.parse("04-01-2021 01:16", formatter), 5, 0, 0, 0, 1));
        ship4.insertPosition(ship4.createPosition(LocalDateTime.parse("02-01-2021 01:16", formatter), 0, 0, 0, 0, 1));
        ship4.insertPosition(ship4.createPosition(LocalDateTime.parse("02-01-2021 01:26", formatter), 1, 0, 0, 0, 1));
        PairOfShips pairOfShips1 = new PairOfShips(ship3, ship4);

        assertTrue(pairOfShips.compareTo(pairOfShips1) == 1);
    }


    @Test
    void compareToMinor() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        ship1.insertPosition(ship1.createPosition(LocalDateTime.parse("31-12-2020 23:16", formatter), 0, 0, 0, 0, 1));
        ship1.insertPosition(ship1.createPosition(LocalDateTime.parse("01-01-2021 00:16", formatter), 5, 0, 0, 0, 1));
        ship2.insertPosition(ship2.createPosition(LocalDateTime.parse("02-01-2021 00:16", formatter), 0, 0, 0, 0, 1));
        ship2.insertPosition(ship2.createPosition(LocalDateTime.parse("02-01-2021 01:16", formatter), 1, 0, 0, 0, 1));
        PairOfShips pairOfShips = new PairOfShips(ship1, ship2);
        Ship ship3 = new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", "9HA2954", "70", 334, 42, 14.7, "70", 'B');
        Ship ship4 = new Ship(257881000, "SPAR ARIES", "IMO9701920", "LATO7", "70", 199, 32, 13.3, "NA", 'B');
        ship3.insertPosition(ship3.createPosition(LocalDateTime.parse("03-01-2021 01:16", formatter), 0, 0, 0, 0, 1));
        ship3.insertPosition(ship3.createPosition(LocalDateTime.parse("04-01-2021 01:16", formatter), 1, 0, 0, 0, 1));
        ship4.insertPosition(ship4.createPosition(LocalDateTime.parse("02-01-2021 01:16", formatter), 0, 0, 0, 0, 1));
        ship4.insertPosition(ship4.createPosition(LocalDateTime.parse("02-01-2021 01:26", formatter), 3, 0, 0, 0, 1));
        PairOfShips pairOfShips1 = new PairOfShips(ship3, ship4);

        assertTrue(pairOfShips.compareTo(pairOfShips1) == -1);
    }

    @Test
    void compareTo0() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        ship1.insertPosition(ship1.createPosition(LocalDateTime.parse("31-12-2020 23:16", formatter), 0, 0, 0, 0, 1));
        ship1.insertPosition(ship1.createPosition(LocalDateTime.parse("01-01-2021 00:16", formatter), 3, 0, 0, 0, 1));
        ship2.insertPosition(ship2.createPosition(LocalDateTime.parse("02-01-2021 00:16", formatter), 0, 0, 0, 0, 1));
        ship2.insertPosition(ship2.createPosition(LocalDateTime.parse("02-01-2021 01:16", formatter), 1, 0, 0, 0, 1));
        PairOfShips pairOfShips = new PairOfShips(ship1, ship2);
        Ship ship3 = new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", "9HA2954", "70", 334, 42, 14.7, "70", 'B');
        Ship ship4 = new Ship(257881000, "SPAR ARIES", "IMO9701920", "LATO7", "70", 199, 32, 13.3, "NA", 'B');
        ship3.insertPosition(ship3.createPosition(LocalDateTime.parse("03-01-2021 01:16", formatter), 0, 0, 0, 0, 1));
        ship3.insertPosition(ship3.createPosition(LocalDateTime.parse("04-01-2021 01:16", formatter), 1, 0, 0, 0, 1));
        ship4.insertPosition(ship4.createPosition(LocalDateTime.parse("02-01-2021 01:16", formatter), 0, 0, 0, 0, 1));
        ship4.insertPosition(ship4.createPosition(LocalDateTime.parse("02-01-2021 01:26", formatter), 3, 0, 0, 0, 1));
        PairOfShips pairOfShips1 = new PairOfShips(ship3, ship4);

        assertTrue(pairOfShips.compareTo(pairOfShips1) == 0);
    }
}
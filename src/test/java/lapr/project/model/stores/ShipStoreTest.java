package lapr.project.model.stores;

import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipStoreTest {


    Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "150", 1, 1, 1, 1);

    ShipStore shipstore = new ShipStore();


    @Test
    void existsShip() {
        shipstore.addShip(shipgeral);
        assertNotNull(shipstore.findShip(shipgeral.getMmsi()));

        Ship ship1 = new Ship(123456789, "ya", "IMO1111211", "L", "123", 15.6, 45, 54, "NA", 'b');
        assertFalse((shipstore.existsShip(123456789)));
    }

    @Test
    void findShip() {
        shipstore.addShip(shipgeral);
        assertTrue(shipstore.existsShip((shipgeral.getMmsi())));
    }
}
package lapr.project.model.stores;

import lapr.project.model.Position;
import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PositionTreeStoreTest {

    PositionTreeStore positionTreeStore = new PositionTreeStore();

    Ship shipgeral2 = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

    ShipStore shipstore = new ShipStore();

    String sdate = "31-12-2020 23:16";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime date = LocalDateTime.parse(sdate, formatter);

    Position posgeral = new Position(0, 0, 0, 0, 1, date);

    @Test
    void addPosition() {

        //Arrange + Act
        boolean expected = positionTreeStore.addPosition(null);
        //Assert
        assertEquals(expected, true);

    }

    @Test
    void getPosition() {
        //Arrange + Act + Assert
        try {
            shipgeral2.insertPosition(posgeral);
            positionTreeStore.addPosition(posgeral);
            positionTreeStore.getPosition(date);
            assertNotNull(positionTreeStore.getPosition(date));
        } catch (Exception e) {
            positionTreeStore.getPosition(date);
        }
    }

    @Test
    void insertPosition() {
        //Arrange + Act + Assert

        boolean actual = positionTreeStore.addPosition(null);

        if(!actual) fail();

    }

}
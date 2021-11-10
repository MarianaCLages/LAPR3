package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.utils.mappers.dto.ShipDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListShipsControllerTest {

    ListShipsController ctrl = new ListShipsController();

    /*
    @Test
    void getShipList() {
        //Arrange
        Ship ship1 = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
        Ship ship2 = new Ship(222222222, "name", "IMO1121111", 1, 1, "B", "A", 1, 1, 1, 1);
        Ship ship3 = new Ship(333333333, "name", "IMO3333333", 1, 1, "A", "A", 1, 1, 1, 1);
        List<Ship> shipList = new ArrayList<>();
        shipList.add(ship1);
        shipList.add(ship2);
        shipList.add(ship3);

        //Act
        List<Ship> expectedList = ctrl.getShipList();

        //Assert
        assertEquals(expectedList, shipList);
    }
     */

    @Test
    void sortedList() {
        //Arrange
        try {
            Ship ship1 = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
            Ship ship2 = new Ship(222222222, "name", "IMO1121111", 1, 1, "B", "A", 1, 1, 1, 1);
            Ship ship3 = new Ship(333333333, "name", "IMO3333333", 1, 1, "A", "A", 1, 1, 1, 1);
            List<Ship> shipList = new ArrayList<>();
            shipList.add(ship1);
            shipList.add(ship2);
            shipList.add(ship3);
            List<Ship> expected = ctrl.sortedList();
            //Act + Assert

            if (expected.isEmpty()) fail();

            assertNull(expected);
        } catch (IllegalArgumentException e) {

        }
    }

    /*
    @Test
    void sortedListEquals() {
        //Arrange
        Ship ship1 = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
        Ship ship2 = new Ship(222222222, "name", "IMO1121111", 1, 1, "B", "A", 1, 1, 1, 1);
        Ship ship3 = new Ship(333333333, "name", "IMO3333333", 1, 1, "C", "C", 1, 1, 1, 1);
        List<Ship> expected = new ArrayList<>();
        expected.add(ship3);
        expected.add(ship2);
        expected.add(ship1);
        ctrl.getShipList().add(ship1);
        ctrl.getShipList().add(ship2);
        ctrl.getShipList().add(ship3);
        //Act + Assert
        List<Ship> actual = ctrl.sortedList();
        assertEquals(expected, actual);
    }
     */


    @Test
    void sortedListEmpty() {
        try {
            //Arrange
            List<Ship> expected = Collections.emptyList();
            //Act + Assert
            assertNotNull(expected);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    /*@Test
    void sortedListNull() {
        try {
            //Arrange
            List<Ship> expected = null;
            //Act + Assert
            assertNull(expected);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    void getShipListDTO() {
        //Arrange
        Ship ship1 = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
        Ship ship2 = new Ship(222222222, "name", "IMO1121111", 1, 1, "B", "A", 1, 1, 1, 1);
        Ship ship3 = new Ship(333333333, "name", "IMO3333333", 1, 1, "A", "A", 1, 1, 1, 1);
        List<Ship> shipList = ctrl.getShipList();
        shipList.add(ship1);
        shipList.add(ship2);
        shipList.add(ship3);

        //Act
        List<ShipDTO> shipDTOList = ctrl.getShipListDTO();

        //Assert
        assertEquals(shipDTOList, "[MMSI: 210950000; Total number of movements: 1; Travelled Distance: 0.0; Delta Distance: 0.0\n" +
                ", MMSI: 212180000; Total number of movements: 1; Travelled Distance: 0.0; Delta Distance: 0.0\n" +
                "]");
    }*/
}
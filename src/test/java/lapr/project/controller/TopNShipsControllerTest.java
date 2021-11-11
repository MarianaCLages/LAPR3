package lapr.project.controller;

import lapr.project.model.Position;
import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopNShipsControllerTest {

    TopNShipsController topNController = new TopNShipsController();

    Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String sdate = "31-12-2020 23:16";
    LocalDateTime date = LocalDateTime.parse(sdate, formatter);

    String sdate2 = "31-12-2020 23:18";
    LocalDateTime date2 = LocalDateTime.parse(sdate2, formatter);

    //Position
    Position posgeral = new Position(0, 0, 0, 0, 1, date);

    @Test
    void getTopNShips() {
        //Arrange
        shipgeral.getPosDate().addPosition(posgeral);
        topNController.shipStore.getShipBinarySearchTree().insert(shipgeral);
        List<Ship> expectedList = new ArrayList<>();
        expectedList.add(shipgeral);

        //Act
        List<Ship> actualList = topNController.getTopNShips(1, "A", date, date2);

        //Assert
        assertEquals(expectedList.size(), actualList.size());
    }

    @Test
    void getTopNShipsMutant() {
        //Arrange

        //Act
        List<Ship> actual = topNController.getTopNShips(1, "A", date, date2);

        //Assert
        if(actual.equals(Collections.emptyList())) fail();

    }

    @Test
    void getTopNShipsMutantAssert() {
        //Arrange

        //Act
        List<Ship> actual = topNController.getTopNShips(1, "A", date, date2);

        //Assert
        if(actual.equals(Collections.emptyList())) assertNotEquals(Collections.emptyList(),actual );

    }

}
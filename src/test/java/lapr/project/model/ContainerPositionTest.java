package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerPositionTest {

    ContainerPosition cp = new ContainerPosition(1, 2, 3);

    @Test
    void getxPos() {
        //Arrange
        int expected = 1;
        //Act
        int actual = cp.getxPos();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void getyPos() {
        //Arrange
        int expected = 2;
        //Act
        int actual = cp.getyPos();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void getzPos() {
        //Arrange
        int expected = 3;
        //Act
        int actual = cp.getzPos();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void setxPos() {
        //Arrange
        int expected = 4;
        //Act
        cp.setxPos(expected);
        int actual = cp.getxPos();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void setyPos() {
        //Arrange
        int expected = 8;
        //Act
        cp.setyPos(expected);
        int actual = cp.getyPos();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void setzPos() {
        //Arrange
        int expected = 1;
        //Act
        cp.setzPos(expected);
        int actual = cp.getzPos();
        //Assert
        assertEquals(actual, expected);
    }
}
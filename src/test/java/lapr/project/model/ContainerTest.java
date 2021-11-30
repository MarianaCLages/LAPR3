package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    Container containerReal = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

    Container containerEqualsTrue = new Container("20BD", 1000, 1000, 100, "20RF", true, true);

    @Test
    void getGross() {
        //Arrange
        int expected = 100;
        //Act
        int actual = containerReal.getGross();
        //Assert
        assertEquals(actual, expected);

    }

    @Test
    void getPayload() {
        //Arrange
        int expected = 1000;
        //Act
        int actual = containerReal.getPayload();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void getTare() {
        //Arrange
        int expected = 1000;
        //Act
        int actual = containerReal.getTare();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void getIdentification() {
        //Arrange
        String expected = "20BD";
        //Act
        String actual = containerReal.getIdentification();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void getIsoCode() {
        //Arrange
        String expected = "20RF";
        //Act
        String actual = containerReal.getIsoCode();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void getPosition() {
        //Arrange
        ContainerPosition cp = new ContainerPosition(1, 1, 1);
        Container containerPos = new Container("20BD", 1000, 1000, 100, "20RF", false, false);
        containerPos.setPosition(cp);
        //Act
        ContainerPosition actual = containerPos.getPosition();
        //Assert
        assertEquals(actual, cp);
    }

    @Test
    void getOffLoadFalse() {
        //Arrange

        //Act
        boolean actual = containerReal.getOffLoad();
        //Assert
        assertFalse(actual);
    }

    @Test
    void getOffLoadTrue() {
        //Arrange

        //Act
        boolean actual = containerEqualsTrue.getOffLoad();
        //Assert
        assertTrue(actual);
    }

    @Test
    void getIsRefrigerated() {
        //Arrange

        //Act
        boolean actual = containerEqualsTrue.getIsRefrigerated();
        //Assert
        assertTrue(actual);
    }

    @Test
    void getContainerTypeFalse() {
        //Arrange
        String expected = "Not refrigerated";
        //Act
        String actual = containerReal.getContainerType();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void getContainerTypeTrue() {
        //Arrange
        String expected = "Refrigerated";
        //Act
        String actual = containerEqualsTrue.getContainerType();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void setGross() {
        //Arrange
        int expected = 200;
        //Act
        containerReal.setGross(expected);
        int actual = containerReal.getGross();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void setIdentification() {
        //Arrange
        String expected = "20DD";
        //Act
        containerReal.setIdentification(expected);
        String actual = containerReal.getIdentification();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void setIsoCode() {
        //Arrange
        String expected = "2DD2";
        //Act
        containerReal.setIsoCode(expected);
        String actual = containerReal.getIsoCode();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void setPayload() {
        //Arrange
        int expected = 1002;
        //Act
        containerReal.setPayload(expected);
        int actual = containerReal.getPayload();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void setTare() {
        //Arrange
        int expected = 9999;
        //Act
        containerReal.setTare(expected);
        int actual = containerReal.getTare();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void setPosition() {
        //Arrange
        ContainerPosition expected = new ContainerPosition(2,2,2);
        //Act
        containerReal.setPosition(expected);
        ContainerPosition actual = containerReal.getPosition();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void testToString() {
        //Arrange
        String expected = "Container{identification='20BD', payload=1000, tare=1000, gross=100, isoCode='20RF', position=null}";
        //Act
        String actual = containerReal.toString();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void testEqualsTrue() {
        //Arrange
        //Act
        boolean actual = containerReal.equals(containerEqualsTrue);
        //Assert
        assertTrue(actual);
    }

    @Test
    void testEqualsSameObject() {
        //Arrange
        //Act
        boolean actual = containerReal.equals(containerReal);
        //Assert
        assertTrue(actual);
    }

    @Test
    void testEqualsInverseOperand() {
        //Arrange
        //Act
        boolean actual = !(containerReal.equals(containerReal));
        //Assert
        assertFalse(actual);
    }

    @Test
    void testEqualsAssert() {
        //Arrange
        //Act
        boolean actual = containerReal.equals(containerReal);
        //Assert
        assertEquals(actual, containerReal.equals(containerReal));
    }

    @Test
    void testHashCode() {
        //Arrange
        //Act
        int expected = -141777933;
        //Assert
        assertEquals(expected, containerReal.hashCode());
    }


    @Test
    void compareContainers() {

        //Arrange + Act
        boolean actual = containerReal.equals(new Container("20BD", 1100, 1100, 110, "20RF", false, false));
        //Assert
        if (actual) {
            fail();
        }
    }

    @Test
    void compareContainersNull() {

        //Arrange + Act
        boolean actual = containerReal.equals(null);
        //Assert
        if (actual) {
            fail();
        }
    }
}
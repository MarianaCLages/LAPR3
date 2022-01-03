package lapr.project.model;

import lapr.project.shared.ContainerPosition;
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
        ContainerPosition expected = new ContainerPosition(2, 2, 2);
        //Act
        containerReal.setPosition(expected);
        ContainerPosition actual = containerReal.getPosition();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void testToString() {
        //Arrange
        String expected = "Container{identification='20BD', payload=1000, tare=1000, gross=100, isoCode='20RF', position=ContainerPosition{xPos=0, yPos=0, zPos=0}}";
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
        assertFalse(actual);
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
        int expected = containerReal.hashCode();
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

    @Test
    void compareContainersNullMutant() {

        //Arrange + Act
        Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

        boolean actual = containerReal.equals(shipgeral);
        //Assert
        if (actual) {
            fail();
        }
    }

    @Test
    void getIsRefrigerated2() {

        boolean actual = containerReal.getIsRefrigerated();

        if (actual) fail();

    }

    @Test
    void equalsMutant() {

        Container containerReal1 = new Container("20BC", 1000, 1000, 100, "20RF", false, false);
        Container containerReal2 = new Container("20BD", 1010, 1000, 100, "20RF", false, false);
        Container containerReal3 = new Container("20BD", 1000, 1100, 100, "20RF", false, false);
        Container containerReal4 = new Container("20BD", 1000, 1000, 110, "20RF", false, false);
        Container containerReal5 = new Container("20BD", 1000, 1000, 100, "20CF", false, false);
        Container containerReal6 = new Container("20BD", 1000, 1000, 100, "20RF", true, false);
        Container containerReal7 = new Container("20BD", 1000, 1000, 100, "20RF", false, true);


        boolean actual1 = containerReal.equals(containerReal1);

        if (actual1) fail();

        boolean actual2 = containerReal.equals(containerReal2);

        if (actual2) fail();

        boolean actual3 = containerReal.equals(containerReal3);

        if (actual3) fail();

        boolean actual4 = containerReal.equals(containerReal4);

        if (actual4) fail();

        boolean actual5 = containerReal.equals(containerReal5);

        if (actual5) fail();

        boolean actual6 = containerReal.equals(containerReal6);

        if (actual6) fail();

        boolean actual7 = containerReal.equals(containerReal7);

        if (actual7) fail();


    }

    @Test
    void hashCodeC() {

        int hash = containerReal.hashCode();

        if(hash==0) fail();

    }

    @Test
    void compareT() {

        int ac = containerReal.compareTo(new Container("20BC", 1100, 1300, 2, "AAA", false, true));

        if(ac != 0) fail();


    }

}
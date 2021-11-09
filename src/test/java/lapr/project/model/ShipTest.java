package lapr.project.model;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipTest {


    Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

    LocalDateTime dateTime =LocalDateTime.now();

    Position posgeral = new Position(dateTime, 0, 0, 1, 0,0);

    @Test
    void checkMMSITest() {

        //Arrange
        //Act
        try {
            Ship ship1 = new Ship(111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
            Ship ship2 = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

        } catch (IllegalArgumentException ex) {
            //Assert
            assertEquals("MMSI code must have 9 digits!", ex.getMessage());
        }


    }

    @Test
    void checkIMOTest() {

        //Arrange
        //Act
        try {
            Ship ship1 = new Ship(111111111, "name", "11", 1, 1, "A", "A", 1, 1, 1, 1);
            Ship ship2 = new Ship(111111111, "name", "1111111", 1, 1, "A", "A", 1, 1, 1, 1);
        } catch (IllegalArgumentException ex) {
            //Assert
            assertEquals("IMO code must have 7 digits!", ex.getMessage());
        }


    }


    @Test
    void getMmsiTest() {

        //Arrange
        //Act
        //Assert
        assertEquals(111111111, shipgeral.getMmsi());
    }

    @Test
    void setMmsiTest() {
        //Arrange
        //Act
        shipgeral.setMmsi(222222222);
        //Assert
        assertEquals(222222222, shipgeral.getMmsi());
    }

    @Test
    void getNameTest() {
        //Arrange
        //Act
        //Assert
        assertEquals("name", shipgeral.getName());

    }

    @Test
    void setNameTest() {
        //Arrange
        //Act
        shipgeral.setName("barco");
        //Assert
        assertEquals("barco", shipgeral.getName());

    }

    @Test
    void getImoTest() {
        //Arrange
        //Act
        //Assert
        assertEquals("IMO1111111", shipgeral.getImo());
    }

    @Test
    void setImoTest() {

        //Arrange
        //Act
        shipgeral.setImo("IMO2222222");

        //Assert
        assertEquals("IMO2222222", shipgeral.getImo());
    }

    @Test
    void getNumGenTest() {
        //Arrange
        //Act
        //Assert
        assertEquals(1, shipgeral.getNumGen());
    }

    @Test
    void setNumGenTest() {

        //Arrange
        //Act
        shipgeral.setNumGen(2);
        //Assert
        assertEquals(2, shipgeral.getNumGen());

    }

    @Test
    void getCallSignTest() {

        //Arrange
        //Act
        //Assert
        assertEquals("A", shipgeral.getCallSign());
    }

    @Test
    void setCallSignTest() {

        //Arrange
        //Act
        shipgeral.setCallSign("B");
        //Assert
        assertEquals("B", shipgeral.getCallSign());
    }

    @Test
    void getVesselTypeTest() {

        //Arrange
        //Act
        //Assert
        assertEquals("A", shipgeral.getVesselType());
    }

    @Test
    void setVesselTypeTest() {

        //Arrange
        //Act
        shipgeral.setVesselType("B");
        //Assert
        assertEquals("B", shipgeral.getVesselType());
    }

    @Test
    void getLength() {

        //Arrange
        //Act
        //Assert
        assertEquals(1, shipgeral.getLength());
    }

    @Test
    void setLengthTest() {

        //Arrange
        //Act
        shipgeral.setLength(2);
        //Assert
        assertEquals(2, shipgeral.getLength());
    }

    @Test
    void getWidthTest() {

        //Arrange
        //Act
        //Assert
        assertEquals(1, shipgeral.getWidth());
    }

    @Test
    void setWidthTest() {

        //Arrange
        //Act
        shipgeral.setWidth(2);
        //Assert
        assertEquals(2, shipgeral.getWidth());
    }

    @Test
    void getCapacityTest() {

        //Arrange
        //Act
        //Assert
        assertEquals(1, shipgeral.getCapacity());
    }

    @Test
    void setCapacityTest() {

        //Arrange
        //Act
        shipgeral.setCapacity(2);
        //Assert
        assertEquals(2, shipgeral.getCapacity());
    }

    @Test
    void getDraftTest() {

        //Arrange
        //Act
        //Assert
        assertEquals(1, shipgeral.getDraft());
    }

    @Test
    void setDraftTest() {

        //Arrange
        //Act
        shipgeral.setDraft(2);
        //Assert
        assertEquals(2, shipgeral.getDraft());

    }

    @Test
    void setGenPowerOutputTest() {

        //Arrange
        //Act
        shipgeral.setGenPowerOutput(2);
        //Assert
        assertEquals(2, shipgeral.getGenPowerOutput());
    }


    @Test
    void toStringTest() {

        //Arrange
        String expected = shipgeral.toString();
        //Act
        //Assert
        assertEquals(expected, shipgeral.toString());
    }


}


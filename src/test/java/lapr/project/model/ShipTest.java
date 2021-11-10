package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class ShipTest {


    Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
    Ship shipgeral2 = new Ship(121111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    String sdate = "31-12-2020 23:16";
    LocalDateTime date = LocalDateTime.parse(sdate, formatter);

    String sdate2 = "31-12-2020 23:50";
    LocalDateTime date2 = LocalDateTime.parse(sdate2, formatter);

    //Position
    Position posgeral = new Position(0, 0, 0, 0, 1, date);
    Position posgeral2 = new Position(10, 20, 30, 20, 10, date2);

    @Test
    void checkMMSITest() {

        //Arrange
        //Act
        try {
            Ship ship0 = new Ship(111111111);
            Ship ship1 = new Ship(111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
            Ship ship2 = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

        } catch (IllegalArgumentException ex) {
            //Assert
            assertEquals(ex.getMessage(), "MMSI code must have 9 digits!");
        }


    }

    @Test
    void checkIMOTest() {

        //Arrange
        //Act
        try {
            Ship ship1 = new Ship(111111111, "name", "IMO11", 1, 1, "A", "A", 1, 1, 1, 1);
            Ship ship2 = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
        } catch (IllegalArgumentException ex) {
            //Assert
            assertEquals(ex.getMessage(), "IMO code must have 7 digits!");
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
        shipgeral.setImo("IMO1111111");

        //Assert
        assertEquals("IMO1111111", shipgeral.getImo());
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



/*
    @Test
    public void getPositionByLocalDateTimeTest(){

        //Arrange
        shipgeral.addPosition(posgeral);
        Position expected = posgeral;
        //Act
        //Assert
        assertEquals(expected, shipgeral.getPositionByLocalDateTime(dateTime));
    }*/


    @Test
    void toStringTest() {

        //Arrange
        String expected = shipgeral.toString();
        //Act
        //Assert
        assertEquals(expected, shipgeral.toString());
    }

    @Test
    void getTravelledDistance() {

        //Arrange
        shipgeral.getPosDate().addPosition(posgeral);
        shipgeral.getPosDate().addPosition(posgeral2);

        double expected = 2491535.47;

        //Act
        double actual = shipgeral.getTravelledDistance();

        //Assert

        assertEquals(expected, actual);

    }

    @Test
    void getDeltaDistance() {

        //Arrange
        shipgeral.getPosDate().addPosition(posgeral);
        shipgeral.getPosDate().addPosition(posgeral2);

        double expected = 2491535.47;

        //Act
        double actual = shipgeral.getDeltaDistance();

        //Assert

        assertEquals(expected, actual);

    }

    @Test
    void compareToBigger() {

        //Arrange
        int expected = -1;

        //Act
        int actual = shipgeral.compareTo(shipgeral2);

        //Assert

        assertEquals(expected, actual);

    }

    @Test
    void compareToSmaller() {

        //Arrange
        int expected = 1;

        //Act
        int actual = shipgeral2.compareTo(shipgeral);

        //Assert

        assertEquals(expected, actual);

    }

    @Test
    void compareToSameShip() {

        //Arrange
        int expected = 0;

        //Act
        int actual = shipgeral.compareTo(shipgeral);

        //Assert

        assertEquals(expected, actual);

    }

    @Test
    void equalsSameObject() {

        //Arrange
        boolean expected = true;

        //Act
        boolean actual = shipgeral.equals(shipgeral);

        //Assert

        assertEquals(expected, actual);

    }

    @Test
    void equalsNotAInstanceOfShip() {

        Object not_Istance_of_Ship = new Object();

        //Arrange
        boolean expected = false;

        //Act
        boolean actual = shipgeral.equals(not_Istance_of_Ship);

        //Assert

        assertEquals(expected, actual);

    }

    @Test
    void equalsBetween2Ships() {

        Object not_Istance_of_Ship = new Object();

        //Arrange
        boolean expected = false;

        //Act
        boolean actual = shipgeral.equals(shipgeral2);

        //Assert

        assertEquals(expected, actual);

    }

    @Test
    void hashCodeTest() {

        //Arrange
        int expected = -1099595711;
        //Act
        int actual = shipgeral.hashCode();
        //Assertion
        assertEquals(expected, actual);

    }

    @Test
    void insertPosition() {

        //Arrange
        Position expected = posgeral;
        shipgeral.insertPosition(posgeral);
        //Act
        Position actual = null;
        for (Position p : shipgeral.getPosDate().getInOrderList()) {

            if (p.equals(expected)) {
                actual = p;
            }

        }
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void createPosition() {

        //Arrange
        Position expected = shipgeral.createPosition(date2, 20, 30, 40, 10, 20);
        //Act + Assert
        assertNotNull(expected);
    }

    @Test
    void getPosition() {

        //Arrange
        List<Position> expected = shipgeral.getDate();
        //Act + Assert
        assertNotNull(expected);
    }

    @Test
    void setPosition() {

        //Arrange
        List<Position> expected = new ArrayList<>();
        expected.add(posgeral);
        expected.add(posgeral2);
        shipgeral.setDate(expected);
        //Act + Assert
        assertNotNull(expected);
    }

   /* @Test
    void writeAllPosTest() {

        //Arrange
        shipgeral.getPosDate().addPosition(posgeral);
        LocalDateTime li = LocalDateTime.of(2020,12,01,01,01,01);
        LocalDateTime lf = LocalDateTime.of(2020,12,31,23,59,50);

        String expected = "Positional Message:";
        String expected2 = "Positional Message:\n" +
                "Position{latitude=0.0, longitude=0.0, heading=0.0, sog=0.0, cog=1.0}";


        //Act + Assert
        assertEquals(expected, shipgeral.writeAllPos(null,null));
        assertEquals(expected2,shipgeral.writeAllPos(li,lf));
    }

    @Test
    void addNewPosMessage() {
        //Arrange
        boolean expected = false;
        //Act
        boolean actual = shipgeral.addNewPosMessage(posgeral2);
        //Assert
        assertEquals(expected, actual);
    } */

   /* @Test
    void creationTest() {

        //Arrange + Act + Assert

        assertNotNull(new Ship(111111111, "Ship", "IMO1111111", "A", "A", 23, 23, 23, 23, 'a'));

    }*/

    @Test
    void getTotalNumberOfMovements() {

        //Arrange + Act + Assert
        assertNotNull(shipgeral.getTotalNumberOfMovements());
    }

    @Test
    void writeAllPosTest() {

        //Arrange
        shipgeral.getPosDate().addPosition(posgeral);
        LocalDateTime li = LocalDateTime.of(2020, 12, 01, 01, 01, 01);
        LocalDateTime lf = LocalDateTime.of(2020, 12, 31, 23, 59, 50);

        String expected = "Positional Message:";
        String expected2 = "Positional Message:\n" +
                "Position{latitude=0.0, longitude=0.0, heading=0.0, sog=0.0, cog=1.0}";


        //Act + Assert
        assertEquals(expected, shipgeral.writeAllPos(null, null));
        assertEquals(expected2, shipgeral.writeAllPos(li, lf));
    }

    @Test
    void notCheckingMMSICreatingShipWithOnlyMMSI() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Ship ship_invalid = new Ship(1111111111);
            if (ship_invalid.getMmsi() > 99999999 && ship_invalid.getMmsi() < 1000000000) {
                fail();
            }
        });
    }

    @Test
    void notCheckingMMSICreatingShip1Type() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Ship ship_invalid = new Ship(1111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
            if (ship_invalid.getMmsi() > 99999999 && ship_invalid.getMmsi() < 1000000000) {
                fail();
            }
        });
    }

    @Test
    void notCheckingMMSICreatingShip2Type() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Ship ship_invalid = new Ship(1111111111, "test", "test", 1, 3, "a", "a", 3, 4, 5, 6);
            if (ship_invalid.getMmsi() > 99999999 && ship_invalid.getMmsi() < 1000000000) {
                fail();
            }
        });
    }

    @Test
    void notCheckingIMOCreatingShip1Type() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Ship ship_invalid = new Ship(1111111111, "name", "IMO11", 1, 1, "A", "A", 1, 1, 1, 1);
            if (ship_invalid.getMmsi() > 99999999 && ship_invalid.getMmsi() < 1000000000) {
                fail();
            }
        });
    }

    @Test
    void notCheckingIMOCreatingShip2Type() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Ship ship_invalid = new Ship(1111111111, "test", "12", 1, 3, "a", "a", 3, 4, 5, 6);
            if (ship_invalid.getMmsi() > 99999999 && ship_invalid.getMmsi() < 1000000000) {
                fail();
            }
        });
    }

    @Test
    void notCheckingIMOCreatingShip3Type() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Ship ship_invalid = new Ship(1111111111, "name", "AA", 1, 1, "A", "A", 1, 1, 1, 1);
            if (ship_invalid.getMmsi() > 99999999 && ship_invalid.getMmsi() < 1000000000) {
                fail();
            }
        });
    }

    @Test
    void equalsMutant() {

        boolean actual = shipgeral.equals(shipgeral2);

        if(actual && shipgeral.getMmsi() == shipgeral2.getMmsi() && Objects.equals(shipgeral.getImo(), shipgeral2.getImo()) && shipgeral.getNumGen() == shipgeral2.getNumGen() && shipgeral.getGenPowerOutput() == shipgeral2.getGenPowerOutput() && shipgeral.getLength() == shipgeral2.getLength() && shipgeral.getWidth() == shipgeral2.getWidth() && shipgeral.getCapacity() == shipgeral2.getCapacity() && shipgeral.getDraft() == shipgeral2.getDraft() && Objects.equals(shipgeral.getName(), shipgeral2.getName()) && Objects.equals(shipgeral.getCallSign(), shipgeral2.getCallSign()) && Objects.equals(shipgeral.getVesselType(), shipgeral2.getVesselType())) fail();

    }

    @Test
    void checkIMOMutant(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            if(shipgeral.checkIMO("12")) fail();
        });
        
    }


}

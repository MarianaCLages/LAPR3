package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class ShipTest {


    Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
    Ship shipgeral2 = new Ship(121111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    String sdate = "29-12-2020 00:16";
    LocalDateTime date = LocalDateTime.parse(sdate, formatter);

    String sdate2 = "31-12-2020 23:50";
    LocalDateTime date2 = LocalDateTime.parse(sdate2, formatter);

    String sdate3 = "31-12-2020 23:16";
    LocalDateTime date3 = LocalDateTime.parse(sdate, formatter);

    String sdate4 = "31-12-2020 00:17";
    LocalDateTime date4 = LocalDateTime.parse(sdate, formatter);

    String sdate5 = "31-12-2020 00:00";
    LocalDateTime date5 = LocalDateTime.parse(sdate, formatter);

    String sdate6 = "31-12-2020 00:00";
    LocalDateTime date6 = LocalDateTime.parse(sdate, formatter);

    //Position
    Position posgeral = new Position(0, 0, 0, 0, 1, date);
    Position posgeral2 = new Position(10, 20, 30, 20, 10, date2);
    Position posgeral3 = new Position(20, 30, 40, 20, 10, date3);
    Position posgeral4 = new Position(20, 30, 40, 20, 10, date3);

    //Container
    Container container = new Container("111",11,1,1,"11",true, false);
    Container container2 = new Container("222",22,2,2,"22",true, false);

    //Location
    FacilityLocation facilityLocation = new FacilityLocation(11,11);
    FacilityLocation facilityLocation2 = new FacilityLocation(8,8);
    //Port
    Port port = new Port("Europa","Portugal","11","name",facilityLocation);
    Port port2 = new Port("Asia","China","22","name",facilityLocation2);
    //CargoManifest
    CargoManifest cargoManifest = new CargoManifest("22",port,null);
    CargoManifest cargoManifest2 = new CargoManifest("33",port2, null);


    @Test
    void checkMMSITest() {

        //Arrange
        //Act
        try {
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

/*    @Test
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

    }*/

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
                "Position{latitude=0.0, longitude=0.0, heading=0.0, SOG=0.0, COG=1.0}";


        //Act + Assert
        assertEquals(expected, shipgeral.writeAllPos(li, null));
        assertEquals(expected, shipgeral.writeAllPos(null, lf));
        assertEquals(expected, shipgeral.writeAllPos(null, null));
        assertEquals(expected2, shipgeral.writeAllPos(li, lf));


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

        if (actual && shipgeral.getMmsi() == shipgeral2.getMmsi() && Objects.equals(shipgeral.getImo(), shipgeral2.getImo()) && shipgeral.getNumGen() == shipgeral2.getNumGen() && shipgeral.getGenPowerOutput() == shipgeral2.getGenPowerOutput() && shipgeral.getLength() == shipgeral2.getLength() && shipgeral.getWidth() == shipgeral2.getWidth() && shipgeral.getCapacity() == shipgeral2.getCapacity() && shipgeral.getDraft() == shipgeral2.getDraft() && Objects.equals(shipgeral.getName(), shipgeral2.getName()) && Objects.equals(shipgeral.getCallSign(), shipgeral2.getCallSign()) && Objects.equals(shipgeral.getVesselType(), shipgeral2.getVesselType()))
            fail();

    }

    @Test
    void equalsFalseExpectedTrue() {

        boolean actual = shipgeral.equals(shipgeral);

        if (!actual) {
            fail();
        }

    }

    @Test
    void checkIMOMutant() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            if (shipgeral.checkIMO("12")) fail();
        });

    }

    @Test
    void equalsMutantNull() {

        if (shipgeral.equals(null)) fail();

    }

    @Test
    void equalsReverseReturn() {

        boolean actual = shipgeral.equals(shipgeral);

        if (!actual) fail();

    }

    @Test
    void equalsAllSameExpectMMSI() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && shipgeral.getMmsi() != shipgeral2.getMmsi()) fail();

    }

    @Test
    void equalsAllSameExpectMMSI2() {

        boolean actual = shipgeral.equals(new Ship(111113111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1));

        if (actual && shipgeral.getMmsi() != shipgeral2.getMmsi()) fail();

    }

    @Test
    void equalsAllSameExpectMMSI3() {

        boolean actual = shipgeral.equals(new Ship(111113111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1));

        if (actual && shipgeral.getMmsi() == shipgeral2.getMmsi()) fail();

    }

    @Test
    void equalsAllSameExpectIMO() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && !shipgeral.getImo().equals(shipgeral2.getImo())) fail();

    }

    @Test
    void equalsAllSameExpectIMO2() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1211111", 1, 1, "A", "A", 1, 1, 1, 1));

        if (actual && !shipgeral.getImo().equals(shipgeral2.getImo())) fail();

    }

    @Test
    void equalsAllSameExpectIMO3() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1211111", 1, 1, "A", "A", 1, 1, 1, 1));

        if (actual && shipgeral.getImo().equals(shipgeral2.getImo())) fail();

    }


    @Test
    void equalsAllSameExpectCallSign() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && !shipgeral.getCallSign().equals(shipgeral2.getCallSign())) fail();

    }

    @Test
    void equalsAllSameExpectCallSign2() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "b", "A", 1, 1, 1, 1));

        if (actual && !shipgeral.getCallSign().equals(shipgeral2.getCallSign())) fail();

    }

    @Test
    void equalsAllSameExpectCallSign3() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "b", "A", 1, 1, 1, 1));

        if (actual && shipgeral.getCallSign().equals(shipgeral2.getCallSign())) fail();

    }

    @Test
    void equalsAllSameExpectNumGens() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && shipgeral.getNumGen() != shipgeral2.getNumGen()) fail();

    }

    @Test
    void equalsAllSameExpectNumsGen2() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 2, 1, "A", "A", 1, 1, 1, 1));

        if (actual && shipgeral.getNumGen() != shipgeral2.getNumGen()) fail();

    }

    @Test
    void equalsAllSameExpectNumsGen3() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 2, 1, "A", "A", 1, 1, 1, 1));

        if (actual && shipgeral.getNumGen() == shipgeral2.getNumGen()) fail();

    }

    @Test
    void equalsAllSameExpectNumGenPowerOutput() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && shipgeral.getGenPowerOutput() != shipgeral2.getGenPowerOutput()) fail();

    }

    @Test
    void equalsAllSameExpectNumGenPowerOutput2() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 2, "A", "A", 1, 1, 1, 1));

        if (actual && shipgeral.getGenPowerOutput() != shipgeral2.getGenPowerOutput()) fail();

    }

    @Test
    void equalsAllSameExpectNumGenPowerOutput3() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 2, "A", "A", 1, 1, 1, 1));

        if (actual && shipgeral.getGenPowerOutput() == shipgeral2.getGenPowerOutput()) fail();

    }

    @Test
    void equalsAllSameExpectWidth() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && shipgeral.getWidth() != shipgeral2.getWidth()) fail();

    }

    @Test
    void equalsAllSameExpectWidth2() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 2, 1, 1));

        if (actual && shipgeral.getWidth() != shipgeral2.getWidth()) fail();

    }

    @Test
    void equalsAllSameExpectWidth3() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 2, 1, 1));

        if (actual && shipgeral.getWidth() == shipgeral2.getWidth()) fail();

    }

    @Test
    void equalsAllSameExpectCapacity() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && shipgeral.getCapacity() != shipgeral2.getCapacity()) fail();

    }

    @Test
    void equalsAllSameExpectCapacity2() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 2, 1));

        if (actual && shipgeral.getCapacity() != shipgeral2.getCapacity()) fail();

    }

    @Test
    void equalsAllSameExpectCapacity3() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 2, 1));

        if (actual && shipgeral.getCapacity() == shipgeral2.getCapacity()) fail();

    }

    @Test
    void equalsAllSameExpectDraft() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && shipgeral.getDraft() != shipgeral2.getDraft()) fail();

    }

    @Test
    void equalsAllSameExpectDraft2() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 2));

        if (actual && shipgeral.getDraft() != shipgeral2.getDraft()) fail();

    }

    @Test
    void equalsAllSameExpectDraft3() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 2));

        if (actual && shipgeral.getDraft() == shipgeral2.getDraft()) fail();

    }

    @Test
    void equalsAllSameExpectName() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && shipgeral.getName().equals(shipgeral2.getName())) fail();

    }

    @Test
    void equalsAllSameExpectName2() {

        boolean actual = shipgeral.equals(new Ship(111111111, "nAmE", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1));

        if (actual && shipgeral.getName().equals(shipgeral2.getName())) fail();

    }

    @Test
    void equalsAllSameExpectName3() {

        boolean actual = shipgeral.equals(new Ship(111111111, "nAmE", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1));

        if (actual && !shipgeral.getName().equals(shipgeral2.getName())) fail();

    }

    @Test
    void equalsAllSameExpectVesselType() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && shipgeral.getVesselType().equals(shipgeral2.getVesselType())) fail();

    }

    @Test
    void equalsAllSameExpectVesselType2() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "b", 1, 1, 1, 1));

        if (actual && shipgeral.getVesselType().equals(shipgeral2.getVesselType())) fail();

    }

    @Test
    void equalsAllSameExpectVesselType3() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "b", 1, 1, 1, 1));

        if (actual && !shipgeral.getVesselType().equals(shipgeral2.getVesselType())) fail();

    }

    @Test
    void equalsAllSameExpectLength() {

        boolean actual = shipgeral.equals(shipgeral2);

        if (actual && shipgeral.getLength() != shipgeral2.getLength()) fail();

    }

    @Test
    void equalsAllSameExpectLength2() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 2, 1, 1, 1));

        if (actual && shipgeral.getLength() != shipgeral2.getLength()) fail();

    }

    @Test
    void equalsAllSameExpectLength3() {

        boolean actual = shipgeral.equals(new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 2, 1, 1, 1));

        if (actual && shipgeral.getLength() == shipgeral2.getLength()) fail();

    }

    @Test
    void checkMMSINegativeCase() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boolean actual = shipgeral.checkMMSI(9999989);
            if (!actual) fail();
        });
    }

    @Test
    void checkMMSIBoundNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boolean actual = shipgeral.checkMMSI(99999999);

            if (actual) fail();

        });


    }

    @Test
    void checkMMSIBound2ndOptionOut() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boolean actual = shipgeral.checkMMSI(1000000000);

            if (actual) fail();
        });


    }

    /*
    @Test
    void getDateCollectionsEmpty() {


        List<Position> positionList = shipgeral.getDate();

        if (!positionList.equals(Collections.emptyList())) fail();

    }

    @Test
    void getDateCollectionsEmpty2() {


        List<Position> positionList = shipgeral.getDate();

        if (!positionList.equals(Collections.emptyList())) assertNotNull(positionList);

    }

    @Test
    void getDateCollectionsEmpty3() {

        shipgeral.insertPosition(posgeral);
        List<Position> positionList = shipgeral.getDate();

        if (!positionList.equals(Collections.emptyList())) assertNotNull(positionList);

    }

    @Test
    void getDateCollectionsEmpty4() {

        shipgeral.insertPosition(posgeral);
        List<Position> positionList = shipgeral.getDate();

        if (!positionList.equals(Collections.emptyList())) fail();

    }

    @Test
    void getAssertNotEquals() {

        shipgeral.insertPosition(posgeral);
        List<Position> positionList = shipgeral.getDate();

        assertNotEquals(positionList, Collections.emptyList());

    }

    @Test
    void getAssertNotEquals2() {

        shipgeral.insertPosition(posgeral);
        shipgeral.insertPosition(posgeral2);
        List<Position> positionList = shipgeral.getDate();

        System.out.println(positionList);


        assertNotEquals(Collections.emptyList(),  positionList);
    }*/

    @Test
    void checkImoFirstCase() {

        String expected = "IMO1111111";

        try {
            boolean actual = shipgeral.checkIMO(expected);
            if (!actual) fail();
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    void checkImoSecondCase() {

        String expected = "IMO1111111";

        try {
            boolean actual = shipgeral.checkIMO(expected);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    void checkImoThirdCase() {

        String expected = "IMAAAAAAAA";

        try {
            boolean actual = shipgeral.checkIMO(expected);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    void checkImoReturn() {

        String expected = "IMO1111111";

        boolean actual = shipgeral.checkIMO(expected);

        if (!actual) fail();

    }


    @Test
    void checkMMSIReturn() {

        boolean actual = shipgeral.checkMMSI(111111111);

        if (!actual) fail();

    }


    @Test
    void testToString() {

        String expected = "Ship{cargo='null', MMSI=111111111, name='name', IMO='IMO1111111', numGen=1, genPowerOutput=1, callSign='A', vesselType='A', length=1.0, width=1.0, capacity=1.0, draft=1.0}";
        assertEquals(expected, shipgeral.toString());
    }

    @Test
    void writePosMessageCase1() {

        String actual = shipgeral.writeAllPos(posgeral.date, posgeral3.date);

        if (actual.equals("")) fail();

    }

    @Test
    void writePosMessageCase2() {

        String actual = shipgeral.writeAllPos(posgeral.date, posgeral2.date);

        if (!actual.equals("Positional Message:")) fail();

    }

    @Test
    void calendarMinutes() {
        String actual = shipgeral.writeAllPos(posgeral3.date, posgeral4.date);
    }

    @Test
    void calendarSeconds() {
        String actual = shipgeral.writeAllPos(posgeral3.date, posgeral4.date);
    }

    @Test
    void equalsMutation() {

        Ship shipgeral3 = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

        boolean actual = shipgeral.equals(shipgeral3);

        if (!actual) fail();

    }

    @Test
    void getCalculatedDistance(){

        boolean actual = shipgeral.calculateTravelledDistance();

        assertFalse(actual);


    }

    @Test
    void getCalculatedDistanceMutation(){

        shipgeral.insertPosition(posgeral);
        shipgeral.insertPosition(posgeral2);
        shipgeral.insertPosition(posgeral3);
        shipgeral.insertPosition(posgeral4);

        boolean actual = shipgeral.calculateTravelledDistance();

        if(!actual) fail();

        assertTrue(actual);


    }

    @Test
    void setAllDynamicDataMutation(){

        shipgeral.insertPosition(posgeral);
        shipgeral.insertPosition(posgeral2);
        shipgeral.insertPosition(posgeral3);
        shipgeral.insertPosition(posgeral4);

        boolean actual = shipgeral.setShipData();

        if(!actual) fail();

        assertTrue(actual);


    }

    @Test
    void setAllDynamicDataMutation2(){

        boolean actual = shipgeral.setShipData();

        if(actual) fail();

        assertFalse(actual);
    }

    @Test
    void addLoadedContainer(){

        //Assert
        shipgeral.getCargoManifestAVL().insert(cargoManifest);
        //Arrange
        boolean actual = shipgeral.addLoadedContainer(container,port);
        boolean actual2 = shipgeral.addLoadedContainer(container,port2);
        //Act
        assertEquals(true,actual);
        assertEquals(false,actual2);
    }

    @Test
    void addOffLoadedContainerTest(){

        //Assert
        shipgeral.getCargoManifestAVL().insert(cargoManifest);
        //Arrange
        boolean actual = shipgeral.addOffLoadedContainer(container,port);
        boolean actual2 = shipgeral.addLoadedContainer(container,port2);
        //Act
        assertEquals(true,actual);
        assertEquals(false,actual2);

    }

    @Test
    void giveCargoOffLoadedSignTest(){


    }
}
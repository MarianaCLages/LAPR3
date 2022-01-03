package lapr.project.model;

import lapr.project.data.CargoManifest;
import lapr.project.shared.ContainerPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CargoManifestTest {

    ContainerPosition cp = new ContainerPosition(1, 1, 1);
    Container containerPos = new Container("20BD", 1000, 1000, 100, "20RF", false, false);
    Country c1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);
    FacilityLocation f1 = new FacilityLocation(11, 11);
    Port p1 = new Port("a", "a", "1", c1, f1, 0);
    CargoManifest cargo1 = new CargoManifest("11", p1, null);
    CargoManifest cargo2 = new CargoManifest("21", p1, null);
    FacilityLocation f2 = new FacilityLocation(20, 20);
    Port p2 = new Port("a", "a", "2", c1, f2, 0);

    @Test
    void getIdentificationTest() {
        //Arrange
        String expected = "11";
        //Act + Assert
        assertEquals(expected, cargo1.getIdentification());
    }

    @Test
    void getOffLoadedTest() {
        //Assert
        String expected = "Container{identification='20BD', payload=1000, tare=1000, gross=100, isoCode='20RF', position=ContainerPosition{xPos=0, yPos=0, zPos=0}}\n";
        //Act
        cargo1.getOffloaded().insert(containerPos);
        //Assert
        assertEquals(expected, cargo1.getOffloaded().toString());
    }

    @Test
    void getLoadedTest() {
        //Assert
        String expected = "Container{identification='20BD', payload=1000, tare=1000, gross=100, isoCode='20RF', position=ContainerPosition{xPos=0, yPos=0, zPos=0}}\n";
        //Act
        cargo1.getLoaded().insert(containerPos);
        System.out.println(cargo1.getLoaded().toString());
        //Assert
        assertEquals(expected, cargo1.getLoaded().toString());
    }

    @Test
    void offLoadSignTest() {
        //Arrange
        boolean expected1 = false;
        boolean expected2 = true;
        cargo2.getOffloaded().insert(containerPos);

        //Act + Assert
        assertEquals(expected1, cargo1.offLoadSign());
        assertEquals(expected2, cargo2.offLoadSign());
    }

    @Test
    void loadSignTest() {

        //Arrange
        boolean expected1 = false;
        boolean expected2 = true;
        cargo2.getLoaded().insert(containerPos);

        //Act + Assert
        assertEquals(expected1, cargo1.loadSign());
        assertEquals(expected2, cargo2.loadSign());
    }

    @Test
    void setDateTest() {
        //Arrange
        //Act
        cargo1.setDate(new Date(2020, Calendar.DECEMBER, 25));
        //Assert
        assertEquals(new Date(2020, Calendar.DECEMBER, 25), cargo1.getDate());
    }

    @Test
    void setIdentificationTest() {
        //Arrange
        //Act
        cargo1.setIdentification("01");
        //Assert
        assertEquals("01", cargo1.getIdentification());
    }

    @Test
    void setPortTest() {
        //Arrange
        //Act
        cargo1.setPort(p2);
        //Assert
        assertEquals(p2, cargo1.getPort());
    }

    @Test
    void countContainersTest() {
        //Arrange
        int expected = 0;
        //Act
        int actual = cargo1.countContainers();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void countContainersMutant() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Container containerReal = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

        Container containerEqualsTrue = new Container("20BD", 1000, 1000, 100, "20RF", true, true);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        Ship ship = new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", 12, 12, "9HA2954", "70", 334, 42, 15, 20);
        CargoManifest cargoManifest = new CargoManifest("1Ab", port, ship, true);

        ship.getCargoManifestAVL().insert(cargoManifest);

        ship.addLoadedContainer(containerReal, port);
        ship.addOffLoadedContainer(containerReal, port);

        ship.addLoadedContainer(containerEqualsTrue, port);
        ship.addOffLoadedContainer(containerEqualsTrue, port);

        cargoManifest.addContainersOffLoaded(containerReal);

        cargoManifest.addContainersLoaded(containerEqualsTrue);

        if (cargoManifest.countContainers() == 0) fail();


    }

    @Test
    void countContainersMutantAVLEmpties() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Container containerReal = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

        Container containerEqualsTrue = new Container("20BD", 1000, 1000, 100, "20RF", true, true);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        Ship ship = new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", 12, 12, "9HA2954", "70", 334, 42, 15, 20);

        CargoManifest cargoManifest = new CargoManifest("1Ab", port, null);

        ship.getCargoManifestAVL().insert(cargoManifest);

        ship.addLoadedContainer(containerReal, port);
        ship.addOffLoadedContainer(containerReal, port);

        ship.addLoadedContainer(containerEqualsTrue, port);
        ship.addOffLoadedContainer(containerEqualsTrue, port);


        if (cargoManifest.getOffloaded().isEmpty()) fail();

        if (cargoManifest.getLoaded().isEmpty()) fail();


    }

    @Test
    void equalsSameObject() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        CargoManifest cargoManifest = new CargoManifest("1Ab", port, null);

        assertEquals(cargoManifest, cargoManifest);


    }

    @Test
    void equalsDifferentObject() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        CargoManifest cargoManifest = new CargoManifest("1Ab", port, null);

        assertNotEquals(null, cargoManifest);


    }

    @Test
    void equalsSameObjectByMethod() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        CargoManifest cargoManifest = new CargoManifest("1Ab", port, null);

        assertEquals(cargoManifest, cargoManifest);


    }

    @Test
    void equalsDifferentObjectByMethod() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        CargoManifest cargoManifest = new CargoManifest("1Ab", port, null);

        assertFalse(cargoManifest.equals(new Object()));


    }

    @Test
    void equalsDifferentObjectByMethod2() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        Port port2 = new Port("29111", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);


        CargoManifest cargoManifest = new CargoManifest("1AA", port, null);

        CargoManifest cargoManifest2 = new CargoManifest("1Ab", port2, null);

        assertFalse(cargoManifest.equals(cargoManifest2));


    }

    @Test
    void equalsDifferentObjectByMethod3() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        CargoManifest cargoManifest = new CargoManifest("1Ab", port, null);

        assertFalse(cargoManifest.equals(new Object()));


    }

    @Test
    void equalsDifferentObjectByMethod4() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        Port port2 = new Port("29111", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);


        CargoManifest cargoManifest = new CargoManifest("1AA", port, null);

        CargoManifest cargoManifest2 = new CargoManifest("1Ab", port2, null);

        assertFalse(cargoManifest.equals(cargoManifest2));


    }

    @Test
    void equalsDifferentObjectByMethodMutant1() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        Port port2 = new Port("29111", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);


        CargoManifest cargoManifest = new CargoManifest("1AA", port, null);

        CargoManifest cargoManifest2 = new CargoManifest("1Ab", port2, null);

        boolean actual = cargoManifest2.equals(cargoManifest);

        if (actual) fail();


    }

    @Test
    void equalsDifferentObjectByMethodMutant3() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        Port port2 = new Port("29111", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);


        CargoManifest cargoManifest = new CargoManifest("1AA", port, null);

        boolean actual = cargoManifest.equals(port);

        if (actual) fail();


    }

    @Test
    void equalsDifferentObjectByMethodMutant4() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        Port port2 = new Port("29111", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);


        CargoManifest cargoManifest = new CargoManifest("1AA", port, null);

        boolean actual = cargoManifest.equals(null);

        if (actual) fail();


    }


    @Test
    void equalsDifferentObjectByMethodMutant2() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        Port port2 = new Port("29111", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);


        CargoManifest cargoManifest = new CargoManifest("1AA", port, null);

        CargoManifest cargoManifest2 = new CargoManifest("1Ab", port, null);

        if (cargoManifest.equals(cargoManifest2)) fail();


    }

    @Test
    void equalsDifferentObjectByMethodMutant5() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        Port port2 = new Port("29111", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);


        CargoManifest cargoManifest = new CargoManifest("1AA", port, null);

        CargoManifest cargoManifest2 = new CargoManifest("1AA", port2, null);

        if (cargoManifest.equals(cargoManifest2)) fail();


    }

    @Test
    void equalsDifferentObjectByMethodMutant6() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        Port port2 = new Port("29111", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        String dt = "2020-01-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = sdf.parse(dt);
        } catch (ParseException e) {

        }

        CargoManifest cargoManifest = new CargoManifest("1AA", port, null);

        CargoManifest cargoManifest2 = new CargoManifest("1AA", port2, date);

        if (cargoManifest.equals(cargoManifest2)) fail();


    }

    @Test
    void hashCodeMutant() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);


        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        CargoManifest cargoManifest = new CargoManifest("1AA", port, null);

        assertNotNull(cargoManifest.hashCode());


    }

    @Test
    void writeCargoByYear() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        String dt = "2020-01-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = sdf.parse(dt);
        } catch (ParseException e) {

        }

        Container containerReal = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

        Container containerEqualsTrue = new Container("20BD", 1000, 1000, 100, "20RF", true, true);

        Ship ship = new Ship(999999999, "name", "IMO1234234", "AABB", "70", 10, 10, 10, "10", 'A');

        CargoManifest cargoManifest = new CargoManifest("1Ab", port, date);

        ship.getCargoManifestAVL().insert(cargoManifest);

        ship.addLoadedContainer(containerReal, port);
        ship.addOffLoadedContainer(containerReal, port);

        ship.addLoadedContainer(containerEqualsTrue, port);
        ship.addOffLoadedContainer(containerEqualsTrue, port);


        if (ship.writeCargoByYear(2020) == null) fail();


    }

    @Test
    void writeCargoByYearMutant() {
        Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

        Port port = new Port("29002", "Liverpool", "Europe", co1, new FacilityLocation(53.46666667, -3.033333333), 0);

        String dt = "2020-01-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = sdf.parse(dt);
        } catch (ParseException e) {

        }

        Container containerReal = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

        Container containerEqualsTrue = new Container("20BD", 1000, 1000, 100, "20RF", true, true);

        CargoManifest cargoManifest = new CargoManifest("111", port, date);

        Ship ship = new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", 12, 12, "9HA2954", "70", 334, 42, 15, 20);

        ship.getCargoManifestAVL().insert(cargoManifest);

        ship.addLoadedContainer(containerReal, port);
        ship.addOffLoadedContainer(containerReal, port);

        ship.addLoadedContainer(containerEqualsTrue, port);
        ship.addOffLoadedContainer(containerEqualsTrue, port);


        String actual = "\n" +
                "Average Containers by Cargo Manifest:NaN";


        if (ship.writeCargoByYear(2019) == actual) fail();


    }


    @Test
    void getInTransport() {
        CargoManifest cargoManifest = new CargoManifest("aaaaa", p1, new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", "9HA2954", "70", 334, 42, 14.7, "70", 'B'), true);
        Assertions.assertTrue(cargoManifest.getInTransport());
    }

    @Test
    void getInTransportFalse() {
        CargoManifest cargoManifest = new CargoManifest("aaaaa", p1, new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", "9HA2954", "70", 334, 42, 14.7, "70", 'B'), false);
        assertFalse(cargoManifest.getInTransport());
    }

    @Test
    void getShip() {
        Ship ship3 = new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", "9HA2954", "70", 334, 42, 14.7, "70", 'B');
        CargoManifest cargoManifest = new CargoManifest("aaaaa", p1, ship3, true);
        assertEquals(cargoManifest.getShip(), ship3);
    }

    @Test
    void setShip() {
        Ship ship3 = new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", "9HA2954", "70", 334, 42, 14.7, "70", 'B');
        CargoManifest cargoManifest = new CargoManifest("aaaaa", p1, ship3, true);
        Ship ship4 = new Ship(257881000, "SPAR ARIES", "IMO9701920", "LATO7", "70", 199, 32, 13.3, "NA", 'B');
        cargoManifest.setShip(ship4);
        assertEquals(cargoManifest.getShip(), ship4);
    }


    @Test
    void compareTo() {
        int actual = cargo1.compareTo(cargo2);
        int reverseActual = cargo2.compareTo(cargo1);
        int actualMutation = cargo1.compareTo(cargo1);
        if (actualMutation != 0) fail();
        if (reverseActual != 1) fail();
        if (actual != -1) fail();
        assertNotNull(actual);
        assertEquals(-1, actual);
        assertNotNull(reverseActual);
        assertEquals(1, reverseActual);
    }

    @Test
    void addContainersOffLoaded() {

        Container containerPos2 = new Container("20BD", 1001, 1000, 100, "20RF", false, false);

        Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);


        cargo1.setShip(shipgeral);

        cargo1.getShip().addLoadedContainer(containerPos, p1);
        cargo1.getShip().addLoadedContainer(containerPos, p2);

        cargo1.getShip().addOffLoadedContainer(containerPos, p1);
        cargo1.getShip().addOffLoadedContainer(containerPos, p2);

        cargo1.getShip().addContainerPosition(cp);

        boolean actual = cargo1.addContainersOffLoaded(containerPos2);

        if (actual) fail();

        containerPos.setPosition(cp);

        boolean actualMutation = cargo1.addContainersOffLoaded(containerPos);

        if(actualMutation) fail();

        containerPos2.setPosition(new ContainerPosition(2, 1, 1));

        int xPos2 = 1;
        int yPos2 = 1;
        int zPos2 = 1;

        cargo1.getShip().addContainerPosition(containerPos2.getPosition());

        boolean actualMutation2 = cargo1.addContainersOffLoaded(containerPos2);

        try {
            cargo1.getShip().getContainerPositionAVL().find(containerPos2.getPosition());
        } catch (Exception e) {
            fail();
        }

        /*
        if (containerPos.getPosition().getxPos() == xPos2) {
            fail();
        } else if (containerPos.getPosition().getyPos() == yPos2) {
            fail();
        } else if (containerPos.getPosition().getzPos() == zPos2) {
            fail();
        }
         */


    }

    @Test
    void addContainerLoaded() {

        Container containerPos2 = new Container("20BD", 1001, 1000, 100, "20RF", false, false);

        Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);


        cargo1.setShip(shipgeral);

        cargo1.getShip().addLoadedContainer(containerPos, p1);
        cargo1.getShip().addLoadedContainer(containerPos, p2);

        cargo1.getShip().addOffLoadedContainer(containerPos, p1);
        cargo1.getShip().addOffLoadedContainer(containerPos, p2);

        cargo1.getShip().addContainerPosition(cp);

        boolean actual = cargo1.addContainersLoaded(containerPos2);

        if (actual) fail();

        containerPos.setPosition(cp);

        boolean actualMutation = cargo1.addContainersLoaded(containerPos);

        if(actualMutation) fail();

        containerPos2.setPosition(new ContainerPosition(2, 1, 1));

        int xPos2 = 1;
        int yPos2 = 1;
        int zPos2 = 1;

        cargo1.getShip().addContainerPosition(containerPos2.getPosition());

        boolean actualMutation2 = cargo1.addContainersLoaded(containerPos2);

        try {
            cargo1.getShip().getContainerPositionAVL().find(containerPos2.getPosition());
        } catch (Exception e) {
            fail();
        }

        /*
        if (containerPos.getPosition().getxPos() == xPos2) {
            fail();
        } else if (containerPos.getPosition().getyPos() == yPos2) {
            fail();
        } else if (containerPos.getPosition().getzPos() == zPos2) {
            fail();
        }
         */


    }

}

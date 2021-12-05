package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CargoManifestTest {

    ContainerPosition cp = new ContainerPosition(1, 1, 1);
    Container containerPos = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

    FacilityLocation f1 = new FacilityLocation(11, 11);
    Port p1 = new Port("a", "a", "1", "a", f1);
    FacilityLocation f2 = new FacilityLocation(20, 20);
    Port p2 = new Port("a", "a", "2", "a", f2);

    CargoManifest cargo1 = new CargoManifest("11", p1, null);
    CargoManifest cargo2 = new CargoManifest("11", p1, null);

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
        String expected = "Container{identification='20BD', payload=1000, tare=1000, gross=100, isoCode='20RF', position=null}\n";
        //Act
        cargo1.getOffloaded().insert(containerPos);
        //Assert
        assertEquals(expected, cargo1.getOffloaded().toString());
    }

    @Test
    void getLoadedTest() {
        //Assert
        String expected = "Container{identification='20BD', payload=1000, tare=1000, gross=100, isoCode='20RF', position=null}\n";
        //Act
        cargo1.getLoaded().insert(containerPos);
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
    void countContainersMutant(){

        Container containerReal = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

        Container containerEqualsTrue = new Container("20BD", 1000, 1000, 100, "20RF", true, true);

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        Ship ship = new Ship(999999999,"name","IMO1234234","AABB","70",10,10,10,"10",'A');

        CargoManifest cargoManifest = new CargoManifest("1Ab",port,null);

        ship.getCargoManifestAVL().insert(cargoManifest);

        ship.addLoadedContainer(containerReal,port);
        ship.addOffLoadedContainer(containerReal,port);

        ship.addLoadedContainer(containerEqualsTrue,port);
        ship.addOffLoadedContainer(containerEqualsTrue,port);

        cargoManifest.addContainersOffLoaded(containerReal);

        cargoManifest.addContainersLoaded(containerEqualsTrue);

        if(cargoManifest.countContainers() == 0) fail();


    }

    @Test
    void countContainersMutantAVLEmpties(){

        Container containerReal = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

        Container containerEqualsTrue = new Container("20BD", 1000, 1000, 100, "20RF", true, true);

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        Ship ship = new Ship(999999999,"name","IMO1234234","AABB","70",10,10,10,"10",'A');

        CargoManifest cargoManifest = new CargoManifest("1Ab",port,null);

        ship.getCargoManifestAVL().insert(cargoManifest);

        ship.addLoadedContainer(containerReal,port);
        ship.addOffLoadedContainer(containerReal,port);

        ship.addLoadedContainer(containerEqualsTrue,port);
        ship.addOffLoadedContainer(containerEqualsTrue,port);

        cargoManifest.addContainersOffLoaded(containerReal);

        cargoManifest.addContainersLoaded(containerEqualsTrue);

        if(cargoManifest.getOffloaded().isEmpty()) fail();

        if(cargoManifest.getLoaded().isEmpty()) fail();


    }

    @Test
    void equalsSameObject(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        CargoManifest cargoManifest = new CargoManifest("1Ab",port,null);

        assertEquals(cargoManifest,cargoManifest);


    }

    @Test
    void equalsDifferentObject(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        CargoManifest cargoManifest = new CargoManifest("1Ab",port,null);

        assertNotEquals(null,cargoManifest);


    }

    @Test
    void equalsSameObjectByMethod(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        CargoManifest cargoManifest = new CargoManifest("1Ab",port,null);

        assertEquals(cargoManifest,cargoManifest);


    }

    @Test
    void equalsDifferentObjectByMethod(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        CargoManifest cargoManifest = new CargoManifest("1Ab",port,null);

        assertFalse(cargoManifest.equals(new Object()));


    }

    @Test
    void equalsDifferentObjectByMethod2(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        Port port2 = new Port("29111", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));


        CargoManifest cargoManifest = new CargoManifest("1AA",port,null);

        CargoManifest cargoManifest2 = new CargoManifest("1Ab",port2,null);

        assertFalse(cargoManifest.equals(cargoManifest2));


    }

    @Test
    void equalsDifferentObjectByMethod3(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        CargoManifest cargoManifest = new CargoManifest("1Ab",port,null);

        assertFalse(cargoManifest.equals(new Object()));


    }

    @Test
    void equalsDifferentObjectByMethod4(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        Port port2 = new Port("29111", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));


        CargoManifest cargoManifest = new CargoManifest("1AA",port,null);

        CargoManifest cargoManifest2 = new CargoManifest("1Ab",port2,null);

        assertFalse(cargoManifest.equals(cargoManifest2));


    }

    @Test
    void equalsDifferentObjectByMethodMutant1(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        Port port2 = new Port("29111", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));


        CargoManifest cargoManifest = new CargoManifest("1AA",port,null);

        CargoManifest cargoManifest2 = new CargoManifest("1Ab",port2,null);

        boolean actual = cargoManifest2.equals(cargoManifest);

        if(actual) fail();


    }

    @Test
    void equalsDifferentObjectByMethodMutant3(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        Port port2 = new Port("29111", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));


        CargoManifest cargoManifest = new CargoManifest("1AA",port,null);

        boolean actual = cargoManifest.equals(port);

        if(actual) fail();


    }

    @Test
    void equalsDifferentObjectByMethodMutant4(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        Port port2 = new Port("29111", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));


        CargoManifest cargoManifest = new CargoManifest("1AA",port,null);

        boolean actual = cargoManifest.equals(null);

        if(actual) fail();


    }


    @Test
    void equalsDifferentObjectByMethodMutant2(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        Port port2 = new Port("29111", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));


        CargoManifest cargoManifest = new CargoManifest("1AA",port,null);

        CargoManifest cargoManifest2 = new CargoManifest("1Ab",port,null);

       if(cargoManifest.equals(cargoManifest2)) fail();


    }

    @Test
    void equalsDifferentObjectByMethodMutant5(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        Port port2 = new Port("29111", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));


        CargoManifest cargoManifest = new CargoManifest("1AA",port,null);

        CargoManifest cargoManifest2 = new CargoManifest("1AA",port2,null);

        if(cargoManifest.equals(cargoManifest2)) fail();


    }

    @Test
    void equalsDifferentObjectByMethodMutant6(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        Port port2 = new Port("29111", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        String dt = "2020-01-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = sdf.parse(dt);
        } catch (ParseException e){

        }

        CargoManifest cargoManifest = new CargoManifest("1AA",port,null);

        CargoManifest cargoManifest2 = new CargoManifest("1AA",port2,date);

        if(cargoManifest.equals(cargoManifest2)) fail();


    }

    @Test
    void hashCodeMutant(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        CargoManifest cargoManifest = new CargoManifest("1AA",port,null);

        assertNotNull(cargoManifest.hashCode());



    }

    @Test
    void writeCargoByYear(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        String dt = "2020-01-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = sdf.parse(dt);
        } catch (ParseException e){

        }

        Container containerReal = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

        Container containerEqualsTrue = new Container("20BD", 1000, 1000, 100, "20RF", true, true);

        Ship ship = new Ship(999999999,"name","IMO1234234","AABB","70",10,10,10,"10",'A');

        CargoManifest cargoManifest = new CargoManifest("1Ab",port,date);

        ship.getCargoManifestAVL().insert(cargoManifest);

        ship.addLoadedContainer(containerReal,port);
        ship.addOffLoadedContainer(containerReal,port);

        ship.addLoadedContainer(containerEqualsTrue,port);
        ship.addOffLoadedContainer(containerEqualsTrue,port);

        cargoManifest.addContainersOffLoaded(containerReal);

        cargoManifest.addContainersLoaded(containerEqualsTrue);

        if(ship.writeCargoByYear(2020) == null) fail();


    }

    @Test
    void writeCargoByYearMutant(){

        Port port = new Port("29002", "Liverpool", "Europe", "United Kingdom", new FacilityLocation(53.46666667, -3.033333333));

        String dt = "2020-01-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = sdf.parse(dt);
        } catch (ParseException e){

        }

        Container containerReal = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

        Container containerEqualsTrue = new Container("20BD", 1000, 1000, 100, "20RF", true, true);

        Ship ship = new Ship(999999999,"name","IMO1234234","AABB","70",10,10,10,"10",'A');

        CargoManifest cargoManifest = new CargoManifest("1Ab",port,date);

        ship.getCargoManifestAVL().insert(cargoManifest);

        ship.addLoadedContainer(containerReal,port);
        ship.addOffLoadedContainer(containerReal,port);

        ship.addLoadedContainer(containerEqualsTrue,port);
        ship.addOffLoadedContainer(containerEqualsTrue,port);

        cargoManifest.addContainersOffLoaded(containerReal);

        cargoManifest.addContainersLoaded(containerEqualsTrue);

        String actual = "\n" +
                "Average Containers by Cargo Manifest:NaN";

        assertEquals(null,ship.writeCargoByYear(2019));

        if(ship.writeCargoByYear(2019) == actual) fail();


    }


}

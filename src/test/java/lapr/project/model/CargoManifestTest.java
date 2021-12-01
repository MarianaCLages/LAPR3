package lapr.project.model;

import org.junit.jupiter.api.Test;

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
        cargo1.getOffLoaded().insert(containerPos);
        //Assert
        assertEquals(expected, cargo1.getOffLoaded().toString());
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
        cargo2.getOffLoaded().insert(containerPos);

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
}

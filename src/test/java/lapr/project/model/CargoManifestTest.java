package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CargoManifestTest {


    ContainerPosition cp = new ContainerPosition(1, 1, 1);
    Container containerPos = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

    FacilityLocation f1 = new FacilityLocation(11,11);
    Port p1 = new Port("a","a","1","a",f1);
    CargoManifest cargo1 = new CargoManifest("11",p1,null);
    CargoManifest cargo2 = new CargoManifest("11",p1,null);

    @Test
    void getIdentificationTest(){


        //Arrange
        String expected = "11";
        //Act + Assert
        assertEquals(expected,cargo1.getIdentification());

    }

    @Test
    void getOffLoadedTest(){

        //Assert
        String expected = "Container{identification='20BD', payload=1000, tare=1000, gross=100, isoCode='20RF', position=null}\n";
        //Act
        cargo1.getOffLoaded().insert(containerPos);
        //Assert
        assertEquals(expected,cargo1.getOffLoaded().toString());

    }

    @Test
    void getLoadedTest(){

        //Assert
        String expected = "Container{identification='20BD', payload=1000, tare=1000, gross=100, isoCode='20RF', position=null}\n";
        //Act
        cargo1.getLoaded().insert(containerPos);
        //Assert
        assertEquals(expected,cargo1.getLoaded().toString());
    }

    @Test
    void offLoadSignTest(){

        //Arrange
        boolean expected1 = false;
        boolean expected2 = true;
        cargo2.getOffLoaded().insert(containerPos);

        //Act + Assert
        assertEquals(expected1,cargo1.offLoadSign());
        assertEquals(expected2,cargo2.offLoadSign());
    }

    @Test
    void loadSignTest(){

        //Arrange
        boolean expected1 = false;
        boolean expected2 = true;
        cargo2.getLoaded().insert(containerPos);

        //Act + Assert
        assertEquals(expected1,cargo1.loadSign());
        assertEquals(expected2,cargo2.loadSign());
    }
}

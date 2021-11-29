package lapr.project.model.stores;
import lapr.project.model.CargoManifest;
import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CargoManifestStoreTest {


    CargoManifestStore cargoManifestStore = new CargoManifestStore();
    CargoManifestStore cargoManifestStore2 = new CargoManifestStore();

    FacilityLocation f1 = new FacilityLocation(11,11);
    Port p1 = new Port("a","a","1","a",f1);
    CargoManifest cargo1 = new CargoManifest("11",p1);

    @Test
    void addTest(){

        //Arrange
        //Act
        cargoManifestStore.add(cargo1);
        String expected = "Identification:11\n" +
                "Port=Facility{identification='a', name='a', continent='1', country='a', location=latitude = 11.0, longitude = 11.0}}";
        //Assert
        assertEquals(expected,cargoManifestStore.toString());
    }

    @Test
    void getCargoManifestAVL(){

        //Arrange
        cargoManifestStore.add(cargo1);
        //Act
        String expected = "Identification:11\n" +
                "Port=Facility{identification='a', name='a', continent='1', country='a', location=latitude = 11.0, longitude = 11.0}}\n";

        //Assert
        assertEquals(expected,cargoManifestStore.getCargoManifestByAVL().toString());
    }

    @Test
    void writeCargoManifests(){

        //Arrange
        cargoManifestStore.add(cargo1);
        //Act + Assert
        assertEquals(true,cargoManifestStore.writeCargoManifests());
        assertEquals(false,cargoManifestStore2.writeCargoManifests());
    }
}

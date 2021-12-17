package lapr.project.model.stores;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CargoManifestStoreTest {


    CargoManifestStore cargoManifestStore = new CargoManifestStore();
    CargoManifestStore cargoManifestStore2 = new CargoManifestStore();

    FacilityLocation f1 = new FacilityLocation(11, 11);
    Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

    Port p1 = new Port("a", "a", "1", co1, f1, 0);
    CargoManifest cargo1 = new CargoManifest("11", p1, null);

    @Test
    void addTest() {

        //Arrange
        //Act
        cargoManifestStore.add(cargo1);
        String expected = "CargoManifest{identification='11', port=Facility{identification='a', name='a', continent='1', country='United Kingdom', location=latitude = 11.0, longitude = 11.0}, date=null}";
        //Assert
        assertEquals(expected, cargoManifestStore.toString());
    }

    @Test
    void getCargoManifestAVL() {

        //Arrange
        cargoManifestStore.add(cargo1);
        //Act
        String expected = "CargoManifest{identification='11', port=Facility{identification='a', name='a', continent='1', country='United Kingdom', location=latitude = 11.0, longitude = 11.0}, date=null}\n";

        //Assert
        assertEquals(expected, cargoManifestStore.getCargoManifestByAVL().toString());
    }

    @Test
    void writeCargoManifests() {

        //Arrange
        cargoManifestStore.add(cargo1);
        //Act + Assert
        assertEquals(true, cargoManifestStore.writeCargoManifests());
        assertEquals(false, cargoManifestStore2.writeCargoManifests());
    }
}

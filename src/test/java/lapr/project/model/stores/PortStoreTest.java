package lapr.project.model.stores;

import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PortStoreTest {

    @Test
    void addPortTest() {
        PortStore portStore = new PortStore();
        Assertions.assertTrue(portStore.add(new Port("1sda", "Leixões", "Europe", "Portugal", new FacilityLocation(66, 87))));
    }

    @Test
    void addPortTestException() {
        PortStore portStore = new PortStore();
        Assertions.assertFalse(portStore.add(null));
    }

    @Test
    void fillTreeEmpty() {
        PortStore portStore = new PortStore();
        Assertions.assertFalse(portStore.fillTree());
    }

    @Test
    void fillTreeNotEmpty() {
        PortStore portStore = new PortStore();
        portStore.add(new Port("1sda", "Leixões", "Europe", "Portugal", new FacilityLocation(66, 87)));
        Assertions.assertTrue(portStore.fillTree());
    }

    @Test
    void fillTreeGetTree() {
        PortStore portStore = new PortStore();
        portStore.add(new Port("1sda", "Leixões", "Europe", "Portugal", new FacilityLocation(66, 87)));
        portStore.fillTree();
        Assertions.assertNotNull(portStore.getPortList());
    }
}
package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SearchContainerLocationForClientControllerTest {

    SearchContainerLocationForClientController ctrl = new SearchContainerLocationForClientController();

    @Test
    void getClientContainers() {

        try {

            Country c1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);
            FacilityLocation f1 = new FacilityLocation(11, 11);
            Port p1 = new Port("a", "a", "1", c1, f1, 0);
            CargoManifest cargo1 = new CargoManifest("11", p1, null);

            String actual = ctrl.printLocation(cargo1);

            if (actual == null || actual.equals("")) fail();

            CargoManifest cargoManifest = ctrl.findContainerVessel(new Container("20BD", 1000, 1000, 100, "20RF", false, false));

            ArrayList<String> list = ctrl.getClientContainers("1");

            if (list.isEmpty()) fail();
            if (list.equals(Collections.emptyList())) fail();


            if (cargoManifest == null) fail();

        } catch (Exception e) {

        }

    }

}
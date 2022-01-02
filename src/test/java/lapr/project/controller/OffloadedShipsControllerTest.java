package lapr.project.controller;

import lapr.project.data.CargoManifest;
import lapr.project.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class OffloadedShipsControllerTest {

    OffloadedContainersController ctrl = new OffloadedContainersController();

    Ship ship = new Ship(222222222, "name", "IMO2222222", 1, 1, "A", "A", 1, 1, 1, 1);

    String sdate = "31/11/2020 23:18";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));
    Position position = new Position(1, 0, 0, 1, 1, date);

    ContainerPosition containerPosition = new ContainerPosition(1, 1, 1);
    Container container = new Container("01", 1, 1, 1, "11", false, true);

    FacilityLocation facilityLocation = new FacilityLocation(2, 2);
    Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

    Port port = new Port("Asia", "Japan", "11", co1, facilityLocation, 0);

    CargoManifest cargoManifest = new CargoManifest("11", port, null);

/*    @Test
    void offLoadedShips() {
        //Arrange
        ship.insertPosition(position);
        container.setPosition(containerPosition);
        ship.getCargoManifestAVL().insert(cargoManifest);
        ctrl.getShipStore().addShip(ship);
        ship.addOffLoadedContainer(container, port);

        boolean expected = false;
        //Act
        boolean actual = ctrl.offLoadedShips(ship.getMmsi());
        //Assert
        assertEquals(expected, actual);
    }*/
}
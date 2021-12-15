package lapr.project.controller;

import lapr.project.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class LoadedShipsControllerTest {

    LoadedContainersController ctrl = new LoadedContainersController();

    Ship ship = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

    String sdate = "31/11/2020 23:16";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));
    Position position = new Position(1, 0, 0, 1, 1, date);

    ContainerPosition containerPosition = new ContainerPosition(1, 1, 1);
    Container container = new Container("01", 1, 1, 1, "11", false, false);

    FacilityLocation facilityLocation = new FacilityLocation(2, 2);
    Port port = new Port("Asia", "Japan", "11", "port", facilityLocation,0);

    CargoManifest cargoManifest = new CargoManifest("11", port, null);

/*    @Test
    void loadedShips() {
        //Arrange
        ship.insertPosition(position);
        container.setPosition(containerPosition);
        ship.getCargoManifestAVL().insert(cargoManifest);
        ctrl.getShipStore().addShip(ship);
        ship.addLoadedContainer(container, port);

        boolean expected = false;

        //Act
        boolean actual = ctrl.loadedShips(ship.getMmsi());

        //Assert
        assertEquals(expected, actual);
    }*/
}
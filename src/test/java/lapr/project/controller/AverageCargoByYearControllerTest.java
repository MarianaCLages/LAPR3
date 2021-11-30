package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AverageCargoByYearControllerTest {

    AverageCargoByYearController ctrl = new AverageCargoByYearController();

    Ship ship = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

    String sdate = "31/11/2020 23:16";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));
    Position position = new Position(1, 0, 0, 1, 1, date);

    ContainerPosition containerPosition = new ContainerPosition(1, 1, 1);
    Container container = new Container("01", 1, 1, 1, "11", false, false);

    FacilityLocation facilityLocation = new FacilityLocation(2, 2);
    Port port = new Port("Asia", "Japan", "11", "port", facilityLocation);

    CargoManifest cargoManifest = new CargoManifest("11", port, new Date(2020, Calendar.DECEMBER, 25));

    @Test
    void averageCargoByYearController() {
        //Arrange
        ship.insertPosition(position);
        container.setPosition(containerPosition);
        ship.getCargoManifestAVL().insert(cargoManifest);
        ctrl.getShipStore().addShip(ship);
        ship.addLoadedContainer(container, port);

        String expected = "CargoManifest{identification='11', port=Facility{identification='Asia', name='Japan', continent='11', country='port', location=latitude = 2.0, longitude = 2.0}, date=Sat Dec 25 00:00:00 WET 3920}\n" +
                "\n" +
                "Average Containers by Cargo Manifest:1.0";

        //Act
        String actual = ctrl.averageCargoByYear(ship.getMmsi(), cargoManifest.getDate().getYear());

        //Assert
        assertEquals(expected, actual);
    }
}
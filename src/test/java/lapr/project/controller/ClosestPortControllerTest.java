package lapr.project.controller;


import lapr.project.model.*;
import lapr.project.utils.mappers.dto.PortDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ClosestPortControllerTest {


    @Test
    void closestPortMock() {

        ClosestPortController closestPortController = mock(ClosestPortController.class);

        String sdate = "31/11/2020 23:16";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));

        when(closestPortController.getNearestPortByCallSign("AA", date)).thenReturn(new PortDTO("1", "1", "1", new FacilityLocation(1, 1)));

    }

    @Test
    void closestPortMemory() {

        ClosestPortController closestPortController = new ClosestPortController();

        String sdate = "31/11/2020 23:16";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));

        Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "Abv", "A", 1, 1, 1, 1);
        Position posgeral = new Position(1, 0, 0, 1, 1, date);

        shipgeral.getPosDate().addPosition(posgeral);

        Country c1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);
        Port port1 = new Port("29002", "Liverpool", "Europe", c1, new FacilityLocation(1, -2), 0);
        Port port2 = new Port("29002", "Liverpool", "Europe", c1, new FacilityLocation(2, -3), 0);
        Port port3 = new Port("29002", "Liverpool", "Europe", c1, new FacilityLocation(3, -4), 0);
        Port port = new Port("29002", "Liverpool", "Europe", c1, new FacilityLocation(5, -6), 0);

        App.getInstance().getCompany().getShipStore().addShip(shipgeral);
        App.getInstance().getCompany().getPortStore().add(port);
        App.getInstance().getCompany().getPortStore().add(port1);
        App.getInstance().getCompany().getPortStore().add(port2);
        App.getInstance().getCompany().getPortStore().add(port3);


        try {

            PortDTO portDTO = closestPortController.getNearestPortByCallSign("Abv", date);

            if (portDTO == null) Assertions.fail();

            Assertions.assertNotNull(portDTO);

        } catch (Exception e) {

        }

    }

}
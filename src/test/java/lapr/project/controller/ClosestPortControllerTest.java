package lapr.project.controller;


import lapr.project.model.FacilityLocation;
import lapr.project.utils.mappers.dto.PortDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ClosestPortControllerTest {

    @Test
    void closestPort() {

        ClosestPortController closestPortController = mock(ClosestPortController.class);

        String sdate = "31/11/2020 23:16";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));

        when(closestPortController.getNearestPortByCallSign("AA", date)).thenReturn(new PortDTO("1", "1", "1", "1", new FacilityLocation(1, 1)));
        
    }

}
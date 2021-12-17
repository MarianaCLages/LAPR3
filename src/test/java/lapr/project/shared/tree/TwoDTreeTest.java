package lapr.project.shared.tree;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoDTreeTest {


    String sdate = "31/11/2020 23:16";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));
    Position posgeral = new Position(20, 20, 0, 1, 1, date);
    Country co1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

    TwoDTree tdt = new TwoDTree();

    @Test
    void insert() {
        List<Port> portList = new ArrayList<>();
        Port port = new Port("11111", "a1", "a1", co1, new FacilityLocation(10, 13), 0);
        Port port2 = new Port("22222", "a2", "a2", co1, new FacilityLocation(11, 12), 0);
        Port port3 = new Port("33333", "a3", "a3", co1, new FacilityLocation(3, 20), 0);
        Port port4 = new Port("33333", "a4", "a4", co1, new FacilityLocation(2, 22), 0);
        Port port5 = new Port("33333", "a5", "a5", co1, new FacilityLocation(20, 20), 0);

        portList.add(port);
        portList.add(port2);
        portList.add(port3);
        portList.add(port4);
        portList.add(port5);

        tdt.insert(portList.toArray(new Port[0]));


        //Assert
        assertEquals(tdt.nearestNeighborPort(posgeral), port5);


    }


    @Test
    void nearestNeighbor() {
        //Arrange
        List<Port> portList = new ArrayList<>();
        Port port = new Port("11111", "a1", "a1", co1, new FacilityLocation(10, 13), 0);
        Port port2 = new Port("22222", "a2", "a2", co1, new FacilityLocation(11, 12), 0);
        Port port3 = new Port("33333", "a3", "a3", co1, new FacilityLocation(3, 20), 0);
        Port port4 = new Port("33333", "a4", "a4", co1, new FacilityLocation(2, 22), 0);
        Port port5 = new Port("33333", "a5", "a5", co1, new FacilityLocation(20, 20), 0);


        Position posgeral = new Position(19, 19, 0, 1, 1, date);
        portList.add(port3);
        portList.add(port2);
        portList.add(port);
        portList.add(port5);

        tdt.insert(portList.toArray(new Port[0]));


        //Act
        Port actual = tdt.nearestNeighborPort(posgeral);


        //Assert
        assertEquals(port5, actual);
    }

    @Test
    void toStringTest() {

        //Arrange
        List<Port> portList = new ArrayList<>();
        Port port = new Port("11111", "a1", "a1", co1, new FacilityLocation(10, 13), 0);
        Port port2 = new Port("22222", "a2", "a2", co1, new FacilityLocation(11, 12), 0);
        Port port3 = new Port("33333", "a3", "a3", co1, new FacilityLocation(3, 20), 0);
        Port port4 = new Port("33333", "a4", "a4", co1, new FacilityLocation(2, 22), 0);
        Port port5 = new Port("33333", "a5", "a5", co1, new FacilityLocation(10, 10), 0);

        portList.add(port);
        portList.add(port2);
        portList.add(port3);
        portList.add(port4);
        //Arrange
        tdt.insert(portList.toArray(new Port[0]));
        //Act
        String expected = " --a1-- \n" +
                " --a4--  --a2-- \n" +
                " --a3--  --null--  --null--  --null-- \n" +
                " --null--  --null-- \n";
        //Assert
        assertEquals(expected, tdt.toString());
    }
}

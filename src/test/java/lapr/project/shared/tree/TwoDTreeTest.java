package lapr.project.shared.tree;

import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
import lapr.project.model.Position;
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

    TwoDTree tdt = new TwoDTree();

    @Test
    void insert() {
        List<Port> portList = new ArrayList<>();
        Port port = new Port("11111", "a1", "a1", "a1", new FacilityLocation(10, 13));
        Port port2 = new Port("22222", "a2", "a2", "a2", new FacilityLocation(11, 12));
        Port port3 = new Port("33333", "a3", "a3", "a3", new FacilityLocation(3, 20));
        Port port4 = new Port("33333", "a4", "a4", "a4", new FacilityLocation(2, 22));
        Port port5 = new Port("33333", "a5", "a5", "a5", new FacilityLocation(20, 20));

        portList.add(port);
        portList.add(port2);
        portList.add(port3);
        portList.add(port4);
        portList.add(port5);

        tdt.insert(portList.toArray(new Port[0]));


        //Assert
        assertEquals(tdt.nearestNeighborPort(posgeral), port2);


    }


    @Test
    void nearestNeighbor() {
        //Arrange
        List<Port> portList = new ArrayList<>();
        Port port = new Port("11111", "a1", "a1", "a1", new FacilityLocation(10, 13));
        Port port2 = new Port("22222", "a2", "a2", "a2", new FacilityLocation(11, 12));
        Port port3 = new Port("33333", "a3", "a3", "a3", new FacilityLocation(3, 20));
        Port port4 = new Port("33333", "a4", "a4", "a4", new FacilityLocation(2, 22));
        Port port5 = new Port("33333", "a5", "a5", "a5", new FacilityLocation(20, 20));


        Position posgeral = new Position(19, 19, 0, 1, 1, date);
        portList.add(port3);
        portList.add(port2);
        portList.add(port);
        portList.add(port5);

        tdt.insert(portList.toArray(new Port[0]));

        System.out.println(tdt.toString());

        //Act
        Port actual = tdt.nearestNeighborPort(posgeral);

        System.out.println(actual.toString());
        //Assert
        assertEquals(port2, actual);
    }
}

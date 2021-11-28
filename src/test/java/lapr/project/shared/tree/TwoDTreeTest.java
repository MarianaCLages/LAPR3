package lapr.project.shared.tree;

import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoDTreeTest {

    Port port = new Port("11111", "a1", "a1", "a1", new FacilityLocation(10, 13));
    Port port2 = new Port("22222", "a2", "a2", "a2", new FacilityLocation(11, 12));
    Port port3 = new Port("33333", "a3", "a3", "a3", new FacilityLocation(3, 20));
    Port port4 = new Port("33333", "a4", "a4", "a4", new FacilityLocation(2, 22));
    Port port5 = new Port("33333", "a5", "a5", "a5", new FacilityLocation(10, 10));

    TwoDTree tdt = new TwoDTree();

    @Test
    void insert() {

        //Arrange
        String expected = " --a1-- \n" +
                " --a3--  --a2-- \n" +
                " --null--  --a4--  --null--  --null-- \n" +
                " --null--  --null-- \n";
        //Act
        tdt.insert(port);
        tdt.insert(port2);
        tdt.insert(port3);
        tdt.insert(port4);


        //Assert
        assertEquals(expected, tdt.toString());


    }


    @Test
    void closest() {

        //Arrange
        tdt.insert(port);
        tdt.insert(port2);
        Port expected = port2;
        //Act
        TwoDTree.Node node = tdt.closest(tdt.root, tdt.root.getRight(), port5);
        //Assert
        assertEquals(expected, node.getElement());
    }

   /* @Test
    void nearestNeighbord() {

        //Arrange
        tdt.insert(port);
        tdt.insert(port2);
        Port expected = port;

        //Act
        TwoDTree.Node actual = tdt.nearesNeighbor(port5);
        //Assert
        assertEquals(expected, actual.getElement());
    } */

    @Test
    void closestTest() {

        //Arrange
        tdt.insert(port);
        tdt.insert(port2);
        tdt.insert(port3);
        tdt.insert(port4);

        Port expected = port2;
        //Act
        TwoDTree.Node actual = tdt.closest(tdt.root.getLeft(), tdt.root.getRight(), port5);

        //Arrange
        assertEquals(expected, actual.getElement());
    }

    @Test
    void smallestElementTest() {

        //Arrange
        tdt.insert(port);
        tdt.insert(port2);
        tdt.insert(port3);
        tdt.insert(port4);
        Port expected = port3;
        //Act
        Port actual = tdt.smallestElement();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void toStringTest() {

        //Arrange
        tdt.insert(port);
        tdt.insert(port2);
        tdt.insert(port3);
        tdt.insert(port4);
        System.out.println(tdt.toString());

        //Act
        String expected = " --a1-- \n" +
                " --a3--  --a2-- \n" +
                " --null--  --a4--  --null--  --null-- \n" +
                " --null--  --null-- \n";
        //Assert
        assertEquals(expected, tdt.toString());
    }

}

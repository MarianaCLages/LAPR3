package lapr.project.shared.tree;

import lapr.project.model.FacilityLocation;
import lapr.project.model.Port;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwoDTreeTest {

    Port port = new Port("11111","a","a","a",new FacilityLocation(10,13));
    Port port2 = new Port("22222","a","a","a",new FacilityLocation(11,12));
    Port port3 = new Port("33333","a","a","a",new FacilityLocation(3,20));
    Port port4 = new Port("33333","a","a","a",new FacilityLocation(2,22));
    Port port5 = new Port("33333","a","a","a",new FacilityLocation(10,10));

    TwoDTree tdt = new TwoDTree();

    @Test
    void insert(){

        //Arrange
        String expected = "(10.0, 13.0)\n" +
                "(3.0, 20.0)(11.0, 12.0)\n" +
                "null, (2.0, 22.0)null, null, \n" +
                "null, null, \n";
        //Act
        tdt.insert(port);
        tdt.insert(port2);
        tdt.insert(port3);
        tdt.insert(port4);


        //Assert
        assertEquals(expected,tdt.toString());


    }


    @Test
    void closest(){

        //Arrange
        tdt.insert(port);
        tdt.insert(port2);
        Port expected = port2;
        //Act
        TwoDTree.Node  node = tdt.closest(tdt.root,tdt.root.getRight(),port5 );
        //Assert
        assertEquals(expected,node.getElement());
    }

    @Test
    void nearestNeighbord(){

        //Arrange
        tdt.insert(port);
        tdt.insert(port2);
        Port expected = port;

        //Act
        TwoDTree.Node actual = tdt.nearesNeighbor(port5);
        //Assert
        assertEquals(expected,actual.getElement());
    }

    @Test
    void closestTest(){

        //Arrange
        tdt.insert(port);
        tdt.insert(port2);
        tdt.insert(port3);
        tdt.insert(port4);

        Port expected = port2;
        //Act
        TwoDTree.Node actual = tdt.closest(tdt.root.getLeft(),tdt.root.getRight(),port5);

        //Arrange
        assertEquals(expected,actual.getElement());
    }

    @Test
    void smallestElementTest(){

        //Arrange
        tdt.insert(port);
        tdt.insert(port2);
        tdt.insert(port3);
        tdt.insert(port4);
        Port expected = port3;
        //Act
        Port actual = tdt.smallestElement();
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void toStringTest(){

        //Arrange
        tdt.insert(port);
        tdt.insert(port2);
        tdt.insert(port3);
        tdt.insert(port4);

        //Act
        String expected = "(10.0, 13.0)\n" +
                "(3.0, 20.0)(11.0, 12.0)\n" +
                "null, (2.0, 22.0)null, null, \n" +
                "null, null, \n";
        //Assert
        assertEquals(expected,tdt.toString());
    }

}

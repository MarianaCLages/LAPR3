package lapr.project.shared.tree;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class KDNodeTest {

    KDTree.KDNode kd1 = new KDTree.KDNode(Arrays.asList(0,1));


    @Test
    void addTest(){

        //Arrange
        KDTree.KDNode expected1 = new KDTree.KDNode(Arrays.asList(2,2));
        KDTree.KDNode expected2 = new KDTree.KDNode(Arrays.asList(0,5));
        //Act
        kd1.add(expected1);
        kd1.add(expected2);
        //Assert
        assertEquals(expected1,kd1.right);
        assertEquals(expected2,kd1.right.right);
    }

    @Test
    void toStringTest(){

        //Arrange + Act
        //Assert
        assertEquals("(point: [0, 1])",kd1.toString());
    }

}

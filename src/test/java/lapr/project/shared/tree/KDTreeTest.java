package lapr.project.shared.tree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;


public class KDTreeTest {

    KDTree tree = new KDTree(2);

    int[][] ar = new int[][] {
            {50, 50, 1},
            {80, 40, 2},
            {10, 60, 3},
            {51, 38, 4},
            {48, 38, 5}

    };





    @Test
    void toStringTest(){


        //Arrange
        for (int[] coord : ar) {
            KDTree.KDNode n = new KDTree.KDNode(Arrays.asList(coord[0], coord[1]));
            tree.add(n);
        }


        //Act + Asserts
        assertEquals("[50, 50], \n" +
                "[10, 60], [80, 40], \n" +
                "[48, 38], null, [51, 38], null, \n" +
                "null, null, null, null, \n",tree.toString());
    }

    @Test
    void distSquaredTest(){

        //Arrange
        KDTree.KDPoint kdp1 = new KDTree.KDPoint(Arrays.asList(11,61));
        KDTree.KDPoint kdp2 = new KDTree.KDPoint(Arrays.asList(50,51));
        long expected = 40;
        //Act
        long dis = KDTree.dist(kdp1,kdp2);

        //Assert
        assertEquals(expected,dis);

    }

    @Test
    void closest(){

        //Arrange
        KDTree.KDPoint kdp1 = new KDTree.KDPoint(Arrays.asList(11,61));
        KDTree.KDNode kd1 = new KDTree.KDNode(Arrays.asList(0,1));
        KDTree.KDNode kd2 = new KDTree.KDNode(Arrays.asList(2,2));
        KDTree.KDNode kd3 = null;
        KDTree.KDNode kd4 = null;
        KDTree.KDNode expected1 = new KDTree.KDNode(Arrays.asList(2,2));
        //Act + Assert
        assertEquals(kd1,tree.closest(kd1,kd3,kdp1));
        assertEquals(null,tree.closest(kd4,kd3,kdp1));
        assertEquals(expected1.toString(),tree.closest(kd1,kd2,kdp1).toString());

    }


    @Test
    void nearestNeighborTest(){

        //Arrange
        for (int[] coord : ar) {
            KDTree.KDNode n = new KDTree.KDNode(Arrays.asList(coord[0], coord[1]));
            tree.add(n);
        }

        KDTree kdt2 = new KDTree(2);
        KDTree.KDPoint kdp1 = new KDTree.KDPoint(Arrays.asList(11,61));
        KDTree.KDNode expected = new KDTree.KDNode(Arrays.asList(10,60));

        //Act
        KDTree.KDNode actual = tree.nearestNeighbor(kdp1);

        //Assert
        assertEquals(null,kdt2.nearestNeighbor(kdp1));
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void addTest(){

        //Arrange
        for (int[] coord : ar) {
            KDTree.KDNode n = new KDTree.KDNode(Arrays.asList(coord[0], coord[1]));
            tree.add(n);
        }

        KDTree.KDNode kd1 = new KDTree.KDNode(Arrays.asList(0,1));
        KDTree.KDNode kd2 = new KDTree.KDNode(Arrays.asList(2,2));
        //Act
        tree.add(kd1);
        tree.add(kd2);
        //Assert
        assertEquals("[50, 50], \n" +
                "[10, 60], [80, 40], \n" +
                "[48, 38], null, [51, 38], null, \n" +
                "[0, 1], null, null, null, \n" +
                "null, [2, 2], \n" +
                "null, null, \n",tree.toString());
    }

}

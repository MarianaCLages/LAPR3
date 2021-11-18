package lapr.project.shared.tree;

import lapr.project.shared.tree.KDPoint;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class KDPointTest {

    KDPoint  kdp1 = new KDPoint(Arrays.asList(11,61));
    KDPoint kdp2 = new KDPoint(Arrays.asList(50,51));

    @Test
    void getTest(){

        //Arrange
        int expected = 11;
        //Act
        int actual = kdp1.get(2);

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void sizeTest(){
        //Arrange + Act
        int expected = 2;
        // Assert
        assertEquals(expected,kdp1.size());
    }

    @Test
    void toStringTest(){

        //Arrange + Act
        //Assert
        assertEquals("[11, 61]",kdp1.toString());
    }
}

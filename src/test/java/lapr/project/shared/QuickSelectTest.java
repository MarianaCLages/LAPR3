package lapr.project.shared;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

class QuickSelectTest {
    Integer[] array = {68,
            97,
            87,
            60,
            58,
            55,
            82,
            33,
            99,
            3,
            6,
            8,
            21,
            50,
            89,
            25,
            74,
            8,
            40,
            3};

    @Test
    void getMedianTest20Elements() {
        Comparator comparator = Comparator.naturalOrder();
        Assertions.assertEquals(55, QuickSelect.select(array, array.length / 2, comparator));
    }
    @Test
    void getMedianTest1Element() {
        Integer array2[] = {5};
        Comparator comparator = Comparator.naturalOrder();
        Assertions.assertEquals(5, QuickSelect.select(array2, array.length / 2, comparator));
    }


}
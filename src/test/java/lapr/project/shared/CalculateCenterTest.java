package lapr.project.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateCenterTest {

    @Test
    void calculateCenter() {

        CalculateCenter calculateCenter = new CalculateCenter();

        double actualX71 = (calculateCenter.calculateCenter(71).get(0));
        double actualY71 = (calculateCenter.calculateCenter(71).get(1));
        double actualZ71 = (calculateCenter.calculateCenter(71).get(2));

        if(actualX71 == 0) fail();
        else if(actualY71 == 0) fail();
        else if(actualZ71 == 0) fail();

        double expectedX71 = 40.27777777777778;
        double expectedY71 = 16.944444444444443;
        double expectedZ71 = 17.77777777777778;

        assertEquals(expectedX71, actualX71);
        assertEquals(expectedY71, actualY71);
        assertEquals(expectedZ71, actualZ71);

        if (actualX71 != 40.27777777777778) fail();
        else if (actualY71 != 16.944444444444443) fail();
        else if (actualZ71 != 17.77777777777778) fail();

        double actualX72 = (calculateCenter.calculateCenter(72).get(0));
        double actualY72 = (calculateCenter.calculateCenter(72).get(1));
        double actualZ72 = (calculateCenter.calculateCenter(72).get(2));

        if(actualX72 == 0) fail();
        else if(actualY72 == 0) fail();
        else if(actualZ72 == 0) fail();

        double expectedX72 = 90.9090909090909;
        double expectedY72 = 36.36363636363637;
        double expectedZ72 = 27.272727272727273;

        assertEquals(expectedX72, actualX72);
        assertEquals(expectedY72, actualY72);
        assertEquals(expectedZ72, actualZ72);

        if (actualX72 != 90.9090909090909) fail();
        else if (actualY72 != 36.36363636363637) fail();
        else if (actualZ72 != 27.272727272727273) fail();

        double actualX74 = (calculateCenter.calculateCenter(74).get(0));
        double actualY74 = (calculateCenter.calculateCenter(74).get(1));
        double actualZ74 = (calculateCenter.calculateCenter(74).get(2));

        if(actualX74 == 0) fail();
        else if(actualY74 == 0) fail();
        else if(actualZ74 == 0) fail();

        double expectedX74 = 146.2962962962963;
        double expectedY74 = 64.81481481481481;
        double expectedZ74 = 101.85185185185185;

        assertEquals(expectedX74, actualX74);
        assertEquals(expectedY74, actualY74);
        assertEquals(expectedZ74, actualZ74);

        if (actualX74 != 146.2962962962963) fail();
        else if (actualY74 != 64.81481481481481) fail();
        else if (actualZ74 != 101.85185185185185) fail();

    }
}
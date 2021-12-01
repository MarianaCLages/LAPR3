package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FacilityLocationTest {

    @Test
    void createValidLocation() {
        double latitude = 50;
        double longitude = 150;
        try {
            FacilityLocation location = new FacilityLocation(longitude, latitude);
        } catch (Exception e) {
            Assertions.fail();
        }

    }

    @Test
    void createValidLocation2() {
        double latitude = 90;
        double longitude = 180;
        try {
            FacilityLocation location = new FacilityLocation(longitude, latitude);
        } catch (Exception e) {
            Assertions.fail();
        }

    }

    @Test
    void createValidLocation3() {
        double latitude = -90;
        double longitude = -180;
        try {
            FacilityLocation location = new FacilityLocation(longitude, latitude);
        } catch (Exception e) {
            Assertions.fail();
        }

    }

    @Test
    void createLocationTestInvalidLatitude() {
        double latitude = 194;
        double longitude = 30.5;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FacilityLocation location = new FacilityLocation(longitude, latitude);
        });

    }

    @Test
    void createLocationTestInvalidLongitude() {
        double latitude = 0;
        double longitude = -190;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FacilityLocation location = new FacilityLocation(longitude, latitude);
        });

    }

    @Test
    void getLatitude() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location = new FacilityLocation(longitude, latitude);
        Assertions.assertEquals(latitude, location.getLatitude());
    }


    @Test
    void getLongitude() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location = new FacilityLocation(longitude, latitude);
        Assertions.assertEquals(longitude, location.getLongitude());
    }

    @Test
    void setLatitude() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location = new FacilityLocation(longitude, latitude);
        try {
            location.setLatitude(68.56456456);
        } catch (Exception e) {
            Assertions.fail();
        }

    }

    @Test
    void setLongitude() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location = new FacilityLocation(longitude, latitude);
        try {
            location.setLongitude(128.56456456);
        } catch (Exception e) {
            Assertions.fail();
        }

    }

    @Test
    void equalsTest() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location1 = new FacilityLocation(longitude, latitude);

        double latitude2 = 50;
        double longitude2 = 150;
        FacilityLocation location2 = new FacilityLocation(longitude2, latitude2);

        Assertions.assertEquals(location1, location2);
    }

    @Test
    void equalsTestSameObject() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location1 = new FacilityLocation(longitude, latitude);

        Assertions.assertEquals(location1, location1);
    }

    @Test
    void equalsTestOtherClass() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location1 = new FacilityLocation(longitude, latitude);

        Integer i = Integer.valueOf(5);

        Assertions.assertNotEquals(location1, i);
    }

    @Test
    void equalsTestNotEquals1() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location1 = new FacilityLocation(longitude, latitude);

        double latitude2 = 55;
        double longitude2 = 150;
        FacilityLocation location2 = new FacilityLocation(longitude2, latitude2);

        Assertions.assertNotEquals(location1, location2);
    }

    @Test
    void equalsTestNotEquals2() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location1 = new FacilityLocation(longitude, latitude);

        double latitude2 = 50;
        double longitude2 = 151;
        FacilityLocation location2 = new FacilityLocation(longitude2, latitude2);

        Assertions.assertNotEquals(location1, location2);
    }

    @Test
    void hashCodeTest() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location1 = new FacilityLocation(longitude, latitude);

        double latitude2 = 50;
        double longitude2 = 150;
        FacilityLocation location2 = new FacilityLocation(longitude2, latitude2);

        Assertions.assertEquals(location1.hashCode(), location2.hashCode());
    }

    @Test
    void hashCodeTestGetNumber() {
        double latitude = 50;
        double longitude = 150;
        FacilityLocation location1 = new FacilityLocation(longitude, latitude);


        Assertions.assertEquals(154779648, location1.hashCode());
    }

    @Test
    void hashCodeTestNotEqual() {
        double latitude = 0;
        double longitude = 150;
        FacilityLocation location1 = new FacilityLocation(longitude, latitude);

        double latitude2 = 50;
        double longitude2 = 0;
        FacilityLocation location2 = new FacilityLocation(longitude2, latitude2);

        Assertions.assertNotEquals(location1.hashCode(), location2.hashCode());
    }

    @Test
    void compareToTest() {
        //Arrange
        double latitude = 0;
        double longitude = 50;
        FacilityLocation location1 = new FacilityLocation(longitude, latitude);

        double latitude2 = 0;
        double longitude2 = 50;
        FacilityLocation location2 = new FacilityLocation(longitude2, latitude2);

        //Assert
        Assertions.assertEquals(location1.compareTo(location2), location2.compareTo(location1));
    }
}
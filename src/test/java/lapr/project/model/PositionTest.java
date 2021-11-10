package lapr.project.model;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {


    String sdate = "31/11/2020 23:16";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));

    Position posgeral = new Position(1, 0, 0, 1, 1, date);


    @Test
    void getDateTest() {
        assertEquals("2020-11-30T23:16", date.toString());
    }

    @Test
    void getLatitudeTest() {
        //Arrange
        //Act
        //Assert
        assertEquals(1, posgeral.getLatitude());

    }

    @Test
    void setLatitudeTest() {

        //Arrange
        //Act
        posgeral.setLatitude(1);

        //Assert
        assertEquals(1, posgeral.getLatitude());

        posgeral.setLatitude(90);
        assertEquals(90, posgeral.getLatitude());

        posgeral.setLatitude(-90);
        assertEquals(-90, posgeral.getLatitude());
    }

    @Test
    void getLongitudeTest() {

        //Arrange
        //Act
        //Assert
        assertEquals(0, posgeral.getLongitude());


    }

    @Test
    void setLongitudeTest() {

        //Arrange
        //Act
        posgeral.setLongitude(1);

        //Assert
        assertEquals(1, posgeral.getLongitude());

        posgeral.setLongitude(180);
        assertEquals(180, posgeral.getLongitude());

        posgeral.setLongitude(-180);
        assertEquals(-180, posgeral.getLongitude());
    }

    @Test
    void getHeadingTest() {

        //Arrange
        //Act
        //Assert
        assertEquals(0, posgeral.getHeading());
    }

    @Test
    void setHeadingTest() {

        //Arrange
        //Act
        posgeral.setHeading(1);
        //Assert
        assertEquals(1, posgeral.getHeading());

        posgeral.setHeading(0);
        //Assert
        assertEquals(0, posgeral.getHeading());

        posgeral.setHeading(359);
        //Assert
        assertEquals(359, posgeral.getHeading());
    }

    @Test
    void getSogTest() {

        //Arrange
        //Act
        //Assert
        assertEquals(1, posgeral.getSog());

    }

    @Test
    void setSogTest() {
        //Arrange
        //Act
        posgeral.setSog(2);
        //Assert
        assertEquals(2, posgeral.getSog());
    }

    @Test
    void getCogTest() {
        //Arrange
        //Act
        //Assert
        assertEquals(1, posgeral.getCog());
    }

    @Test
    void setCogTest() {

        //Arrange
        //Act
        posgeral.setCog(1);
        //Assert
        assertEquals(1, posgeral.getCog());
    }

    @Test
    void invalidLatitude() {
        assertThrows(IllegalArgumentException.class, () -> {
            posgeral.setLatitude(-200);
        });

    }


    @Test
    void noInfoLatitude() {
        posgeral.setLatitude(91);
        assertNull(posgeral.getLatitude());
    }


    @Test
    void invalidLongitude() {
        assertThrows(IllegalArgumentException.class, () -> {
            posgeral.setLongitude(-200);
        });
        try {
            posgeral.setLongitude(180);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void noInfoLongitude() {
        posgeral.setLongitude(181);
        assertNull(posgeral.getLongitude());
    }

    @Test
    void invalidHeading() {
        assertThrows(IllegalArgumentException.class, () -> {
            posgeral.setHeading(500);
        });
        try {
            posgeral.setHeading(91);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void noInfoHeading() {
        posgeral.setHeading(511);
        assertNull(posgeral.getHeading());
    }

    @Test
    void toStringTest() {

        //Arrange
        String expected = "Position{latitude=1.0, longitude=0.0, heading=0.0, sog=1.0, cog=1.0}";

        //Act
        //Assert
        assertEquals(expected, posgeral.toString());
    }

    @Test
    void setDate() {
        //Arrange
        LocalDateTime expected = LocalDateTime.now();
        //Act
        posgeral.setDate(expected);
        LocalDateTime actual = posgeral.getDate();
        //Assert
        assertEquals(expected, actual);
    }

}

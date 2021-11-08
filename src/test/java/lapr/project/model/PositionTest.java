package lapr.project.model;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {


    String sdate = "31/11/2020 23:16";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));

    Position posgeral = new Position(date, 0, 0, 0, 1, 0);


    @Test
    void getDateTest() {
        assertEquals("2020-11-30T23:16", date.toString());
    }

    @Test
    void getLatitudeTest() {
        //Arrange
        //Act
        //Assert
        assertEquals(0, posgeral.getLatitude());

    }

    @Test
    void setLatitudeTest() {

        //Arrange
        //Act
        posgeral.setLatitude(1);

        //Assert
        assertEquals(1, posgeral.getLatitude());

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
        assertEquals(0, posgeral.getCog());
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
    void toStringTest() {

        //Arrange
        String expected = "Position{latitude=0, longitude=0, heading=0, sog=1, cog=0}";

        //Act
        //Assert
        assertEquals(expected, posgeral.toString());
    }
}

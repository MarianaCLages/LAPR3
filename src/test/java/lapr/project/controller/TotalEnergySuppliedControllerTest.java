package lapr.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalEnergySuppliedControllerTest {

    TotalEnergySuppliedController controller = new TotalEnergySuppliedController();
    int numberOfContainers = 10;
    double temperature = 20;
    int time = 9000;

    @Test
    void calculationToMinus5() {
        //Arrange
        String expected = "Journey time: 9000s\n" +
                "Temperature: 20.0ºC\n" +
                "Total energy to be supplied: 134338.27 J";
        //Act
        String actual = String.valueOf(controller.calculationToMinus5(numberOfContainers, temperature, time));
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void calculationTo7() {
        //Arrange
        String expected = "Journey time: 9000s\n" +
                "Temperature: 20.0ºC\n" +
                "Total energy to be supplied: 851266.50 J";
        //Act
        String actual = String.valueOf(controller.calculationTo7(numberOfContainers, temperature, time));
        //Assert
        assertEquals(expected, actual);
    }
}
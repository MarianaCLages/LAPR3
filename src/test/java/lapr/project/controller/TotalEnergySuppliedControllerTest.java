package lapr.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalEnergySuppliedControllerTest {

    TotalEnergySuppliedController controller = new TotalEnergySuppliedController();
    int numberOfContainers = 10;
    double temperature = 20;
    int time = 9000;

    @Test
    void calculationToMinus5() {
        //Arrange
        String expected = "134338.27436138067";
        //Act
        String actual = String.valueOf(controller.calculationToMinus5(numberOfContainers, temperature, time));
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void calculationTo7() {
        //Arrange
        String expected = "851266.5028210469";
        //Act
        String actual = String.valueOf(controller.calculationTo7(numberOfContainers, temperature, time));
        //Assert
        assertEquals(expected, actual);
    }
}
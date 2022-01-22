package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhysicsCalculationTest {

    int numberOfContainers = 10;
    double temperature = 20;
    int time = 9000;

    @Test
    void calculateTotalEnergySuppliedMinus5() {
        //Arrange
        double expected = 134338.27436138067;
        //Act
        double actual = PhysicsCalculation.calculateTotalEnergySuppliedMinus5(numberOfContainers, temperature, time);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void calculateTotalEnergySupplied7() {
        //Arrange
        double expected = 851266.5028210469;
        //Act
        double actual = PhysicsCalculation.calculateTotalEnergySupplied7(numberOfContainers, temperature, time);
        //Assert
        assertEquals(expected, actual);
    }
}
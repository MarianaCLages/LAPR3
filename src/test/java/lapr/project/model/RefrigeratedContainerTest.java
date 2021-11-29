package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefrigeratedContainerTest {

    RefrigeratedContainer refrigeratedContainer = new RefrigeratedContainer("111111111", 1, 1, 1, "11", 1, 1);

    @Test
    void getEnergyConsume() {
        //Arrange
        double expected = 1;
        //Act
        double actual = refrigeratedContainer.getEnergyConsume();
        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void getTemperature() {
        //Arrange
        double expected = 1;
        //Act
        double actual = refrigeratedContainer.getTemperature();
        //Assert
        assertEquals(actual, expected);
    }
}
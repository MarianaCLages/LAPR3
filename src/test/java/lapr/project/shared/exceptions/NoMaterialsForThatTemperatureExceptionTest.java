package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoMaterialsForThatTemperatureExceptionTest {

    @Test
    void getException() {

        try {
            throw new NoMaterialsForThatTemperatureException();
        } catch (NoMaterialsForThatTemperatureException ex1) {

        }

    }

}
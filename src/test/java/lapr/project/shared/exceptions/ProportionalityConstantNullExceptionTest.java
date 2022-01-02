package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProportionalityConstantNullExceptionTest {

    @Test
    void getException() {

        try {
            throw new ProportionalityConstantNullException();
        } catch (ProportionalityConstantNullException ex1) {

        }

    }

}
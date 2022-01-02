package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoMaterialsFoundExceptionTest {

    @Test
    void getException() {

        try {
            throw new NoMaterialsFoundException();
        } catch (NoMaterialsFoundException ex1) {

        }

    }

}
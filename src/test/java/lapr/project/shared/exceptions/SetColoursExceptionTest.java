package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetColoursExceptionTest {

    @Test
    void getException() {

        try {
            throw new SetColoursException();
        } catch (SetColoursException ex1) {

        }

    }

}
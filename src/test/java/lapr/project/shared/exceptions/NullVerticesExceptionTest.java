package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NullVerticesExceptionTest {

    @Test
    public void createException() {


        try {
            throw new NullVerticesException();
        } catch (NullVerticesException ex1) {

        }

    }

}
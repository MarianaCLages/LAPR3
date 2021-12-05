package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacilityNotFoundExceptionTest {

    @Test
    void getException() {

        try {
            throw new FacilityNotFoundException();
        } catch (FacilityNotFoundException ex1) {

        }

    }

}
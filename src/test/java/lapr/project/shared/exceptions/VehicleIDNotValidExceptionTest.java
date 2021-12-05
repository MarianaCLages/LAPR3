package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleIDNotValidExceptionTest {

    @Test
    void getException() {

        try {
            throw new VehicleIDNotValidException();
        } catch (VehicleIDNotValidException ex1) {

        }

    }

}
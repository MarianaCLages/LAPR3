package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTypeNullExceptionTest {

    @Test
    void getException() {

        try {
            throw new MaterialTypeNullException();
        } catch (MaterialTypeNullException ex1) {

        }

    }

}
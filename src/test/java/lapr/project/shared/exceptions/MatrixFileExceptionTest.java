package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixFileExceptionTest {

    @Test
    public void createException() {


        try {
            throw new MatrixFileException();
        } catch (MatrixFileException ex1) {

        }

    }

}
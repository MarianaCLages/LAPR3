package lapr.project.controller;

import lapr.project.shared.exceptions.MatrixFileException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class Create3DMatrixControllerTest {

    @Test
    void createMatrix() {

        Create3DMatrixController create3DMatrixController = new Create3DMatrixController();

        try {
            boolean actual = create3DMatrixController.createMatrix("103");

            if (!actual) fail();

            assertTrue(actual);

            boolean actual2 = create3DMatrixController.createMatrix("-1");

            if (actual2) fail();

            assertFalse(actual2);

        } catch (MatrixFileException | IOException | SQLException e) {
            System.out.println("y");
        }

    }
}
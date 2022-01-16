package lapr.project.controller;

import lapr.project.shared.exceptions.InvalidCargoManifestException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ExportInformationControllerTest {
    @Test
    void idCorrect() {
        ExportInformationController controller = new ExportInformationController();

        try {

            assertTrue(controller.export("1"));
        } catch (SQLException | InvalidCargoManifestException e) {
            fail();
        }
    }

    @Test
    void idIncorrect() {
        ExportInformationController controller = new ExportInformationController();

        try {

            assertFalse(controller.export("a"));
        } catch (SQLException e) {
            fail();
        } catch (InvalidCargoManifestException e) {
            // pass test
        }
    }

}
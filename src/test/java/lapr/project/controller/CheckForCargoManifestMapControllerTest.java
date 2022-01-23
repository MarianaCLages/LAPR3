package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CheckForCargoManifestMapControllerTest {

    @Test
    void callFunction() {

        CheckForCargoManifestMapController controller = new CheckForCargoManifestMapController();

        java.sql.Date rDate = Date.valueOf(LocalDate.now());

        try {
            String value = controller.callFunction("8", rDate);
            if (value.isEmpty()) fail();
        } catch (SQLException exception) {

        }

    }
}
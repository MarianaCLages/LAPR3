package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class GetAuditTrailControllerTest {

    @Test
    void getAuditTrail() {

        GetAuditTrailController getAuditTrailController = new GetAuditTrailController();

        try {
            String actual = getAuditTrailController.getAuditTrail();

            if (actual == null || actual.equals("")) fail();

        } catch (SQLException e) {
            System.out.println("NANI");
        }


    }
}
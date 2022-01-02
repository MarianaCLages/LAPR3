package lapr.project.controller;

import lapr.project.shared.exceptions.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AverageCargoByYearControllerTest {

    @Test
    void averageCargoByYearController() {
        //Arrange
        AverageCargoByYearController ctrl = new AverageCargoByYearController();

        String expected = "\n" +
                "Number of cargo manifests transported in the given year: 1\n" +
                "Average number of containers per manifest: 1.0";

        //Act
        String actual = null;
        try {
            actual = ctrl.averageCargoByYear(303296000, 2021);
        } catch (ShipCargoCapacityException | NoContainersInsideThatTripException | NoCargoManifestsWereFoundInThatTripException | NoCargoManifestInThatDateException | VehicleIDNotValidException | SQLException | CargoManifestDoesntBelongToThatShipException | CargoManifestIDException | ContainersInsideCargoManifestListSizeException | ContainerGrossException e) {
            System.out.println(e.getMessage());
        }

        if (actual == null || actual.equals("")) fail();

        //Assert
        assertEquals(expected, actual);
    }
}

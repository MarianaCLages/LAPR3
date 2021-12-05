package lapr.project.controller;

import lapr.project.data.DataBaseScripts.AverageCargoByYearScript;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.*;

import java.sql.SQLException;

public class AverageCargoByYearController {

    private final AverageCargoByYearScript averageCargoByYear;
    private final DatabaseConnection databaseConnection;

    /**
     * Constructor.
     */
    public AverageCargoByYearController() {
        this.averageCargoByYear = new AverageCargoByYearScript();
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the total number of cargo manifests and the average number of containers
     *
     * @param mmsi the ship's mmsi
     * @param year the year
     * @return the total number of cargo manifests and the average number of containers
     */
    public String averageCargoByYear(int mmsi, int year) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestIDException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException, SQLException, NoCargoManifestInThatDateException, NoCargoManifestsWereFoundInThatTrip, NoContainersInsideThatTripException {
        return averageCargoByYear.numberOfContainers(databaseConnection, mmsi, year);
    }
}

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
     * Gets the total number of cargo manifests in a given year and the average number of containers per manifest.
     *
     * @param mmsi the ship's MMSI
     * @param year the year
     * @return the total number of cargo manifests in a given year and the average number of containers per manifest
     */
    public String averageCargoByYear(int mmsi, int year) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestIDException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException, SQLException, NoCargoManifestInThatDateException, NoCargoManifestsWereFoundInThatTripException, NoContainersInsideThatTripException {
        return averageCargoByYear.numberOfContainers(databaseConnection, mmsi, year);
    }
}

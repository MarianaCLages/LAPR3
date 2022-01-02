package lapr.project.controller;

import lapr.project.data.DataBaseScripts.OccupancyRateOfAGivenShip;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.*;

public class OccupancyRateGivenCargoManifestDateController {

    private final OccupancyRateOfAGivenShip occupancyRateOfAGivenShip;
    private final DatabaseConnection databaseConnection;

    /**
     * Constructor.
     */
    public OccupancyRateGivenCargoManifestDateController() {
        this.occupancyRateOfAGivenShip = new OccupancyRateOfAGivenShip();
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the occupancy rate of a given ship at a given moment.
     *
     * @param mmsi the ship's MMSI
     * @param date the date
     * @return
     * @throws ShipCargoCapacityException in case a ship cargo capacity is invalid
     * @throws ContainerGrossException if the container gross is null or is invalid
     * @throws ContainersInsideCargoManifestListSizeException if there is no containers inside a cargo manifest
     * @throws CargoManifestIDException if the ID introduced doesn't exist
     * @throws CargoManifestDoesntBelongToThatShipException if a cargoManifest doesn't belong to a ship
     * @throws VehicleIDNotValidException if that VehicleID doesn't exist
     * @throws IllegalArgumentException in case the input of the MMSI is not valid
     */
    public double getOccupancyRate(int mmsi, String date) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestIDException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException {
        return occupancyRateOfAGivenShip.occupancyRateInAShipGivenACargoManifestDate(databaseConnection, mmsi, date);
    }
}

package lapr.project.controller;

import lapr.project.data.DataBaseScripts.OccupancyRateOfAGivenShip;
import lapr.project.data.DatabaseConnection;
import lapr.project.shared.exceptions.*;

public class OccupancyRateGivenCargoManifestIDController {

    private final OccupancyRateOfAGivenShip occupancyRateOfAGivenShip;
    private final DatabaseConnection databaseConnection;

    /**
     * Constructor.
     */
    public OccupancyRateGivenCargoManifestIDController() {
        this.occupancyRateOfAGivenShip = new OccupancyRateOfAGivenShip();
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    /**
     * Gets the occupancy rate of a given ship for a given cargo manifest.
     *
     * @param mmsi            the ship's MMSI
     * @param cargoManifestID the cargo manifest ID
     * @return return the occupancy rate for that specific Ship
     * @throws ShipCargoCapacityException in case the ship cargo is not available
     * @throws ContainerGrossException in case the container cross is null/not available
     * @throws ContainersInsideCargoManifestListSizeException in case there is no containers inside that cargo manifest
     * @throws CargoManifestDoesntBelongToThatShipException in case the cargo manifest doesn't belong to the ship
     * @throws VehicleIDNotValidException in case the vehicle ID doesn't exist
     * @throws IllegalArgumentException in case the introduced MMSI is not valid
     */
    public double getOccupancyRate(int mmsi, String cargoManifestID) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException {
        return occupancyRateOfAGivenShip.occupancyRateInAShipGivenACargoManifestID(databaseConnection, mmsi, cargoManifestID);
    }
}

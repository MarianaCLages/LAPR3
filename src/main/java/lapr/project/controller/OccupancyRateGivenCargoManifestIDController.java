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
     * @return
     * @throws ShipCargoCapacityException
     * @throws ContainerGrossException
     * @throws ContainersInsideCargoManifestListSizeException
     * @throws CargoManifestDoesntBelongToThatShipException
     * @throws VehicleIDNotValidException
     * @throws IllegalArgumentException
     */
    public double getOccupancyRate(int mmsi, String cargoManifestID) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException {
        return occupancyRateOfAGivenShip.occupancyRateInAShipGivenACargoManifestID(databaseConnection, mmsi, cargoManifestID);
    }
}

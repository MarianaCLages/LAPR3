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
     * @throws ShipCargoCapacityException
     * @throws ContainerGrossException
     * @throws ContainersInsideCargoManifestListSizeException
     * @throws CargoManifestIDException
     * @throws CargoManifestDoesntBelongToThatShipException
     * @throws VehicleIDNotValidException
     * @throws IllegalArgumentException
     */
    public double getOccupancyRate(int mmsi, String date) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestIDException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException {
        return occupancyRateOfAGivenShip.occupancyRateInAShipGivenACargoManifestDate(databaseConnection, mmsi, date);
    }
}

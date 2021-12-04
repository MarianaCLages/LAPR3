package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

public class OccupancyRateController {

    private final ShipStore shipStore;

    /**
     * Constructor.
     */
    public OccupancyRateController() {
        Company company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    /**
     * Gets the occupancy rate of a given ship for a given cargo manifest.
     *
     * @param mmsi the ship's MMSI
     * @return the occupancy rate of a given ship for a given cargo manifest
     */
    public String occupancyRate(int mmsi) {
        Ship ship = shipStore.getShipByMmsi(mmsi);

        return ship.getOccupancyRate();
    }
}
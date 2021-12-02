package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

public class AverageCargoByYearController {

    private final ShipStore shipStore;

    /**
     * Constructor.
     */
    public AverageCargoByYearController() {
        Company company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
    }

    /**
     * Gets the ship store.
     *
     * @return the ship store
     */
    public ShipStore getShipStore() {
        return shipStore;
    }

    /**
     * Gets the total number of cargo manifests and the average number of containers
     *
     * @param mmsi the ship's mmsi
     * @param year the year
     * @return the total number of cargo manifests and the average number of containers
     */
    public String averageCargoByYear(int mmsi, int year) {
        Ship ship = shipStore.getShipByMmsi(mmsi);

        return ship.writeCargoByYear(year);
    }
}

package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

public class SearchShipController {

    private final Company company;
    private final ShipStore shipStore;

    /**
     * Constructor.
     */
    public SearchShipController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
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
     * Gets the ship by MMSI.
     *
     * @param mmsi the ship's MMSI
     * @return the ship found
     */
    public Ship searchShipByMMSI(int mmsi) {
        return shipStore.getShipByMmsi(mmsi);
    }

    /**
     * Gets the ship by IMO.
     *
     * @param imo the ship's IMO
     * @return the ship found
     */
    public Ship searchShipByIMO(String imo) {
        return shipStore.getShipByIMO(imo);
    }

    /**
     * Gets the ship by Call Sign.
     *
     * @param callSign the ship's Call Sign
     * @return the ship found
     */
    public Ship searchShipByCallSign(String callSign) {
        return shipStore.getShipByCallSign(callSign);
    }
}

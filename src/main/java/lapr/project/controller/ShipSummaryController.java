package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.stores.ShipStore;

public class ShipSummaryController {

    private final Company company;
    private final ShipStore shipStore;

    /**
     * Constructor.
     */
    public ShipSummaryController() {
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
     * Gets the ship summary by its MMSI.
     *
     * @param mmsi the ship's MMSI
     * @return the ship summary of the found ship
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public String getShipSummaryByMMSI(int mmsi) throws IllegalArgumentException, NullPointerException {
        return shipStore.getShipSummaryByMMSI(mmsi);
    }

    /**
     * Gets the ship summary by its IMO.
     *
     * @param imo the ship's IMO
     * @return the ship summary of the found ship
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public String getShipSummaryByIMO(String imo) throws IllegalArgumentException, NullPointerException {
        return shipStore.getShipSummaryByIMO(imo);
    }

    /**
     * Gets the ship summary by its Call Sign.
     *
     * @param callSign the ship's Call Sign
     * @return the ship summary of the found ship
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public String getShipSummaryByCallSign(String callSign) throws IllegalArgumentException, NullPointerException {
        return shipStore.getShipSummaryByCallSign(callSign);
    }
}

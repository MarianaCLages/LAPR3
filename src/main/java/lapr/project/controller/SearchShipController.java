package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

public class SearchShipController {

    private final Company cmpn;
    private final ShipStore ss;

    /**
     * Constructor.
     */
    public SearchShipController() {
        this.cmpn = App.getInstance().getCompany();
        this.ss = cmpn.getShipStore();
    }

    /**
     * Gets the ship by MMSI.
     *
     * @param p the MMSI
     * @return the ship found
     */
    public Ship searchShipByMMSI(int p) {
        return ss.getShipByMmsi(p);
    }

    /**
     * Gets the ship by IMO.
     *
     * @param p the IMO
     * @return the ship found
     */
    public Ship searchShipByIMO(String p) {
        return ss.getShipByIMO(p);
    }

    /**
     * Gets the ship by Call Sign.
     *
     * @param p the Call Sign
     * @return the ship found
     */
    public Ship searchShipByCallSign(String p) {
        return ss.getShipByCallSign(p);
    }

    /**
     * Gets the ship store.
     *
     * @return the ship store
     */
    public ShipStore getShipStore() {
        return ss;
    }
}

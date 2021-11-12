package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

import java.time.LocalDateTime;

public class PositionalMessageController {
    private final Company company;
    private final ShipStore shipStore;

    /**
     * Constructor.
     */
    public PositionalMessageController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }

    /**
     * Gets the positional messages of a ship by its MMSI on a certain period of time.
     */
    public String getPositionalMessages(int mmsi, LocalDateTime di, LocalDateTime df) {
        try {
            Ship ship = shipStore.getShipByMMSI(mmsi);

            if (ship == null) throw new NullPointerException("Ship doesn't exist!");
            String posMessage = ship.writeAllPos(di, df);

            if (!posMessage.equals("Positional Message:")) {
                return posMessage;
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    /**
     * Gets the Ship Store.
     *
     * @return the Ship Store
     */
    public ShipStore getShipStore() {
        return shipStore;
    }
}

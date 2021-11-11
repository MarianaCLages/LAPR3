package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;

import java.time.LocalDateTime;

public class PositionalMessageController {


    private Company company;
    private ShipStore shipStore;

    /**
     * Constructor.
     */

    public PositionalMessageController() {
        this.company = App.getInstance().getCompany();
        this.shipStore = company.getShipStore();
    }


    /**
     * Gets the positional messages of a ship by its mmsi on a certain period of time.
     * */
    public boolean getPositionalMessages(int mmsi, LocalDateTime di , LocalDateTime df) {

        try {
            Ship ship = shipStore.getShipByMMSI(mmsi);


            if (ship == null) return false;

            String posMessage = ship.writeAllPos(di, df);

            if (!posMessage.equals("Positional Message:")) {

                System.out.println(posMessage);
                System.out.println("\n\nPositional messages sent successfully|");

                return true;
            }

        }catch (NullPointerException e){
            return false;
        }
        return false;
    }

    /**
     * Gets the Ship Store.
     *
     * @return the Ship Store
     */
    public ShipStore getShipStore(){
        return shipStore;
    }
}

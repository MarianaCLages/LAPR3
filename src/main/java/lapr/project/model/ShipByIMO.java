package lapr.project.model;

public class ShipByIMO extends Ship implements Comparable<ShipByIMO> {

    /**
     * Constructor.
     *
     * @param mmsi             the ship's MMSI
     * @param name             the ship's name
     * @param imo              the ship's IMO code
     * @param callSign         the ship's call sign
     * @param vesselType       the ship's vessel type
     * @param length           the ship's length
     * @param width            the ship's width
     * @param draft            the ship's draft
     * @param cargo            the ship's cargo
     * @param transceiverClass the ship's transceiver class
     */
    public ShipByIMO(int mmsi, String name, String imo, String callSign, String vesselType, double length, double width, double draft, String cargo, char transceiverClass) {
        super(mmsi, name, imo, callSign, vesselType, length, width, draft, cargo, transceiverClass);
    }

    /**
     * Constructor to find the ship in the AVL tree by its IMO code.
     *
     * @param imo the ship's IMO code
     */
    public ShipByIMO(String imo) {
        setImo(imo);
    }

    /**
     * Compares the position date of two different objects (ShipByIMO).
     *
     * @param o the object to compare (ShipByIMO)
     * @return 0 if they're equal, -1 or 1 if they're different
     */
    @Override
    public int compareTo(ShipByIMO o) {
        return (getImo().compareTo(o.getImo()));
    }
}

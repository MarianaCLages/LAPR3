package lapr.project.model;

public class ShipByCallSign extends Ship implements Comparable<ShipByCallSign> {

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
    public ShipByCallSign(int mmsi, String name, String imo, String callSign, String vesselType, double length, double width, double draft, String cargo, char transceiverClass) {
        super(mmsi, name, imo, callSign, vesselType, length, width, draft, cargo, transceiverClass);
    }

    /**
     * Constructor to find the ship in the AVL tree by its call sign.
     *
     * @param callSign the ship's call sign
     */
    public ShipByCallSign(String callSign) {
        setCallSign(callSign);
    }

    /**
     * Compares the position date of two different objects (ShipByCallSign).
     *
     * @param o the object to compare (ShipByCallSign)
     * @return 0 if they're equal, -1 or 1 if they're different
     */
    @Override
    public int compareTo(ShipByCallSign o) {
        return (getCallSign().compareTo(o.getCallSign()));
    }
}

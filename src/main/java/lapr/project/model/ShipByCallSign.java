package lapr.project.model;

public class ShipByCallSign extends Ship implements Comparable<ShipByCallSign> {
    public ShipByCallSign(int mmsi, String name, String imo, String callSign, String vesselType, double length, double width, double draft, String cargo, char transceiverClass) {
        super(mmsi, name, imo, callSign, vesselType, length, width, draft, cargo, transceiverClass);
    }

    public ShipByCallSign(String callSign) {
        setCallSign(callSign);
    }


    @Override
    public int compareTo(ShipByCallSign o) {
        return (getCallSign().compareTo(o.getCallSign()));
    }
}

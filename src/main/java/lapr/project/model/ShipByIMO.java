package lapr.project.model;

public class ShipByIMO extends Ship implements Comparable<ShipByIMO> {
    public ShipByIMO(int mmsi, String name, String imo, String callSign, String vesselType, double length, double width, double draft, String cargo, char transceiverClass) {
        super(mmsi, name, imo, callSign, vesselType, length, width, draft, cargo, transceiverClass);
    }

    public ShipByIMO(String imo) {
        setImo(imo);
    }


    @Override
    public int compareTo(ShipByIMO o) {
        return (getImo().compareTo(o.getImo()));
    }
}

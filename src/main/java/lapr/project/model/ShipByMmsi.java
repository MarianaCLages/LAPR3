package lapr.project.model;

public class ShipByMmsi extends Ship implements Comparable<ShipByMmsi> {
    public ShipByMmsi(int mmsi, String name, String imo, String callSign, String vesselType, double length, double width, double draft, String cargo, char transceiverClass) {
        super(mmsi, name, imo, callSign, vesselType, length, width, draft, cargo, transceiverClass);
    }

    public ShipByMmsi(int mmsi) {
        setMmsi(mmsi);
    }


    @Override
    public int compareTo(ShipByMmsi o) {
        return Integer.compare(getMmsi(), o.getMmsi());
    }
}

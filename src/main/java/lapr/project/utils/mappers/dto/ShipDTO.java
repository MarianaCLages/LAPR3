package lapr.project.utils.mappers.dto;

public class ShipDTO {

    private int mmsi;
    private int totalNumberOfMovements;
    private double travelledDistance;
    private double deltaDistance;

    public ShipDTO(int mmsi, int totalNumberOfMovements, double travelledDistance, double deltaDistance) {

        this.mmsi = mmsi;
        this.totalNumberOfMovements = totalNumberOfMovements;
        this.travelledDistance = travelledDistance;
        this.deltaDistance = deltaDistance;

    }

    @Override
    public String toString() {
        return "MMSI: " + this.mmsi + "; Total number of movements: " + this.totalNumberOfMovements + "; Travelled Distance: " + this.travelledDistance + "; Delta Distance: " + this.deltaDistance + "\n";
    }
}

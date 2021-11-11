package lapr.project.utils.mappers.dto;

public class ShipDTO {

    private final int mmsi;
    private final int totalNumberOfMovements;
    private final double travelledDistance;
    private final double deltaDistance;

    /**
     * Constructor.
     *
     * @param mmsi                   the ship's MMSI
     * @param totalNumberOfMovements the ship's total number of movements
     * @param travelledDistance      the ship's travelled distance
     * @param deltaDistance          the ship's delta distance
     */
    public ShipDTO(int mmsi, int totalNumberOfMovements, double travelledDistance, double deltaDistance) {
        this.mmsi = mmsi;
        this.totalNumberOfMovements = totalNumberOfMovements;
        this.travelledDistance = travelledDistance;
        this.deltaDistance = deltaDistance;
    }

    /**
     * Returns the textual description of the ship info in the format : MMSI, total number of movements, travelled distance, delta distance.
     *
     * @return the Ship's characteristics
     */
    @Override
    public String toString() {
        return "MMSI: " + this.mmsi + "; Total number of movements: " + this.totalNumberOfMovements + "; Travelled Distance: " + this.travelledDistance + "; Delta Distance: " + this.deltaDistance + "\n";
    }
}

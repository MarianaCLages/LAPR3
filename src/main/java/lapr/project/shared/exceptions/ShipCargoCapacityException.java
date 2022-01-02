package lapr.project.shared.exceptions;

public class ShipCargoCapacityException extends Exception {

    /**
     * In case the ship's cargo capacity is invalid, it prints the message "Cargo capacity invalid! Please verify the integrity of the ship that you enter.".
     */
    public ShipCargoCapacityException() {
        super("Cargo capacity invalid! Please verify the integrity of the ship that you enter.");
    }
}

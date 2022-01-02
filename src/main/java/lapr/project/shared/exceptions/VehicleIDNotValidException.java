package lapr.project.shared.exceptions;

public class VehicleIDNotValidException extends Exception {

    /**
     * In case the vehicle ID introduced by the user doesn't exist, it prints the message "Vehicle ID doesn't exist for that ship/cargo manifest! Please update the ship/cargo manifest information.".
     */
    public VehicleIDNotValidException() {
        super("Vehicle ID doesn't exist for that ship/cargo manifest! Please update the ship/cargo manifest information.");
    }
}

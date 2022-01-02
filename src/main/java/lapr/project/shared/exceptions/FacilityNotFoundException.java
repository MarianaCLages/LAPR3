package lapr.project.shared.exceptions;

public class FacilityNotFoundException extends Exception {

    /**
     * In case the specified facility doesn't exist, it prints the message "Facility not found! Please verify the input data.".
     */
    public FacilityNotFoundException() {
        super("Facility not found! Please verify the input data.");
    }
}

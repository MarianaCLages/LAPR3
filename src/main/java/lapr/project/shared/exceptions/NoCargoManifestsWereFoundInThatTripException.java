package lapr.project.shared.exceptions;

public class NoCargoManifestsWereFoundInThatTripException extends Exception {

    /**
     * In case there are no cargo manifests in the specified trip, it prints the message "There is no cargo manifests inside that trip! Please verify the data.".
     */
    public NoCargoManifestsWereFoundInThatTripException() {
        super("There is no cargo manifests inside that trip! Please verify the data.");
    }
}

package lapr.project.shared.exceptions;

public class NoCargoManifestInThatDateException extends Exception {

    /**
     * In case there are no cargo manifests in the specified date, it prints the message "No cargo manifests were found in that date! Please verify the data.".
     */
    public NoCargoManifestInThatDateException() {
        super("No cargo manifests were found in that date! Please verify the data.");
    }
}

package lapr.project.shared.exceptions;

public class InvalidCargoManifestException extends Exception {

    /**
     * In case there are no cargo manifests with the inserted ID, it prints the message "There are no cargo manifest with the inserted ID!".
     */
    public InvalidCargoManifestException() {
        super("There are no cargo manifest with the inserted ID!");
    }
}

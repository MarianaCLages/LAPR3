package lapr.project.shared.exceptions;

public class CargoManifestIDException extends Exception {

    /**
     * In case the cargo manifest ID introduced by the user doesn't exist, it prints the message "There is no cargoManifest with the specific ID that you enter! Please enter another one!".
     */
    public CargoManifestIDException() {
        super("There is no cargoManifest with the specific ID that you enter! Please enter another one!");
    }
}

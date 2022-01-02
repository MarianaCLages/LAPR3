package lapr.project.shared.exceptions;

public class CargoManifestDoesntBelongToThatShipException extends Exception {

    /**
     * In case a cargo manifest doesn't belong to the ship introduced, it prints the message "The selected cargo manifest doesn't belong to the selected ship! Please enter a valid cargo manifest for the typed ship.".
     */
    public CargoManifestDoesntBelongToThatShipException() {
        super("The selected cargo manifest doesn't belong to the selected ship! Please enter a valid cargo manifest for the typed ship.");
    }
}

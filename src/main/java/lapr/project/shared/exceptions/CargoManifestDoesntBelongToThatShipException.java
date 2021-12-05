package lapr.project.shared.exceptions;

public class CargoManifestDoesntBelongToThatShipException extends Exception {

    public CargoManifestDoesntBelongToThatShipException(){
        super("The selected cargo manifest doesn't belong to the selected ship! Please enter a valid cargo manifest for the typed ship");
    }

}

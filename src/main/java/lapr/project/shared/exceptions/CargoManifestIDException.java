package lapr.project.shared.exceptions;

public class CargoManifestIDException extends Exception{

    public CargoManifestIDException(){

        super("There is no cargoManifest with the specific ID that you enter! Please enter another one!");

    }

}

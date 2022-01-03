package lapr.project.shared.exceptions;

public class InvalidCargoManifestException extends Exception{

    public InvalidCargoManifestException(){
        super("There is no cargo manifest with the entered ID!");
    }

}

package lapr.project.shared.exceptions;

public class NoCargoManifestInThatDateException extends Exception{

    public NoCargoManifestInThatDateException(){

        super("No cargo manifests were found in that date! Please verify the data");

    }

}

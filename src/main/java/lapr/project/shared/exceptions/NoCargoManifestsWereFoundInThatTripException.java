package lapr.project.shared.exceptions;

public class NoCargoManifestsWereFoundInThatTripException extends Exception{

    public NoCargoManifestsWereFoundInThatTripException(){

        super("There is no cargo manifests inside that trip! Please verify the data.");

    }

}

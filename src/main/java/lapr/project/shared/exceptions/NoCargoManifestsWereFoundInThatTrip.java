package lapr.project.shared.exceptions;

public class NoCargoManifestsWereFoundInThatTrip extends Exception{

    public NoCargoManifestsWereFoundInThatTrip(){

        super("There is no cargo manifests inside that trip! Please verify the data.");

    }

}

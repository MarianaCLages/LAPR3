package lapr.project.shared.exceptions;

public class FacilityNotFoundException extends Exception{

    public FacilityNotFoundException(){

        super("Facility not found! Please verify the input data.");

    }

}

package lapr.project.shared.exceptions;

public class VehicleIDNotValidException extends Exception{

    public VehicleIDNotValidException(){
        super("Vehicle ID doesn't exist for that ship/cargo manifest! Please update the ship/cargo manifest information!");
    }

}

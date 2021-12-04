package lapr.project.shared.exceptions;

public class ShipCargoCapacityException extends Exception{

    public ShipCargoCapacityException(){
        super("Cargo capacity invalid! Please verify the ship that you enter!");
    }

}

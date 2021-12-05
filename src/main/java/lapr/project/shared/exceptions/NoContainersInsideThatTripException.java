package lapr.project.shared.exceptions;

public class NoContainersInsideThatTripException extends Exception{

    public NoContainersInsideThatTripException(){

        super("No containers were found in that specific trip! Please verify the data!");

    }

}

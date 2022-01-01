package lapr.project.shared.exceptions;

public class ProportionalityConstantNullException extends Exception {

    public ProportionalityConstantNullException(){
        super("The proportionality constant is null! Please verify the integrity of the data in the data base!");
    }

}

package lapr.project.shared.exceptions;

public class NoMaterialsFoundException extends Exception{
    public NoMaterialsFoundException(){
        super("No materials were found for that specific temperature! Please verify the data base!");
    }
}

package lapr.project.shared.exceptions;

public class NullVerticesException extends Exception{

    public NullVerticesException(){
        super("Vertices information cannot be null!");
    }

}

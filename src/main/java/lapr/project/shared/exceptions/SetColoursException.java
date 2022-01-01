package lapr.project.shared.exceptions;

public class SetColoursException extends Exception {

    public SetColoursException(){
        super("There was an error while colouring the Graph! Please verify if the cities are well defined!");
    }

}

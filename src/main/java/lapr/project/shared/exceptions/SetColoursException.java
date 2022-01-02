package lapr.project.shared.exceptions;

public class SetColoursException extends Exception {

    /**
     * In case there is an error colouring the graph, it prints the message "There was an error while colouring the graph! Please verify if the cities are well defined.".
     */
    public SetColoursException(){
        super("There was an error while colouring the graph! Please verify if the cities are well defined.");
    }
}

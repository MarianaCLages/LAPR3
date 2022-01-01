package lapr.project.shared.exceptions;

public class NoPathFoundForSpecificVertexException extends Exception {

    public NoPathFoundForSpecificVertexException(){
        super("Tried to access a path that is null! Please verify the all the vertexes of the graph!");
    }

}

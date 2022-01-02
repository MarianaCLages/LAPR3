package lapr.project.shared.exceptions;

public class NoPathFoundForSpecificVertexException extends Exception {

    /**
     * In case there is no path for a specified vertex of the graph, it prints the message "Tried to access a path that is null! Please verify the all the vertexes of the graph.".
     */
    public NoPathFoundForSpecificVertexException() {
        super("Tried to access a path that is null! Please verify the all the vertexes of the graph.");
    }
}

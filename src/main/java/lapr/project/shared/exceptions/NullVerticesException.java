package lapr.project.shared.exceptions;

public class NullVerticesException extends Exception {

    /**
     * In case the vertex information is null, it prints the message "Vertices information cannot be null!".
     */
    public NullVerticesException() {
        super("Vertices information cannot be null!");
    }
}

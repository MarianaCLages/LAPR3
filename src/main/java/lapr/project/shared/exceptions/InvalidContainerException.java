package lapr.project.shared.exceptions;

public class InvalidContainerException extends Exception {

    /**
     * In case there are no containers with the inserted ID, it prints the message "No container was found with that ID!".
     */
    public InvalidContainerException() {
        super("No container was found with that ID!");
    }
}

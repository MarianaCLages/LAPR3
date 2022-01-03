package lapr.project.shared.exceptions;

public class InvalidContainerException extends Exception {

    public InvalidContainerException() {
        super("No container was found with that ID!");
    }

}

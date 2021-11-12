package lapr.project.shared.exceptions;

public class InvalidLineException extends Exception {
    /**
     * In case a line from a file is invalid, it prints the message "Invalid line!"
     */
    public InvalidLineException() {
        super("Invalid line!");
    }
}

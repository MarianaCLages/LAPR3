package lapr.project.shared.exceptions;

public class NoMaterialsFoundException extends Exception {

    /**
     * In case there were no materials found, it prints the message "No materials were found! Please verify the data base.".
     */
    public NoMaterialsFoundException() {
        super("No materials were found! Please verify the data base.");
    }
}

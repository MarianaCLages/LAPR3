package lapr.project.shared.exceptions;

public class ProportionalityConstantNullException extends Exception {

    /**
     * In case the proportionality constant is null, it prints the message "The proportionality constant is null! Please verify the integrity of the data in the database.".
     */
    public ProportionalityConstantNullException() {
        super("The proportionality constant is null! Please verify the integrity of the data in the database.");
    }
}

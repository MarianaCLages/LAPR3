package lapr.project.shared.exceptions;

public class NoContainersInsideThatTripException extends Exception {

    /**
     * In case there are no containers in the specified trip, it prints the message "No containers were found in that specific trip! Please verify the data.".
     */
    public NoContainersInsideThatTripException() {
        super("No containers were found in that specific trip! Please verify the data.");
    }
}

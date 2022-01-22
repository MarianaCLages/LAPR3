package lapr.project.shared.exceptions;

public class InvalidShipException extends Exception {

    /**
     * In case the user inserts an invalid ship, it prints the message "Please enter other ship!! The entered ship does not have valid data! That means there is no cargo manifest (inside that period) that satisfies the desired goal! Please try again!".
     */
    public InvalidShipException() {
        super("\nPlease enter other ship!! The entered ship does not have valid data! That means there is no cargo manifest (inside that period) that satisfies the desired goal! Please try again!");
    }
}

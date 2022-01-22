package lapr.project.shared.exceptions;

public class NoValidInformationInsideThatTripException extends Exception {

    /**
     * In case the trip information inserted is invalid, it prints the message "There is no valid information inside that voyage! Please verify again the introduced data, or verify the trip itself!".
     */
    public NoValidInformationInsideThatTripException(){
        super("There is no valid information inside that voyage! Please verify again the introduced data, or verify the trip itself!");
    }
}

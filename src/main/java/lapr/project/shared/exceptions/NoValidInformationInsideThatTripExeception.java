package lapr.project.shared.exceptions;

public class NoValidInformationInsideThatTripExeception extends Exception {

    public NoValidInformationInsideThatTripExeception(){
        super("There is no valid information inside that voyage! Please verify again the introduced data, or verify the trip itself!");
    }


}

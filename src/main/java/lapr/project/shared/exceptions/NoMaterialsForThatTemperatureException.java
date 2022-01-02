package lapr.project.shared.exceptions;

public class NoMaterialsForThatTemperatureException extends Exception {

    /**
     * In case there are no materials for the specified temperature, it prints the message "There are no materials for that specific temperature! Please verify the number that you introduced! (Note: Enter a temperature of 7º or -5º)".
     */
    public NoMaterialsForThatTemperatureException() {
        super("There are no materials for that specific temperature! Please verify the number that you introduced! (Note: Enter a temperature of 7º or -5º)");
    }
}

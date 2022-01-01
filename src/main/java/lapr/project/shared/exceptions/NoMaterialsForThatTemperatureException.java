package lapr.project.shared.exceptions;

public class NoMaterialsForThatTemperatureException extends Exception {
    public NoMaterialsForThatTemperatureException() {
        super("There are no materials for that specific temperature! Please verify the number that you introduced! (Note: Enter a temperature of 7º or -5º)");
    }
}

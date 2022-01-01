package lapr.project.shared.exceptions;

public class MaterialTypeNullException extends Exception {
    public MaterialTypeNullException() {
        super("The type of the material is null! Please verify the data base!");
    }
}

package lapr.project.shared.exceptions;

public class MaterialTypeNullException extends Exception {

    /**
     * In case the material type doesn't exist, it prints the message "The type of the material is null! Please verify the database!".
     */
    public MaterialTypeNullException() {
        super("The type of the material is null! Please verify the database!");
    }
}

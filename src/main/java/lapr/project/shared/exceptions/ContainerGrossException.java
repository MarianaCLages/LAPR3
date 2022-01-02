package lapr.project.shared.exceptions;

public class ContainerGrossException extends Exception {

    /**
     * In case there's no gross for the specified container, it prints the message "There is no gross for this specific container! Please verify if this container is valid!".
     */
    public ContainerGrossException() {
        super("There is no gross for this specific container! Please verify if this container is valid!");
    }
}

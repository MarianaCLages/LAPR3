package lapr.project.shared.exceptions;

public class ContainerGrossException extends Exception{

    public ContainerGrossException(){
        super("There is no Gross for this specific container! Please verify if this container is valid!");
    }

}

package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerGrossExceptionTest {

    @Test
    void getException(){

        try{
            throw new ContainerGrossException();
        }catch (ContainerGrossException ex1){

        }

    }

}
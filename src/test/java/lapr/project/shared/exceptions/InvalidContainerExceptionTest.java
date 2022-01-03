package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidContainerExceptionTest {


    @Test
    void getException(){

        try{
            throw new InvalidContainerException();
        }catch (InvalidContainerException ex1){

        }

    }

}
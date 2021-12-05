package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidLineExceptionTest {

    @Test
    void getException(){

        try{
            throw new InvalidLineException();
        }catch (InvalidLineException ex1){

        }

    }

}
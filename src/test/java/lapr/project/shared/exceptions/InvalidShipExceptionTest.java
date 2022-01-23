package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidShipExceptionTest {


    @Test
    void getException(){

        try{
            throw new InvalidShipException();
        }catch (InvalidShipException ex1){

        }

    }

}
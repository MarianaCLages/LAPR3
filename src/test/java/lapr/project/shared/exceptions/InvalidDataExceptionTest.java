package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidDataExceptionTest {

    @Test
    void getException(){

        try{
            throw new InvalidDataException();
        }catch (InvalidDataException ex1){

        }

    }

    @Test
    void getException2(){

        try{
            throw new InvalidDataException("Yah");
        }catch (InvalidDataException ex1){

        }

    }

}
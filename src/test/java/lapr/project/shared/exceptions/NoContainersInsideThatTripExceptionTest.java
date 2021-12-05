package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoContainersInsideThatTripExceptionTest {

    @Test
    void getException(){

        try{
            throw new NoContainersInsideThatTripException();
        }catch (NoContainersInsideThatTripException ex1){

        }

    }

}
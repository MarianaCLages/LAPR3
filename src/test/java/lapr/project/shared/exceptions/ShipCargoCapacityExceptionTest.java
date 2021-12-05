package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipCargoCapacityExceptionTest {

    @Test
    void getException(){

        try{
            throw new ShipCargoCapacityException();
        }catch (ShipCargoCapacityException ex1){

        }

    }

}
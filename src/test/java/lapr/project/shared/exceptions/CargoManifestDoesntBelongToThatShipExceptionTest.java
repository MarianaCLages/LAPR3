package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CargoManifestDoesntBelongToThatShipExceptionTest {

    @Test
    void getException(){

        try{
            throw new CargoManifestDoesntBelongToThatShipException();
        }catch (CargoManifestDoesntBelongToThatShipException ex1){

        }

    }

}
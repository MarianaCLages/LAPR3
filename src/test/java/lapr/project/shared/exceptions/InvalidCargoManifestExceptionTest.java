package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCargoManifestExceptionTest {

    @Test
    void getException(){

        try{
            throw new InvalidCargoManifestException();
        }catch (InvalidCargoManifestException ex1){

        }

    }

}
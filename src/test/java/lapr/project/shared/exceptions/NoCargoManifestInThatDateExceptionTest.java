package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoCargoManifestInThatDateExceptionTest {

    @Test
    void getException(){

        try{
            throw new NoCargoManifestInThatDateException();
        }catch (NoCargoManifestInThatDateException ex1){

        }

    }

}
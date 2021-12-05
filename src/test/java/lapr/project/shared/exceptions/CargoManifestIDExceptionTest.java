package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CargoManifestIDExceptionTest {

    @Test
    void getException(){

        try{
            throw new CargoManifestIDException();
        }catch (CargoManifestIDException ex1){

        }

    }


}
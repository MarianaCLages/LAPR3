package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainersInsideCargoManifestListSizeExceptionTest {

    @Test
    void getException(){

        try{
            throw new ContainersInsideCargoManifestListSizeException();
        }catch (ContainersInsideCargoManifestListSizeException ex1){

        }

    }

}
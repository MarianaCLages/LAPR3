package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

class NoCargoManifestsWereFoundInThatTripExceptionTest {

    @Test
    void getException(){

        try{
            throw new NoCargoManifestsWereFoundInThatTripException();
        }catch (NoCargoManifestsWereFoundInThatTripException ex1){

        }

    }

}
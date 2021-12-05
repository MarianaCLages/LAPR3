package lapr.project.shared.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoCargoManifestsWereFoundInThatTripTest {

    @Test
    void getException(){

        try{
            throw new NoCargoManifestsWereFoundInThatTrip();
        }catch (NoCargoManifestsWereFoundInThatTrip ex1){

        }

    }

}
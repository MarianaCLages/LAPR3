package lapr.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void createCompanyInvalid() {
        //Arrange + Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Company company = new Company("");
        });
    }

    @Test
    void createCompanyNull() {
        //Arrange + Act + Assert
        try {
            Company company = new Company(null);
            assertEquals(company, null);
        } catch (IllegalArgumentException e) {
            //Mutant killer
        }
    }
}
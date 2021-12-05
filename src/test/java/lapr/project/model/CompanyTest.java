package lapr.project.model;


import lapr.project.data.CargoManifestStoreData;
import lapr.project.data.ContainerStoreData;
import lapr.project.data.PortStoreData;
import lapr.project.data.ShipStoreData;
import lapr.project.utils.auth.AuthFacade;
import lapr.project.utils.auth.domain.store.OrgRoleStore;
import lapr.project.utils.auth.domain.store.UserStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    Company company = new Company("Cargo");

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

    @Test
    void getAuthFacade() {

        AuthFacade authFacade = company.getAuthFacade();

        if(authFacade==null) fail();

    }

    @Test
    void getOrgRoleStore() {

        OrgRoleStore orgRoleStore = company.getOrgRoleStore();

        if(orgRoleStore==null) fail();

    }

    @Test
    void getShipStoreData() {

        ShipStoreData shipStoreData = company.getShipStoreData();

        if(shipStoreData==null) fail();

    }

    @Test
    void getPortStoreData() {

        PortStoreData portStoreData = company.getPortStoreData();

        if(portStoreData ==null) fail();

    }

    @Test
    void getCargoManifestPortStore() {

        CargoManifestStoreData cargoManifestStoreData = company.getCargoManifestStoreData();

        if(cargoManifestStoreData==null) fail();

    }

    @Test
    void getContainerStoreData() {

        ContainerStoreData containerStoreData = company.getContainerStoreData();

        if(containerStoreData==null) fail();

    }

    @Test
    void getUserStoreData() {

        UserStore userStore = company.getUserStore();

        if(userStore ==null) fail();

    }



}
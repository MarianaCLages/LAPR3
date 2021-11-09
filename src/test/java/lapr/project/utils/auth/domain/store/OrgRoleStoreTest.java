package lapr.project.utils.auth.domain.store;

import lapr.project.utils.auth.domain.OrgRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrgRoleStoreTest {

    static OrgRoleStore orgRoleStore = new OrgRoleStore();
    static OrgRole orgRole = new OrgRole("9FdC", "Design");

    OrgRoleStore orgRoleStoreEmpty = new OrgRoleStore();

    @BeforeAll
    public static void init() {
        orgRoleStore.addOrgRole(orgRole);
    }

    @Test
    void addOrgRole() {
        //Arrange
        OrgRole expected = new OrgRole("12dfk", "Analysis");
        orgRoleStore.addOrgRole(expected);
        //Act
        OrgRole actual = orgRoleStore.getRoleById("12dfk");
        //Assert
        assertNotNull(actual);
    }

    @Test
    void getOrgRolesNull() {

        //Arrange
        List<OrgRole> expected = null;
        //Act
        orgRoleStoreEmpty.setLor(null);
        List<OrgRole> actual = orgRoleStoreEmpty.getOrgRoles();
        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void getOrgRolesEmpty() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            List<OrgRole>  list_null = orgRoleStoreEmpty.getOrgRoles();
        });

    }

    @Test
    void getRoleByIdNoRoleInsideTheList() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            OrgRole orgRole_notValid = orgRoleStore.getRoleById("aBdEx");
        });


    }

    @Test
    void setLor() {
        //Arrange
        List<OrgRole> expected = new ArrayList<>();
        OrgRole role = new OrgRole("12dfk", "Analysis");
        expected.add(role);
        orgRoleStore.setLor(expected);
        //Act
        List<OrgRole> actual = orgRoleStore.getOrgRoles();
        //Assert
        assertEquals(expected, actual);
    }
}
package lapr.project.utils.auth.mappers.dto;

import lapr.project.utils.auth.domain.Email;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleDTOTest {

    UserRoleDTO userRoleDTO = new UserRoleDTO("Trolha", "trolhice");

    @Test
    void getId() {
        //Arrange
        String expected = "Trolha";
        //Act
        String actual = userRoleDTO.getId();
        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void getDescription() {
        //Arrange
        String expected = "trolhice";
        //Act
        String actual = userRoleDTO.getDescription();
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void toStringTest() {
        //Arrange
        String expected = "UserRoleDTO{id='Trolha', description='trolhice'}";
        //Act
        String actual = userRoleDTO.toString();
        //Assert
        if(StringUtils.isBlank(actual) || actual.equals("")) fail();
        assertEquals(expected, actual);
    }
}
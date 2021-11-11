package lapr.project.utils.auth.domain.store;

import lapr.project.utils.auth.domain.Email;
import lapr.project.utils.auth.domain.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserStoreTest {

    UserStore userStore = new UserStore();

    String name = "Miguel";
    String email = "1201487@isep.ipp.pt";
    String password = "abCdEl";


    @Test
    void create() {
        //Arrange + Act + Assert
        assertNotNull(userStore.create(name, email, password));
    }

    @Test
    void add() {
        //Arrange
        User user = userStore.create(name, email, password);
        boolean expected = true;
        //Act
        boolean actual = userStore.add(user);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void addMutant() {
        //Arrange
        User user = userStore.create(name, email, password);
        boolean expected = false;
        //Act
        userStore.add(user);
        boolean actual = userStore.add(user);
        if(actual) fail();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void remove() {
        //Arrange
        User user = userStore.create(name, email, password);
        userStore.add(user);
        boolean expected = true;
        //Act
        boolean actual = userStore.remove(user);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void removeMutant() {
        //Arrange
        User user = userStore.create(name, email, password);
        userStore.add(user);
        boolean expected = false;
        //Act
        userStore.remove(user);
        boolean actual = userStore.remove(user);
        if(actual) fail();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getByIdTrueEmail() {
        //Arrange
        User user = userStore.create(name, email, password);
        userStore.add(user);
        Optional<User> expected = Optional.ofNullable(user);
        //Act
        Optional<User> actual = userStore.getById(user.getId());
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getByIdFalseEmail() {
        User user = userStore.create(name, email, password);
        Optional<User> expected = Optional.empty();
        //Act
        Optional<User> actual = userStore.getById(user.getId());
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getByIdString() {
        //Arrange
        User user = userStore.create(name, email, password);
        userStore.add(user);
        Optional<User> expected = Optional.ofNullable(user);
        //Act
        Optional<User> actual = userStore.getById("1201487@isep.ipp.pt");
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void existsByEmail() {
        //Arrange
        User user = userStore.create(name, email, password);
        userStore.add(user);
        boolean expected = true;
        //Act
        boolean actual = userStore.exists(user.getId());
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void existsByEmailMutant() {
        //Arrange
        User user = userStore.create(name, email, password);
        userStore.add(user);
        boolean expected = false;
        //Act
        boolean actual = userStore.exists(new Email("aa@a.pt"));
        if(actual) fail();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void existsByString() {
        //Arrange
        User user = userStore.create(name, email, password);
        userStore.add(user);
        boolean expected = true;
        //Act
        boolean actual = userStore.exists("1201487@isep.ipp.pt");
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void existsByStringMutant() {
        //Arrange
        User user = userStore.create(name, email, password);
        userStore.add(user);
        boolean expected = false;
        //Act
        boolean actual = userStore.exists("aaa@a.pt");
        if(actual) fail();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void existsByUser() {
        //Arrange
        User user = userStore.create(name, email, password);
        userStore.add(user);
        boolean expected = true;
        //Act
        boolean actual = userStore.exists(user);
        //Assert
        assertEquals(expected, actual);
    }


}
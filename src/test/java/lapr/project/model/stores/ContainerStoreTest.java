package lapr.project.model.stores;
import lapr.project.model.Container;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ContainerStoreTest {

    Container c1 = new Container("20BD", 1000, 1000, 100, "20RF", false, false);

    ContainerStore containerStore = new ContainerStore();
    ContainerStore containerStore2 = new ContainerStore();


    @Test
    void addContainerTest(){

        //Arrange
        //Act
        containerStore.addContainer(c1);
        String expected = "Container{identification='20BD', payload=1000, tare=1000, gross=100, isoCode='20RF', position=ContainerPosition{xPos=0, yPos=0, zPos=0}}\n";

        assertEquals(expected,containerStore.toString());
    }


    @Test
    void writeAllContainersTest(){

        //Arrange
        containerStore2.addContainer(c1);
        //Act
        String expedted = "Container{identification='20BD', payload=1000, tare=1000, gross=100, isoCode='20RF', position=null}";
        //Assert
        assertEquals(false,containerStore.writeAllContainers());
        assertEquals(true,containerStore2.writeAllContainers());
    }

    @Test
    void getContainerAVL(){

        //Arrange
        containerStore2.addContainer(c1);
        //Act
         //Assert
        if(!containerStore.getContainerByAVL().isEmpty()) fail();
    }

}

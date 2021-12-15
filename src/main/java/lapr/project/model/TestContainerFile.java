package lapr.project.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestContainerFile {


    public static void main(String[] args) {

        FacilityLocation f1 = new FacilityLocation(11, 11);
        Port p1 = new Port("a", "a", "1", "a", f1,0);
        Ship ship3 = new Ship(256888000, "CMA CGM MELISANDE", "IMO9473028", 12, 12, "9HA2954", "70", 334, 42, 15, 20);
        CargoManifest cargoManifest = new CargoManifest("aaaaa", p1, ship3, true);
        Container containerPos = new Container("123", 1000, 1000, 100, "20RF", false, false);
        Container containerPos2 = new Container("456", 1000, 1000, 100, "20RF");
        Container containerPos3 = new Container("789", 1000, 1000, 100, "20RF");
        Container containerPos4 = new Container("222", 1000, 1000, 100, "20RF");
        Container containerPos5 = new Container("24444", 1000, 1000, 100, "20RF");
        Container containerPos6 = new Container("007", 1000, 1000, 100, "20RF");
        Container containerPos7 = new Container("707", 1000, 1000, 100, "20RF");
        Container containerPos8 = new Container("777", 1000, 1000, 100, "20RF");

        cargoManifest.addContainersOffLoaded(containerPos);
        cargoManifest.addContainersOffLoaded(containerPos2);
        cargoManifest.addContainersOffLoaded(containerPos3);
        cargoManifest.addContainersLoaded(containerPos4);
        cargoManifest.addContainersOffLoaded(containerPos5);
        cargoManifest.addContainersOffLoaded(containerPos6);
        cargoManifest.addContainersLoaded(containerPos7);
        cargoManifest.addContainersLoaded(containerPos8);


        try {

            File myObj = new File("container.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File updated!");
            }

            FileWriter myWriter = new FileWriter(myObj);

            for (Container cp : cargoManifest.getOffloaded().inOrder()) {

                myWriter.write(cp.getPosition().xPos + "," + cp.getPosition().yPos + "," + cp.getPosition().zPos + "," + cp.getIdentification() + "\n");

            }

            for (Container cp : cargoManifest.getLoaded().inOrder()) {

                myWriter.write(cp.getPosition().xPos + "," + cp.getPosition().yPos + "," + cp.getPosition().zPos + "," + cp.getIdentification() + "\n");
            }

            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error has occurred!");
            System.out.println(e.getMessage());
        }
    }
}

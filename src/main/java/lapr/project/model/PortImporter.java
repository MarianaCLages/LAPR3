package lapr.project.model;

import lapr.project.model.stores.PortStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PortImporter {
    private PortImporter() {
        //empty constructor
    }

    public static boolean importPorts(File path,PortStore portStore) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(String.valueOf(path)));
        sc.nextLine();
        do {
            String[] line = sc.nextLine().split(",");

            Port port = new Port(line[0], line[1], line[2], line[3], new FacilityLocation(Double.valueOf(line[5]), Double.valueOf(line[4])));
            portStore.add(port);

        } while (sc.hasNextLine());
        sc.close();
        portStore.fillTree();

        return true;
    }

}

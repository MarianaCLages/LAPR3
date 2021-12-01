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
            portStore.add(new Port(line[0], line[1], line[2], line[3], new FacilityLocation(Double.parseDouble(line[5]), Double.parseDouble(line[4]))));
        } while (sc.hasNextLine());
        sc.close();
        portStore.fillTree();

        return true;
    }

}

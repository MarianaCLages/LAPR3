package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.model.stores.PortStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PortImporter {

    public static boolean importPorts(File path) throws FileNotFoundException {

        PortStore portStore = App.getInstance().getCompany().getPortStore();
        Scanner sc = new Scanner(new File(String.valueOf(path)));
        sc.nextLine();
        do {
            String[] line = sc.nextLine().split(",");
            portStore.add(new Port(line[0], line[1], line[2], line[3], new FacilityLocation(Double.valueOf(line[5]), Double.valueOf(line[4]))));
        } while (sc.hasNextLine());
        sc.close();

        return true;

    }

}

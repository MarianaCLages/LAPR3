package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.PortStoreData;
import lapr.project.model.stores.PortStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PortImporter {
    private PortImporter() {
        //Empty constructor
    }

    /**
     * Imports the ports from a file.
     *
     * @param path      the path of the file
     * @param portStore the port store
     * @return true if it imports the file, false if it doesn't
     * @throws FileNotFoundException
     */
    public static boolean importPorts(File path, PortStore portStore, PortStoreData portStoreData, DatabaseConnection dbConnection) throws FileNotFoundException {
        boolean returnValue = false;
        Scanner sc = new Scanner(new File(String.valueOf(path)));
        sc.nextLine();
        do {
            String[] line = sc.nextLine().split(",");

            Port port = new Port(line[2], line[3], line[0], line[1], new FacilityLocation(Double.valueOf(line[5]), Double.valueOf(line[4])));
            portStore.add(port);

            //  portStoreData.save(dbConnection, port);

        } while (sc.hasNextLine());
        sc.close();
        returnValue = portStore.fillTree();

        return returnValue;
    }

}

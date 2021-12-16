package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.shared.exceptions.MatrixFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatrixFileGenerator {

    private final DatabaseConnection databaseConnection;
    private final List<CargoManifest> cargoManifestList;

    public MatrixFileGenerator(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        this.cargoManifestList = new ArrayList<>();
    }

    public boolean generateMatrixFile(int mmsi) throws SQLException, MatrixFileException, IOException {

        Ship ship = DataBaseUtils.getShipByMmsi(mmsi, databaseConnection);
        int count = DataBaseUtils.countCargoManifestByShip(mmsi, this.databaseConnection);
        int countContainers;

        if (count == 0) {
            return false;
        }

        while (count != 0) {
            cargoManifestList.add(DataBaseUtils.getCargoManifestByMmsi(mmsi, count, ship, this.databaseConnection));
            count--;
        }



        for (CargoManifest cm : cargoManifestList) {

            countContainers = DataBaseUtils.countContainerByCargoManifest(cm.getIdentification(), this.databaseConnection);

            while (countContainers != 0) {

                Container c = DataBaseUtils.getContainerByCargo(cm.getIdentification(), countContainers, this.databaseConnection);
                cm.addContainersLoaded(c);
                countContainers--;
            }

        }

       /* for (CargoManifest cm : cargoManifestList) {
            for (Container container : cm.getLoaded().inOrder()) {

                System.out.println(container.getPosition().xPos + "," + container.getPosition().yPos + "," + container.getPosition().zPos + "," + container.getIdentification() + "\n");

            }}*/


        File myObj = new File("container.txt");

        try (FileWriter myWriter = new FileWriter(myObj)) {



                for (CargoManifest cargoManifest : cargoManifestList) {
                    for (Container container : cargoManifest.getLoaded().inOrder()) {

                        myWriter.write(container.getPosition().xPos + "," + container.getPosition().yPos + "," + container.getPosition().zPos + "," + container.getIdentification() + "\n");

                    }
                }

            }

        return true;
        }





}

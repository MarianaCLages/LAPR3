package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class MatrixFileGenerator {

    private final DatabaseConnection databaseConnection;

    public MatrixFileGenerator(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public boolean generateMatrixFile(String id, int mmsi) throws SQLException, IOException {

        Ship ship = DataBaseUtils.getShipByMmsi(mmsi, databaseConnection);
        int countContainers;
        CargoManifest cargoManifest = DataBaseUtils.getCargoManifestByID(id, ship, databaseConnection);

        if (cargoManifest == null) {
            return false;
        }

        ship.getCargoManifestAVL().insert(cargoManifest);

        countContainers = DataBaseUtils.countContainerByCargoManifest(cargoManifest.getIdentification(), this.databaseConnection);

        while (countContainers != 0) {

            Container c = DataBaseUtils.getContainerByCargo(cargoManifest.getIdentification(), countContainers, this.databaseConnection);
            cargoManifest.addContainersLoaded(c);
            countContainers--;
        }


        File myObj = new File("container.txt");

        try (FileWriter myWriter = new FileWriter(myObj)) {
            for (Container container : cargoManifest.getLoaded().inOrder()) {
                myWriter.write(container.getPosition().xPos + "," + container.getPosition().yPos + "," + container.getPosition().zPos + "," + container.getIdentification() + "\n");
            }
        }
        return true;
    }
}

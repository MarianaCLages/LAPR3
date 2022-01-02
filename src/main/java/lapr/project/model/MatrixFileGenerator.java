package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class MatrixFileGenerator {

    private final DatabaseConnection databaseConnection;

    /**
     * Constructor.
     *
     * @param databaseConnection the database connection
     */
    public MatrixFileGenerator(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Generates the matrix file.
     *
     * @param cargoManifestId the cargo manifest ID
     * @return true if it succeeds, false if it doesn't
     * @throws SQLException
     * @throws IOException
     */
    public boolean generateMatrixFile(String cargoManifestId) throws SQLException, IOException {

        Ship ship = DataBaseUtils.getMmsiByCargoManifest(databaseConnection, cargoManifestId);

        if (ship == null) {
            return false;
        }

        int countContainers;
        CargoManifest cargoManifest = DataBaseUtils.getCargoManifestByID(cargoManifestId, ship, databaseConnection);

        if (cargoManifest == null) {
            return false;
        }

        ship.getCargoManifestAVL().insert(cargoManifest);

        countContainers = DataBaseUtils.countContainerByCargoManifest(cargoManifest.getIdentification(), this.databaseConnection);

        while (countContainers != 0) {

            Container container = DataBaseUtils.getContainerByCargo(cargoManifest.getIdentification(), countContainers, this.databaseConnection);
            cargoManifest.addContainersLoaded(container);
            countContainers--;
        }


        File file = new File("container.txt");

        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Container container : cargoManifest.getLoaded().inOrder()) {
                fileWriter.write(container.getPosition().xPos + "," + container.getPosition().yPos + "," + container.getPosition().zPos + "," + container.getIdentification() + "\n");
            }
        }
        return true;
    }
}

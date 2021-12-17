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


    public MatrixFileGenerator(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

    }

    public boolean generateMatrixFile(String id, int mmsi) throws SQLException, MatrixFileException, IOException {

        Ship s = DataBaseUtils.getShipByMmsi(mmsi,databaseConnection);
        int countContainers;
        CargoManifest cm = DataBaseUtils.getACargoByID(id,s, databaseConnection);

        if(cm == null){return false;}

        s.getCargoManifestAVL().insert(cm);


        countContainers = DataBaseUtils.countContainerByCargoManifest(cm.getIdentification(), this.databaseConnection);

        while (countContainers != 0) {

                Container c = DataBaseUtils.getContainerByCargo(cm.getIdentification(), countContainers, this.databaseConnection);
                cm.addContainersLoaded(c);
                countContainers--;
        }




        File myObj = new File("container.txt");

        try (FileWriter myWriter = new FileWriter(myObj)) {


            for (Container container : cm.getLoaded().inOrder()) {

                myWriter.write(container.getPosition().xPos + "," + container.getPosition().yPos + "," + container.getPosition().zPos + "," + container.getIdentification() + "\n");

            }


            }

        return true;
        }





}

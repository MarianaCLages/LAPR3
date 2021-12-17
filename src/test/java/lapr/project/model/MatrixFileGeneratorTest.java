package lapr.project.model;

import lapr.project.controller.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixFileGeneratorTest {

    @Test
    void generateMatrixFile() {

        MatrixFileGenerator matrixFileGenerator = new MatrixFileGenerator(App.getInstance().getDatabaseConnection());

        int mmsi = 257799000;
        String id = "1";

        try {
            matrixFileGenerator.generateMatrixFile(id,mmsi);
        } catch (Exception e) {

        }

    }

}
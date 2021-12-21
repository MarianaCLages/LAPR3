package lapr.project.model;

import lapr.project.controller.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixFileGeneratorTest {

    @Test
    void generateMatrixFile() {

        MatrixFileGenerator matrixFileGenerator = new MatrixFileGenerator(App.getInstance().getDatabaseConnection());

        String id = "1";

        try {
            matrixFileGenerator.generateMatrixFile(id);
        } catch (Exception e) {

        }

    }

}
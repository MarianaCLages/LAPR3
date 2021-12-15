package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.MatrixFileGenerator;
import lapr.project.shared.exceptions.MatrixFileException;

import java.sql.SQLException;

public class Create3DMatrixController {

    private final DatabaseConnection databaseConnection;
    private final MatrixFileGenerator matrixFileGenerator;

    public Create3DMatrixController() {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
        this.matrixFileGenerator = new MatrixFileGenerator(databaseConnection);
    }

    public boolean createMatrix(int mmsi) throws MatrixFileException, SQLException {

        return matrixFileGenerator.generateMatrixFile(mmsi);

    }

}
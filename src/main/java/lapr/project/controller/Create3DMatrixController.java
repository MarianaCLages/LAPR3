package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.shared.MatrixFileGenerator;
import lapr.project.shared.exceptions.MatrixFileException;

import java.io.IOException;
import java.sql.SQLException;

public class Create3DMatrixController {

    private final MatrixFileGenerator matrixFileGenerator;

    /**
     * Constructor.
     */
    public Create3DMatrixController() {
        DatabaseConnection databaseConnection = App.getInstance().getDatabaseConnection();
        this.matrixFileGenerator = new MatrixFileGenerator(databaseConnection);
    }

    /**
     * Creates the 3D matrix.
     *
     * @param cargoManifestId the cargo manifest ID
     * @return true if it succeeds, false if it doesn't
     * @throws MatrixFileException
     * @throws SQLException
     * @throws IOException
     */
    public boolean createMatrix(String cargoManifestId) throws MatrixFileException, SQLException, IOException {
        return matrixFileGenerator.generateMatrixFile(cargoManifestId);
    }
}
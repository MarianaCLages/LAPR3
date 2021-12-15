/*package lapr.project.controller;

import lapr.project.data.CargoManifestStoreData;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;
import lapr.project.model.MatrixFileGenerator;
import lapr.project.model.stores.CargoManifestStore;

import java.io.FileNotFoundException;

public class Create3DMatrixController {

    private final CargoManifestStore cargoManifestStore;
    private final CargoManifestStoreData cargoManifestStoreData;
    private final DatabaseConnection databaseConnection;

    public Create3DMatrixController() {
        Company company = App.getInstance().getCompany();
        this.cargoManifestStore = company.getCargoManifestStore();
        this.cargoManifestStoreData = company.getCargoManifestStoreData();
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }

    public boolean createMatrix() throws FileNotFoundException {
        boolean returnValue;

        returnValue = MatrixFileGenerator.generateMatrixFile(databaseConnection);

        return returnValue;
    }
}
*/

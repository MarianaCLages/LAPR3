package lapr.project.controller;

import lapr.project.data.DataBaseScripts.OffOrLoadContainers;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;
import lapr.project.shared.exceptions.*;

import java.sql.SQLException;

public class LoadedShipsController {

    private final Company company;
    private final OffOrLoadContainers offOrLoadContainers;
    private final DatabaseConnection databaseConnection;


    public LoadedShipsController() {
        this.company = App.getInstance().getCompany();
        this.offOrLoadContainers = new OffOrLoadContainers();
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }


    public String getLoadedShips(int mmsi, String type) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestIDException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException, SQLException {
        return (offOrLoadContainers.getResult(databaseConnection, mmsi, type));
    }
}
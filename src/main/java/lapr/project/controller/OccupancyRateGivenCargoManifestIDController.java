package lapr.project.controller;

import lapr.project.data.DataBaseScripts.OccupancyRateOfAGivenShip;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;
import lapr.project.shared.exceptions.*;

public class OccupancyRateGivenCargoManifestIDController {

    private final OccupancyRateOfAGivenShip occupancyRateOfAGivenShip;
    private final Company company;
    private final DatabaseConnection databaseConnection;

    public OccupancyRateGivenCargoManifestIDController(){
        this.company = App.getInstance().getCompany();
        this.occupancyRateOfAGivenShip = new OccupancyRateOfAGivenShip();
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }


    public double getOccupancyRate(int mmsi,String cargoManifestID) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException {

        return occupancyRateOfAGivenShip.occupancyRateInAShipGivenACargoManifestID(databaseConnection,mmsi,cargoManifestID);

    }

}

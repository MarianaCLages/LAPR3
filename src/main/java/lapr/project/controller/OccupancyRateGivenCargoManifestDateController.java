package lapr.project.controller;

import lapr.project.data.DataBaseScripts.OccupancyRateOfAGivenShip;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;
import lapr.project.shared.exceptions.*;

public class OccupancyRateGivenCargoManifestDateController {

    private final OccupancyRateOfAGivenShip occupancyRateOfAGivenShip;
    private final Company company;
    private final DatabaseConnection databaseConnection;

    public OccupancyRateGivenCargoManifestDateController(){
        this.company = App.getInstance().getCompany();
        this.occupancyRateOfAGivenShip = new OccupancyRateOfAGivenShip();
        this.databaseConnection = App.getInstance().getDatabaseConnection();
    }


    public double getOccupancyRate(int mmsi, String date) throws ShipCargoCapacityException, ContainerGrossException, ContainersInsideCargoManifestListSizeException, CargoManifestIDException, CargoManifestDoesntBelongToThatShipException, VehicleIDNotValidException, IllegalArgumentException {

        return occupancyRateOfAGivenShip.occupancyRateInAShipGivenACargoManifestDate(databaseConnection,mmsi,date);

    }

}

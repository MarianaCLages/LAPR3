package lapr.project.controller;

import lapr.project.data.CargoManifest;
import lapr.project.data.CargoManifestStoreData;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ShipWaterPhysicsController {

    private final Company company = App.getInstance().getCompany();
    private final ShipStore shipStore = company.getShipStore();
    private final CargoManifestStoreData cargoManifestStoreData = company.getCargoManifestStoreData();
    private Ship rShip;
    private CargoManifest cargoManifest;
    private int numContainers = 0;


    /**
     * Salt water's average density (g/cm^3)
     */
    private static final double waterDensity = 1.030;

    /**
     * Container's weight (Kg)
     */
    private static final double containerWeight = 500;

    /**
     * Gravity accelaration (m/s^2)
     */
    private static final double gravityAcceleration = 9.81;


    /**
     * All the container's combined weight
     */
    private double totalMass = 0;

    /**
     * Pressure the container's exert on the water
     */
    private double pressureExerted = 0;

    /**
     * Ship's height above water
     */
    private double heightAboveWater = 0;

    /**
     * Ship's submerged height, calculated from the container's added weight on the vessel
     */
    private double heightDiff = 0;



    /**
     * Finds the ship instance from the ship's callsign stored in the ShipStore class
     *
     * @param shipCallsign ship's name
     */
    public Ship getShip(String shipCallsign) {
        rShip = shipStore.getShipByCallSign(shipCallsign);
        return rShip;
    }

    public List<CargoManifest> getShipCargoManifests(){
        List<CargoManifest> lCargoManifest = null;
        for(CargoManifest cargoManifest : cargoManifestStoreData.getListCargoManifest(App.getInstance().getDatabaseConnection())){
            if(cargoManifest.getShip().getMmsi() == rShip.getMmsi()){
                lCargoManifest.add(cargoManifest);
            }
        }

        return lCargoManifest;
    }

    /**
     * Calculates all the data for the parameters: totalMass, pressureExerted and heightDiff
     *
     */
    public void calculateData(CargoManifest cargoManifest) {
        this.cargoManifest = cargoManifest;
        totalMass = calculateTotalMass(cargoManifest);
        pressureExerted = calculatePressureExerted();
        heightAboveWater = calculateHeightAboveWater();
    }

    /**
     * Calculates the total mass of all the containers in the cargo manifest
     *
     * @return the total mass of the containers present in the ship
     * @throws
     */
    private double calculateTotalMass(CargoManifest cargoManifest) {
        countLoadedContainers(cargoManifest);
        double totalMass = numContainers * containerWeight;
        return totalMass;
    }

    /**
     * Calculates the pressure exerted on the water from the containers present in the ship
     *
     * @return the mass of the container's pressure exerted on the water
     * @throws
     */
    private double calculatePressureExerted() {
        double pressureExerted = totalMass * gravityAcceleration;
        return pressureExerted;
    }

    /**
     * Calculates the ship's water height lost with all the extra weight of the containers and substracts that to the current set ship's draft
     *
     * @return the ship's current height above water
     */
    private double calculateHeightAboveWater() {
        heightDiff = totalMass / (waterDensity * rShip.getWidth() * rShip.getLength());
        return rShip.getDraft() - heightDiff;
    }

    /**
     * Gets the container count from the ship's current active cargo manifest
     *
     * @param cargoManifest ship's cargo manifest
     */
    private void countLoadedContainers(CargoManifest cargoManifest){

        numContainers = cargoManifest.countContainers();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Ship: " + rShip.getCallSign() + " - " + Integer.toString(rShip.getMmsi()) + "\n");
        sb.append("Cargo manifest ID: " + cargoManifest.getIdentification());
        sb.append("Total mass = " + Double.toString(totalMass) + " Kg\n");
        sb.append("Pressure exerted on water: " + Double.toString(pressureExerted) + " N\n");
        sb.append("Height above water: " + Double.toString(heightAboveWater) + " m\n");

        if(numContainers > 0){
            sb.append("Original ship's draft height: " + Double.toString(rShip.getDraft()) + " m --> Height difference: " + Double.toString(heightDiff) + " m\n");
        } else {
            sb.append("The ship has no loaded containers!!\n");
        }

        return sb.toString();
    }
}

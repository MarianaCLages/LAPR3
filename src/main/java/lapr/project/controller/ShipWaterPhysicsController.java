package lapr.project.controller;

import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.model.Ship;
import lapr.project.shared.exceptions.InvalidDataException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;


public class ShipWaterPhysicsController {

    private Ship rShip;
    private String cargoManifest;
    private int numContainers;


    /**
     * Salt water's average density (kg/m^3)
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
     * Finds the list of ship instances associated with the Ship Captain in the Database
     */
    public List<Ship> getShiplist() throws SQLException {
        List<Ship> lShip = DataBaseUtils.getShipCaptainShips(App.getInstance().getDatabaseConnection());
        return lShip;
    }

    public List<String> getShipCargoManifests(int shipmmsi) throws SQLException {
        return DataBaseUtils.getShipCargoManifests(shipmmsi, App.getInstance().getDatabaseConnection());
    }

    /**
     * Calculates all the data for the parameters: totalMass, pressureExerted and heightDiff
     */
    public void calculateData(String cargoManifest, Ship ship) throws SQLException, InvalidDataException {
        this.rShip = ship;
        this.cargoManifest = cargoManifest;
        this.numContainers = DataBaseUtils.countContainerByCargoManifest(cargoManifest, App.getInstance().getDatabaseConnection());
        this.totalMass = calculateTotalMass(this.numContainers);
        this.pressureExerted = calculatePressureExerted(this.totalMass);
        this.heightAboveWater = calculateHeightAboveWater(this.totalMass, this.rShip.getWidth(), this.rShip.getLength(), this.rShip.getDraft());
    }

    /**
     * Calculates the total mass of all the containers in the cargo manifest
     *
     * @return the total mass of the containers present in the ship
     * @throws
     */
    public double calculateTotalMass(int numContainers) throws InvalidDataException {
        double totalMass = numContainers * this.containerWeight;

        if(totalMass < 0) {
            throw new InvalidDataException("Número de containers inválido!");
        }

        return totalMass;
    }

    /**
     * Calculates the pressure exerted on the water from the containers present in the ship
     *
     * @return the mass of the container's pressure exerted on the water
     * @throws
     */
    public double calculatePressureExerted(double totalMass) {
        double pressureExerted = totalMass * this.gravityAcceleration;
        return pressureExerted;
    }

    /**
     * Calculates the ship's water height lost with all the extra weight of the containers and substracts that to the current set ship's draft
     *
     * @return the ship's current height above water
     */
    public double calculateHeightAboveWater(double totalMass, double shipWidth, double shipLength, double shipDraft) throws InvalidDataException {
        this.heightDiff = totalMass / (this.waterDensity * shipWidth * shipLength);

        if( this.heightDiff > shipDraft || shipLength <= 0 || shipWidth <= 0 || shipDraft <= 0){
            throw new InvalidDataException("Dimensões do navio inválidas!");
        }

        return shipDraft - this.heightDiff;
    }

    public String SummaryString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ship: " + this.rShip.getCallSign() + " - " + this.rShip.getMmsi() + "\n");
        sb.append("Cargo manifest ID: " + this.cargoManifest);
        sb.append("Total mass = " + new BigDecimal(this.totalMass).setScale(2, RoundingMode.HALF_DOWN) + " Kg\n");
        sb.append("Pressure exerted on water: " + new BigDecimal(this.pressureExerted).setScale(2,RoundingMode.HALF_DOWN) + " N\n");
        sb.append("Height above water: " + new BigDecimal(this.heightAboveWater).setScale(2,RoundingMode.HALF_DOWN) + " m\n");

        if (this.totalMass > 0) {
            sb.append("Original ship's draft height: " + new BigDecimal(this.rShip.getDraft()).setScale(2,RoundingMode.HALF_DOWN) + " m --> Height difference: " + new BigDecimal(this.heightDiff).setScale(2,RoundingMode.HALF_DOWN) + " m\n");
        } else {
            sb.append("The ship has no loaded containers!!\n");
        }

        return sb.toString();
    }
}

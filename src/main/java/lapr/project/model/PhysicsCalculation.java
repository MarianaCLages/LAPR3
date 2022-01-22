package lapr.project.model;

import lapr.project.shared.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class PhysicsCalculation {

    public static double calculateEnergyConsumptionDeterminedTripMinus5C() {
        double stainlessSteelResistance = 0.95 / (15 * Constants.AREA);
        double polyurethaneFoamResistance = 30 / (0.029 * Constants.AREA);
        double marinePlywoodResistance = 11.75 / (0.13 * Constants.AREA);

        double totalResistance = stainlessSteelResistance + polyurethaneFoamResistance + marinePlywoodResistance;

        // Q = temperature difference / total resistance
        double q = (20 + 5.0) / totalResistance;

        //Return = Q x time
        return (q * Constants.VOYAGE_TIME);
    }

    public static double calculateEnergyConsumptionDeterminedTrip7C() {
        double cortenSteelResistance = 2 / (25 * Constants.AREA);
        double extrudedPolystyreneResistance = 6.10 / (3.30 * Constants.AREA);
        double marinePlywoodResistance = 11.75 / (0.13 * Constants.AREA);

        double totalResistance = cortenSteelResistance + extrudedPolystyreneResistance + marinePlywoodResistance;

        // Q = temperature difference / total resistance
        double q = (20 - 7.0) / totalResistance;

        //Return = Q x time
        return (q * Constants.VOYAGE_TIME);
    }

    /**
     * Calculates the total energy to be supplied to a set of containers of -5ºC.
     *
     * @param numberOfContainers the number of containers
     * @param temperature        the container temperature
     * @param voyageTime         the trip time
     * @return the total energy to be supplied to a set of containers of -5ºC
     */
    public static double calculateTotalEnergySuppliedMinus5(int numberOfContainers, double temperature, int voyageTime) {
        double stainlessSteelResistance = 0.95 / (15 * Constants.AREA);
        double polyurethaneFoamResistance = 30 / (0.029 * Constants.AREA);
        double marinePlywoodResistance = 11.75 / (0.13 * Constants.AREA);

        double totalResistance = stainlessSteelResistance + polyurethaneFoamResistance + marinePlywoodResistance;

        // Q = temperature difference / total resistance
        double q = (temperature + 5.0) / totalResistance;

        //Energy = Q x time
        double energy = q * voyageTime;

        return (energy * numberOfContainers);
    }

    /**
     * Calculates the total energy to be supplied to a set of containers of 7ºC.
     *
     * @param numberOfContainers the number of containers
     * @param temperature        the container temperature
     * @param voyageTime         the trip time
     * @return the total energy to be supplied to a set of containers of 7ºC
     */
    public static double calculateTotalEnergySupplied7(int numberOfContainers, double temperature, int voyageTime) {
        double cortenSteelResistance = 2 / (25 * Constants.AREA);
        double extrudedPolystyreneResistance = 6.10 / (3.30 * Constants.AREA);
        double marinePlywoodResistance = 11.75 / (0.13 * Constants.AREA);

        double totalResistance = cortenSteelResistance + extrudedPolystyreneResistance + marinePlywoodResistance;

        // Q = temperature difference / total resistance
        double q = (temperature - 7.0) / totalResistance;

        //Energy = Q x time
        double energy = q * voyageTime;

        return (energy * numberOfContainers);
    }

    public static double calculateEnergyConsumptionMinus5(Map<Integer, Double> section, int frontFaces, int sideSides, int topSides) {

        double areaFront = frontFaces * Constants.CONTAINER_HEIGHT * Constants.CONTAINER_WIDTH;
        double areaSide = sideSides * Constants.CONTAINER_HEIGHT * Constants.CONTAINER_LENGTH;
        double areaTop = topSides * Constants.CONTAINER_WIDTH * Constants.CONTAINER_LENGTH;
        double areaTotal = areaFront + areaSide + areaTop;
        double thermalResistance = (1 / areaTotal) * (Constants.CONTAINER_EXTERIOR_MINUS5 / Double.parseDouble(System.getProperty("thermalConductivity.outer.minus5"))) + (Constants.CONTAINER_MID_MINUS5 / Double.parseDouble(System.getProperty("thermalConductivity.mid.minus5"))) + (Constants.CONTAINER_INTERIOR / Double.parseDouble(System.getProperty("thermalConductivity.inner.minus5")));

        double total = 0;
        for (Map.Entry<Integer, Double> entry : section.entrySet()) {

            total += (Math.abs(entry.getValue() - (-5)) / thermalResistance) * entry.getKey();

        }

        return total;
    }

    public static double calculateEnergyConsumption7(Map<Integer, Double> section, int frontFaces, int sideSides, int topSides) {
        double areaFront = frontFaces * Constants.CONTAINER_HEIGHT * Constants.CONTAINER_WIDTH;
        double areaSide = sideSides * Constants.CONTAINER_HEIGHT * Constants.CONTAINER_LENGTH;
        double areaTop = topSides * Constants.CONTAINER_WIDTH * Constants.CONTAINER_LENGTH;
        double areaTotal = areaFront + areaSide + areaTop;

        double thermalResistance = (1 / areaTotal) * (Constants.CONTAINER_EXTERIOR_7 / Double.parseDouble(System.getProperty("thermalConductivity.outer.7")) + (Constants.CONTAINER_MID_7 / Double.parseDouble(System.getProperty("thermalConductivity.mid.7"))) + (Constants.CONTAINER_INTERIOR / Double.parseDouble(System.getProperty("thermalConductivity.inner.7"))));


        double total = 0;
        for (Map.Entry<Integer, Double> entry : section.entrySet()) {

            total += (Math.abs(entry.getValue() - 7) / thermalResistance) * entry.getKey();

        }

        return total;
    }

    public static int calculateSuppliesNeededFor7(int numberOfContainers, double temperature, int voyageTime) {

        double energykw = (calculateTotalEnergySupplied7(numberOfContainers, temperature, voyageTime));
        System.out.println(energykw);
        int supliesNeeded = (int) Math.abs(Math.round(energykw / (75 * 1000)));

        if (supliesNeeded == 0) supliesNeeded = 1;

        return supliesNeeded;
    }

    public static int calculateSuppliesNeededForMinus5(int numberOfContainers, double temperature, int voyageTime) {


        double energykw = (calculateTotalEnergySuppliedMinus5(numberOfContainers, temperature, voyageTime));
        int supliesNeeded = (int) Math.abs(Math.round(energykw / (75 * 1000)));

        if (supliesNeeded == 0) supliesNeeded = 1;

        return supliesNeeded;
    }
}
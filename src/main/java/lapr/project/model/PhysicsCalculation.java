package lapr.project.model;

import lapr.project.shared.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class PhysicsCalculation {

    public static double calculateTotalEnergySuppliedMinus5(int numberOfContainers, double temperature, int voyageTime) {
        double containerArea;
        double totalResistance;
        double stainlessSteelResistance;
        double polyurethaneFoamResistance;
        double marinePlywoodResistance;

        containerArea = Constants.AREA;

        stainlessSteelResistance = 0.95 / (15 * containerArea);
        polyurethaneFoamResistance = 30 / (0.029 * containerArea);
        marinePlywoodResistance = 11.75 / (0.13 * containerArea);

        totalResistance = stainlessSteelResistance + polyurethaneFoamResistance + marinePlywoodResistance;

        // Q = temperature difference / total resistance
        double q;
        q = (temperature + 5.0) / totalResistance;

        //Energy = Q x time
        double energy;
        energy = q * voyageTime;

        return energy * numberOfContainers;
    }

    public static double calculateTotalEnergySupplied7(int numberOfContainers, double temperature, int voyageTime) {
        double containerArea;
        double totalResistance;
        double cortenSteelResistance;
        double extrudedPolystyreneResistance;
        double marinePlywoodResistance;

        containerArea = Constants.AREA;

        cortenSteelResistance = 2 / (25 * containerArea);
        extrudedPolystyreneResistance = 6.10 / (3.30 * containerArea);
        marinePlywoodResistance = 11.75 / (0.13 * containerArea);

        totalResistance = cortenSteelResistance + extrudedPolystyreneResistance + marinePlywoodResistance;

        // Q = temperature difference / total resistance
        double q;
        q = (temperature - 7.0) / totalResistance;

        //Energy = Q x time
        double energy;
        energy = q * voyageTime;

        return energy * numberOfContainers;
    }

    public static double calculateEnergyConsumptionMinus5(Map<Integer, Double> section, int frontFaces, int sideSides, int topSides) {

        double areaFront = frontFaces * Constants.CONTAINER_HEIGHT * Constants.CONTAINER_WIDTH;
        double areaSide = sideSides * Constants.CONTAINER_HEIGHT * Constants.CONTAINER_LENGTH;
        double areaTop = topSides * Constants.CONTAINER_WIDTH * Constants.CONTAINER_LENGTH;
        double thermalResistance = (1 / areaFront + areaSide + areaTop) * (Constants.CONTAINER_EXTERIOR_MINUS5 / Double.parseDouble(System.getProperty("thermalConductivity.outer.minus5"))) + (Constants.CONTAINER_MID_MINUS5 / Double.parseDouble(System.getProperty("thermalConductivity.mid.minus5"))) + (Constants.CONTAINER_INTERIOR / Double.parseDouble(System.getProperty("thermalConductivity.inner.minus5")));

        double total = 0;
        for (Map.Entry<Integer, Double> entry : section.entrySet()) {

            total += (Math.abs(entry.getValue() - (-5)) / thermalResistance) * entry.getKey();

        }

        return total;
    }

    public static double calculateEnergyConsumption7(LinkedHashMap<Integer, Double> section, int frontFaces, int sideSides, int topSides) {
        double areaFront = frontFaces * Constants.CONTAINER_HEIGHT * Constants.CONTAINER_WIDTH;
        double areaSide = sideSides * Constants.CONTAINER_HEIGHT * Constants.CONTAINER_LENGTH;
        double areaTop = topSides * Constants.CONTAINER_WIDTH * Constants.CONTAINER_LENGTH;
        double thermalResistance = (1 / areaFront + areaSide + areaTop) * (Constants.CONTAINER_EXTERIOR_7 / Double.parseDouble(System.getProperty("thermalConductivity.outer.7")) + (Constants.CONTAINER_MID_7 / Double.parseDouble(System.getProperty("thermalConductivity.mid.7"))) + (Constants.CONTAINER_INTERIOR / Double.parseDouble(System.getProperty("thermalConductivity.inner.7"))));

        double total = 0;
        for (Map.Entry<Integer, Double> entry : section.entrySet()) {

            total += (Math.abs(entry.getValue() - 7) / thermalResistance) * entry.getKey();

        }

        return total;
    }
}
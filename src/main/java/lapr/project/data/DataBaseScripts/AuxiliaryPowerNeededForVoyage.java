/*package lapr.project.data.DataBaseScripts;

import lapr.project.model.PhysicsCalculation;

public class AuxiliaryPowerNeededForVoyage {


    public AuxiliaryPowerNeededForVoyage() {
        //Empty Constructor
    }

    public String amountOfEnergyRequired(int mmsi, DatabaseConnection connection) throws SQLException, ProportionalityConstantNullException { //mmsi:310567000

        StringBuilder sb = new StringBuilder();
        int countCargo = DataBaseUtils.countCargoManifestByShip(mmsi, connection);
        GetMaterialsScript getMaterialsScript = new GetMaterialsScript(connection);
        int count = 0;
        List<Container> permanentList = new ArrayList<>();

        while (countCargo >= 0) {

            CargoManifest cargoManifest;
            cargoManifest = DataBaseUtils.getCargoManifestByMmsi(mmsi, count, null, connection);
            List<Container> changingList = DataBaseUtils.getAllContainersFromACargoManifest(cargoManifest.getIdentification(), connection);

            if (changingList != null) {
                for (Container c : changingList) {
                    permanentList.add(c);
                }
            }
            countCargo--;
            count++;
        }

        String[] walls = {"Outer Wall", "Inner Wall", "Intermediate Material"};
        double thermalResistance = 0;
        int times = 0;
        double supplies = 75;
        double saveI = 0;

        for (Container c : permanentList) {

            for (String wall : walls) {
                thermalResistance = thermalResistance + Constants.GROSS / (getMaterialsScript.getKConstant(Integer.parseInt(c.getIdentification()), wall) * Constants.AREA);
            }

            double I = 7 / thermalResistance;
            saveI = saveI + I;
            sb.append("Energy needed of Container " + c.getIdentification() + ": " + I + "Kw\n");


        }

        times = (int) ((int) saveI / supplies);

        sb.append("In Total: " + saveI + " Kw\n" + times + " supplies is needed");

        return sb.toString();
    }

    public int amountOfEnergyRequiredFor7(int numberOfContainers, double temperature, int voyageTime){

        int supplies = PhysicsCalculation.calculateSuppliesNeededFor7(numberOfContainers,temperature,voyageTime);

        return supplies;
    }

    public int amountOfEnergyRequiredFor5(int numberOfContainers, double temperature, int voyageTime){

        int supplies = PhysicsCalculation.calculateSuppliesNeededForMinus5(numberOfContainers,temperature,voyageTime);

        return supplies;
    }
}
*/
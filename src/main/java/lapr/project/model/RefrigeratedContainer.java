package lapr.project.model;

public class RefrigeratedContainer extends Container {

    private final double energyConsume;
    private final double temperature;

    public RefrigeratedContainer(String identification, int payload, int tare, int gross, String isoCode, double energyConsume, double temperature) {
        super(identification, payload, tare, gross, isoCode, true);
        this.energyConsume = energyConsume;
        this.temperature = temperature;
    }

    public double getEnergyConsume() {
        return energyConsume;
    }

    public double getTemperature() {
        return temperature;
    }
}

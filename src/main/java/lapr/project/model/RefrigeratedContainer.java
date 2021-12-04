package lapr.project.model;

public class RefrigeratedContainer extends Container {

    private final double energyConsume;
    private final double temperature;

    /**
     * Constructor.
     *
     * @param identification the refrigerated container's identification
     * @param payload        the refrigerated container's payload
     * @param tare           the refrigerated container's tare
     * @param gross          the refrigerated container's gross
     * @param isoCode        the refrigerated container's ISO code
     * @param energyConsume  the refrigerated container's energy consume
     * @param temperature    the refrigerated container's temperature
     */
    public RefrigeratedContainer(String identification, int payload, int tare, int gross, String isoCode, double energyConsume, double temperature) {
        super(identification, payload, tare, gross, isoCode, true, false);
        this.energyConsume = energyConsume;
        this.temperature = temperature;
    }

    //Getters

    /**
     * Gets the refrigerated container's energy consume.
     *
     * @return the refrigerated container's energy consume
     */
    public double getEnergyConsume() {
        return energyConsume;
    }

    /**
     * Gets the refrigerated container's temperature.
     *
     * @return the refrigerated container's temperature
     */
    public double getTemperature() {
        return temperature;
    }
}

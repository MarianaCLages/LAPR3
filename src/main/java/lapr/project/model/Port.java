package lapr.project.model;

public class Port extends Facility {

    /**
     * Constructor.
     *
     * @param identification the port's identification
     * @param name           the port's name
     * @param continent      the port's continent
     * @param country        the port's country
     * @param location       the port's location
     */
    public Port(String identification, String name, String continent, String country, FacilityLocation location) {
        super(identification, name, continent, country, location);
    }
}
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
    public Port(String identification, String name, String continent, String country, FacilityLocation location,int capacity) {
        super(identification, name, continent, country, location,capacity);
    }

    /**
     * Returns the textual description of the facility in the format: identification, name, continent, country, location.
     * @return the port's characteristics
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
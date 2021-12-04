package lapr.project.model;

import java.util.List;

public class Port extends Facility {

    private List<Container> lContainer;
    private CargoManifest cargoManifest;

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

    /**
     * Returns the textual description of the facility in the format: identification, name, continent, country, location.
     * @return the port's characteristics
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
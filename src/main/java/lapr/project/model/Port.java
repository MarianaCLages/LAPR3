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
    public Port(String continent, String country, String identification, String name, FacilityLocation location) {
        super(continent, country, identification, name, location);
    }


    @Override
    public String toString() {
        return super.toString();
    }

}
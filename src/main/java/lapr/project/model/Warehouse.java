package lapr.project.model;

public class Warehouse extends Facility {

    /**
     * Constructor.
     *
     * @param identification the warehouse's identification
     * @param name           the warehouse's name
     * @param continent      the warehouse's continent
     * @param country        the warehouse's country
     * @param location       the warehouse's location
     */
    public Warehouse(String identification, String name, String continent, String country, FacilityLocation location,int capacity) {
        super(identification, name, continent, country, location,capacity);
    }
}

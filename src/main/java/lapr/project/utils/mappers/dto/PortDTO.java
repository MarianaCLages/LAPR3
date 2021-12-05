package lapr.project.utils.mappers.dto;

import lapr.project.model.FacilityLocation;

public class PortDTO {

    private final String identification;
    private final String name;
    private final String continent;
    private final String country;
    private final FacilityLocation facilityLocation;

    /**
     * @param identification the port's identification
     * @param name           the port's name
     * @param continent      the port's continent
     * @param country        the port's country
     * @param location       the port's location
     */
    public PortDTO(String identification, String name, String continent, String country, FacilityLocation location) {
        this.identification = identification;
        this.name = name;
        this.continent = continent;
        this.country = country;
        this.facilityLocation = location;
    }

    /**
     * Returns the textual description of the port info in the format: identification, name, continent, country, facilityLocation.
     *
     * @return the Port's characteristics
     */
    @Override
    public String toString() {
        return "Port Information :" + "\n" +
                "Identification: " + identification + "\n" +
                "Name: " + name + "\n" +
                "Continent: " + continent + "\n" +
                "Country: " + country + "\n" +
                "Longitude = " + facilityLocation.getLongitude() + "\n" +
                "Latitude = " + facilityLocation.getLatitude() + "\n";
    }
}

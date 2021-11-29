package lapr.project.utils.mappers.dto;

import lapr.project.model.FacilityLocation;

public class PortDTO {

    private final String continent;
    private final String country;
    private final String identification;
    private final String name;
    private final FacilityLocation facilityLocation;

    public PortDTO(String continent, String country, String identification, String name, FacilityLocation location) {
        this.continent = continent;
        this.country = country;
        this.identification = identification;
        this.name = name;
        this.facilityLocation = location;
    }

    @Override
    public String toString() {
        return "Port{" +
                "continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", identification='" + identification + '\'' +
                ", name='" + name + '\'' +
                ", facilityLocation=" + facilityLocation +
                '}';
    }
}

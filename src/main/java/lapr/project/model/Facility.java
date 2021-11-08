package lapr.project.model;

import org.apache.commons.lang3.StringUtils;

public class Facility {
    private String identification;
    private String name;
    private String continent;
    private String country;
    private FacilityLocation location;

    public Facility(String identification, String name, String continent, String country, FacilityLocation location) {
        if (!checkId(identification)) throw new IllegalArgumentException("Invalid Identification");
        this.identification = identification;
        this.name = name;
        this.continent = continent;
        this.country = country;
        this.location = location;
    }

    //Checks
    public boolean checkId(String id) {
        return StringUtils.isNumeric(id) && id.length() == 5;
    }

    //Getters
    public String getIdentification() {
        return identification;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public FacilityLocation getLocation() {
        return location;
    }

    //Setters
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLocation(FacilityLocation location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Facility)) return false;
        Facility facility = (Facility) o;
        return getIdentification().equals(facility.getIdentification()) && getName().equals(facility.getName()) && getContinent().equals(facility.getContinent()) && getCountry().equals(facility.getCountry()) && getLocation().equals(facility.getLocation());
    }

    @Override
    public int hashCode() {
        int result = getIdentification() != null ? getIdentification().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getContinent() != null ? getContinent().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        return result;
    }
}

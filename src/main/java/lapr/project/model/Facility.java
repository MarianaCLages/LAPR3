package lapr.project.model;

import org.apache.commons.lang3.StringUtils;

public class Facility {
    private String identification;
    private String name;
    private String continent;
    private String country;
    private FacilityLocation location;

    /**
     * Constructor.
     *
     * @param identification the facility's identification
     * @param name           the facility's name
     * @param continent      the facility's continent
     * @param country        the facility's country
     * @param location       the facility's location
     */
    public Facility(String identification, String name, String continent, String country, FacilityLocation location) {
        if (!checkId(identification)) throw new IllegalArgumentException("Invalid Identification");
        this.identification = identification;
        this.name = name;
        this.continent = continent;
        this.country = country;
        this.location = location;
    }

    /**
     * Checks the facility's identification.
     *
     * @param id the facility's identification
     * @return true if it is valid, false if it isn't
     */
    //Checks
    public boolean checkId(String id) {
        return StringUtils.isNumeric(id) && id.length() == 5;
    }

    //Getters

    /**
     * Gets the facility's identification.
     *
     * @return the facility's identification
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Gets the facility's name.
     *
     * @return the facility's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the facility's continent.
     *
     * @return the facility's continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Gets the facility's country.
     *
     * @return the facility's country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the facility's location.
     *
     * @return the facility's location
     */
    public FacilityLocation getLocation() {
        return location;
    }

    //Setters

    /**
     * Sets the facility's identification.
     *
     * @param identification the facility's identification
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Sets the facility's name.
     *
     * @param name the facility's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the facility's continent.
     *
     * @param continent the facility's continent
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Sets the facility's country.
     *
     * @param country the facility's country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets the facility's location.
     *
     * @param location the facility's location
     */
    public void setLocation(FacilityLocation location) {
        this.location = location;
    }

    /**
     * Checks if two objects (Facility) are equal.
     *
     * @param o the object
     * @return true if objects are equal, false if they aren't
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Facility)) return false;
        Facility facility = (Facility) o;
        return getIdentification().equals(facility.getIdentification()) && getName().equals(facility.getName()) && getContinent().equals(facility.getContinent()) && getCountry().equals(facility.getCountry()) && getLocation().equals(facility.getLocation());
    }

    /**
     * Generates a hash code for the facility values.
     *
     * @return the hash code for the facility values
     */
    @Override
    public int hashCode() {
        int result = getIdentification() != null ? getIdentification().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getContinent() != null ? getContinent().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "identification='" + identification + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", location=" + location +
                '}';
    }
}

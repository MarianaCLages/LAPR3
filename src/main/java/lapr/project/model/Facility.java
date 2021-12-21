package lapr.project.model;

import lapr.project.shared.graph.Vertex;

public class Facility implements Vertex {
    private String identification;
    private String name;
    private String continent;
    private Country country;
    private String countryName;
    private FacilityLocation location;
    private int capacity;

    /**
     * Constructor.
     *
     * @param identification the facility's identification
     * @param name           the facility's name
     * @param continent      the facility's continent
     * @param country        the facility's country
     * @param location       the facility's location
     */
    public Facility(String identification, String name, String continent, Country country, FacilityLocation location, int capacity) {
        this.identification = identification;
        this.name = name;
        this.continent = continent;
        this.country = country;
        this.location = location;
        this.capacity = capacity;
    }

    public Facility(String identification, String name, String continent, String countryName, FacilityLocation location, int capacity) {
        this.identification = identification;
        this.name = name;
        this.continent = continent;
        this.countryName = countryName;
        this.location = location;
        this.capacity = capacity;
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
     * Sets the facility's identification.
     *
     * @param identification the facility's identification
     */
    public void setIdentification(String identification) {
        this.identification = identification;
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
     * Sets the facility's name.
     *
     * @param name the facility's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the facility's continent.
     *
     * @return the facility's continent
     */
    public String getContinent() {
        return continent;
    }

    //Setters

    /**
     * Sets the facility's continent.
     *
     * @param continent the facility's continent
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Gets the facility's country.
     *
     * @return the facility's country
     */
    public String getCountry() {
        if (country == null) {
            return countryName;
        }
        return country.toString();
    }

    /**
     * Sets the facility's country.
     *
     * @param country the facility's country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the facility's location.
     *
     * @return the facility's location
     */
    public FacilityLocation getLocation() {
        return location;
    }

    /**
     * Sets the facility's location.
     *
     * @param location the facility's location
     */
    public void setLocation(FacilityLocation location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Facility)) return false;

        Facility facility = (Facility) o;

        return getIdentification() != null ? getIdentification().equals(facility.getIdentification()) : facility.getIdentification() == null;
    }

    @Override
    public int hashCode() {
        return getIdentification() != null ? getIdentification().hashCode() : 0;
    }

    /**
     * Returns the textual description of the facility in the format: identification, name, continent, country, location.
     *
     * @return the facility's characteristics
     */
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

    /**
     * Compares the latitude and longitude of two different objects (Facility).
     *
     * @param o the object to compare (Facility)
     * @return 0 if they're equal, -1 or 1 if they're different
     */
    @Override
    public int compareTo(Vertex o) {
        if (this.getLongitude() > o.getLongitude()) return 1;
        if (this.getLongitude() < o.getLongitude()) return -1;

        if (this.getLatitude() > o.getLatitude()) return 1;
        if (this.getLatitude() < o.getLatitude()) return -1;

        return 0;
    }

    @Override
    public String getDesignation() {
        return this.name;
    }

    @Override
    public double getLongitude() {
        return location.getLongitude();
    }

    @Override
    public double getLatitude() {
        return location.getLatitude();
    }


}

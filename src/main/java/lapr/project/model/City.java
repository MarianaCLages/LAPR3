package lapr.project.model;

import lapr.project.shared.graph.Vertex;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class City implements Vertex {
    private final String name;
    private final double latitude;
    private final double longitude;
    private final Country country;
    private int color;

    /**
     * Constructor.
     *
     * @param name      the city's name
     * @param latitude  the city's latitude
     * @param longitude the city's longitude
     * @param country   the city's country
     */
    public City(String name, double latitude, double longitude, Country country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.color = -1;
    }

    /**
     * Checks if the country of two objects (City) are equal.
     *
     * @param o the object
     * @return true if they are, false if they aren't
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (!Objects.equals(name, city.name)) return false;
        return getCountry() != null ? getCountry().equals(city.getCountry()) : city.getCountry() == null;
    }

    /**
     * Returns the textual description of the city in the format: name, latitude, longitude, country.
     *
     * @return the city's characteristics
     */
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country=" + country +
                '}';
    }

    @Override
    public String getDesignation() {
        return this.name;
    }

    @Override
    public double getLongitude() {
        return this.longitude;
    }

    @Override
    public double getLatitude() {
        return this.latitude;
    }

    @Override
    public String getCountry() {
        return this.country.getName();
    }

    @Override
    public String getContinent() {
        return this.country.getContinent().getName();
    }

    @Override
    public int compareTo(@NotNull Vertex o) {
        return 0;
    }

    @Override
    public boolean isColour() {
        return color != -1;
    }

    @Override
    public int getColour() {
        return color;
    }

    @Override
    public void setColour(int colour) {
        this.color = colour;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

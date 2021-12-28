package lapr.project.model;

import lapr.project.shared.graph.Vertex;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class City implements Vertex {
    private String name;
    private double latitude;
    private double longitude;
    private Country country;
    private int color;

    public City(String name, double latitude, double longitude, Country country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.color = -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (!Objects.equals(name, city.name)) return false;
        return getCountry() != null ? getCountry().equals(city.getCountry()) : city.getCountry() == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        return result;
    }

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
    public int compareTo(@NotNull Vertex o) {
        return 0;
    }

    @Override
    public boolean isColor() {
        return color != -1;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int colour) {
        this.color = colour;
    }
}

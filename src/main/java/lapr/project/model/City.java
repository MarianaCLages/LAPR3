package lapr.project.model;

import lapr.project.shared.graph.Vertex;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class City implements Vertex {
    private String name;
    private double latitude;
    private double longitude;
    private Country country;

    public City(String name, double latitude, double longitude, Country country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (Double.compare(city.getLatitude(), getLatitude()) != 0) return false;
        if (Double.compare(city.getLongitude(), getLongitude()) != 0) return false;
        if (!Objects.equals(name, city.name)) return false;
        return getCountry() != null ? getCountry().equals(city.getCountry()) : city.getCountry() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
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
}

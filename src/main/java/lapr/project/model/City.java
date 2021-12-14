package lapr.project.model;

import lapr.project.shared.graph.Vertex;
import org.jetbrains.annotations.NotNull;

public class City implements Vertex {
    private String name;
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
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
    public int compareTo(@NotNull Vertex o) {
        return 0;
    }
}

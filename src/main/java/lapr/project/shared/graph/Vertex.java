package lapr.project.shared.graph;

public interface Vertex extends Comparable<Vertex> {
    String getDesignation();

    double getLongitude();

    double getLatitude();

    String getCountry();
}

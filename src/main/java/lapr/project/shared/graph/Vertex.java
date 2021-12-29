package lapr.project.shared.graph;

public interface Vertex extends Comparable<Vertex> {
    String getDesignation();

    double getLongitude();

    double getLatitude();

    String getCountry();

    String getContinent();

    boolean equals(Object o);

    boolean isColor();

    int getColor();

    void setColor(int colour);
}

package lapr.project.shared.graph;

public interface Vertex extends Comparable<Vertex> {

    /**
     * Gets the vertex designation.
     *
     * @return the vertex designation
     */
    String getDesignation();

    /**
     * Gets the vertex longitude.
     *
     * @return the vertex longitude
     */
    double getLongitude();

    /**
     * Gets the vertex latitude.
     *
     * @return the vertex latitude
     */
    double getLatitude();

    /**
     * Gets the vertex country.
     *
     * @return the vertex country
     */
    String getCountry();

    /**
     * Gets the vertex continent.
     *
     * @return the vertex continent
     */
    String getContinent();

    /**
     * Checks if two objects (Vertex) are equal.
     *
     * @param o the object
     * @return true if objects are equal, false if they aren't
     */
    boolean equals(Object o);

    /**
     * Checks if is colour.
     *
     * @return true if it is, false if it isn't
     */
    boolean isColour();

    /**
     * Gets the vertex colour.
     *
     * @return the vertex colour
     */
    int getColour();

    /**
     * Sets the vertex colour
     *
     * @param colour the vertex colour
     */
    void setColour(int colour);

    /**
     * Gets the vertex name.
     *
     * @return the vertex name
     */
    String getName();

}

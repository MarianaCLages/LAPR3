package lapr.project.shared.graph;

import java.util.Objects;

/**
 * @param <V> Vertex value type
 * @param <E> Edge value type
 * @author DEI-ESINF
 */
public class Edge<V, E> {
    private final V vOrig;         // vertex origin
    private final V vDest;        // vertex destination
    private E weight;            // Edge weight

    /**
     * Constructor.
     *
     * @param vOrig  the edge's origin vertex
     * @param vDest  the edge's destination vertex
     * @param weight the edge's weight
     */
    public Edge(V vOrig, V vDest, E weight) {
        if ((vOrig == null) || (vDest == null)) throw new RuntimeException("Edge vertices cannot be null!");
        this.vOrig = vOrig;
        this.vDest = vDest;
        this.weight = weight;
    }

    /**
     * Gets the edge's origin vertex.
     *
     * @return the edge's origin vertex
     */
    public V getVOrig() {
        return vOrig;
    }

    /**
     * Gets the edge's destination vertex.
     *
     * @return the edge's destination vertex
     */
    public V getVDest() {
        return vDest;
    }

    /**
     * Gets the edge's weight.
     *
     * @return the edge's weight
     */
    public E getWeight() {
        return weight;
    }

    /**
     * Sets the edge's weight.
     *
     * @param weight the edge's weight
     */
    public void setWeight(E weight) {
        this.weight = weight;
    }

    /**
     * Returns the textual description of the edge in the format: origin vertex, destination vertex, weight.
     *
     * @return the edge's characteristics
     */
    @Override
    public String toString() {
        return String.format("%s -> %s\nWeight: %s", vOrig, vDest, weight);
    }

    /**
     * Checks if two objects (Edge) are equal.
     *
     * @param o the object
     * @return true if objects are equal, false if they aren't
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked") Edge<V, E> edge = (Edge<V, E>) o;
        return vOrig.equals(edge.vOrig) &&
                vDest.equals(edge.vDest);
    }

    /**
     * Generates a hash code for the edge values.
     *
     * @return the hash code for the edge values
     */
    @Override
    public int hashCode() {
        return Objects.hash(vOrig, vDest);
    }
}

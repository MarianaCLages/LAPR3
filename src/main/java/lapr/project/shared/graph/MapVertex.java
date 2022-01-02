package lapr.project.shared.graph;

import lapr.project.shared.exceptions.NullVerticesException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapVertex<V, E> {

    private final V element;                       // Vertex information
    private final Map<V, Edge<V, E>> outVerts;    // Adjacent vertices

    /**
     * Constructor.
     *
     * @param vert the vertex
     * @throws NullVerticesException
     */
    public MapVertex(V vert) throws NullVerticesException {
        if (vert == null) throw new NullVerticesException();
        element = vert;
        outVerts = new LinkedHashMap<>();
    }

    /**
     * Gets the element (vertex).
     *
     * @return the element (vertex)
     */
    public V getElement() {
        return element;
    }

    /**
     * Adds an adjacent vertex.
     *
     * @param vAdj the adjacent vertex to add
     * @param edge the edge
     */
    public void addAdjVert(V vAdj, Edge<V, E> edge) {
        outVerts.put(vAdj, edge);
    }

    /**
     * Removes an adjacent vertex.
     *
     * @param vAdj the adjacent vertex to remove
     */
    public void remAdjVert(V vAdj) {
        outVerts.remove(vAdj);
    }

    /**
     * Gets the edge.
     *
     * @param vAdj the adjacent vertex
     * @return the edge
     */
    public Edge<V, E> getEdge(V vAdj) {
        return outVerts.get(vAdj);
    }

    /**
     * Gets the number of adjacent vertices.
     *
     * @return the number of adjacent vertices
     */
    public int numAdjVerts() {
        return outVerts.size();
    }

    /**
     * Gets a list of all adjacent vertices.
     *
     * @return a list of all adjacent vertices
     */
    public Collection<V> getAllAdjVerts() {
        return new ArrayList<>(outVerts.keySet());
    }

    /**
     * Gets a list of all outgoing edges.
     *
     * @return a list of all outgoing edges
     */
    public Collection<Edge<V, E>> getAllOutEdges() {
        return new ArrayList<>(outVerts.values());
    }

    /**
     * Returns the textual description of the map vertex.
     *
     * @return the map vertex's characteristics
     */
    @Override
    public String toString() {
        StringBuilder st = new StringBuilder(element + ": \n");
        if (!outVerts.isEmpty())
            for (V vert : outVerts.keySet())
                st.append(outVerts.get(vert));

        return st.toString();
    }
}

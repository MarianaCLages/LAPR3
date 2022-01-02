package lapr.project.shared.graph;

import lapr.project.shared.exceptions.NullVerticesException;

import java.util.*;

public class MapGraph<V, E> extends CommonGraph<V, E> {

    private final Map<V, MapVertex<V, E>> mapVertices;  // all the Vertices of the graph

    /**
     * Constructor.
     *
     * @param directed boolean that says if the map is directed or not
     */
    // Constructs an empty graph (either undirected or directed)
    public MapGraph(boolean directed) {
        super(directed);
        mapVertices = new LinkedHashMap<>();
    }

    /**
     * Constructor.
     *
     * @param g the graph
     * @throws NullVerticesException
     */
    public MapGraph(Graph<V, E> g) throws NullVerticesException {
        this(g.isDirected());
        copy(g, this);
    }

    /**
     * Checks if the vertex is valid (not null).
     *
     * @param vert the vertex
     * @return true if is valid, false if it isn't
     */
    @Override
    public boolean validVertex(V vert) {
        return (mapVertices.get(vert) != null);
    }

    /**
     * Gets the adjacent vertices.
     *
     * @param vert the vertex for which to find adjacent vertices
     * @return the adjacent vertices
     */
    @Override
    public Collection<V> adjVertices(V vert) {

        if (!validVertex(vert)) {
            return Collections.emptyList();
        }

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.getAllAdjVerts();
    }

    /**
     * Gets the list of outgoing edges.
     *
     * @return the list of outgoing edges
     */
    @Override
    public Collection<Edge<V, E>> edges() {

        ArrayList<Edge<V, E>> le = new ArrayList<>(numEdges);

        for (MapVertex<V, E> mv : mapVertices.values())
            le.addAll(mv.getAllOutEdges());

        return le;
    }

    /**
     * Gets the edge from the map graph.
     *
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @return the edge from the map
     */
    @Override
    public Edge<V, E> edge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest))
            return null;

        MapVertex<V, E> mv = mapVertices.get(vOrig);

        return mv.getEdge(vDest);
    }

    /**
     * Creates an edge.
     *
     * @param vOrigKey the key of vertex vOrig
     * @param vDestKey the key of vertex vDist
     * @return the edge created
     */
    @Override
    public Edge<V, E> edge(int vOrigKey, int vDestKey) {
        V vOrig = vertex(vOrigKey);
        V vDest = vertex(vDestKey);

        return edge(vOrig, vDest);
    }

    /**
     * Gets the degree of the outgoing edges.
     *
     * @param vert the vertex of interest
     * @return the degree of the outgoing edges
     */
    @Override
    public int outDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.numAdjVerts();
    }

    /**
     * Gets the degree of the incoming edges.
     *
     * @param vert the vertex of interest
     * @return the degree of the incoming edges
     */
    @Override
    public int inDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        int degree = 0;
        for (V otherVert : mapVertices.keySet())
            if (edge(otherVert, vert) != null)
                degree++;

        return degree;
    }

    /**
     * Gets all the outgoing edges.
     *
     * @param vert the vertex of interest
     * @return all the outgoing edges
     */
    @Override
    public Collection<Edge<V, E>> outgoingEdges(V vert) {

        if (!validVertex(vert))
            return null;

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.getAllOutEdges();
    }

    /**
     * Gets all the incoming edges.
     *
     * @param vert the vertex of interest
     * @return all the incoming edges
     */
    @Override
    public Collection<Edge<V, E>> incomingEdges(V vert) {

        if (!validVertex(vert)) {
            return null;
        }

        ArrayList<Edge<V, E>> edgelist = new ArrayList<>();

        for (V other : mapVertices.keySet()) {
            Edge<V, E> e = edge(other, vert);

            if (e != null) {
                edgelist.add(e);
            }
        }

        return edgelist;
    }

    /**
     * Adds a vertex.
     *
     * @param vert the vertex to add
     * @return true if it succeeds, false if it doesn't
     * @throws NullVerticesException
     */
    @Override
    public boolean addVertex(V vert) throws NullVerticesException {

        if (vert == null) throw new NullVerticesException();
        if (validVertex(vert))
            return false;

        MapVertex<V, E> mv = new MapVertex<>(vert);
        vertices.add(vert);
        mapVertices.put(vert, mv);
        numVerts++;

        return true;
    }

    /**
     * Adds an edge.
     *
     * @param vOrig  origin vertex
     * @param vDest  destination vertex
     * @param weight the weight of the edge
     * @return true if it succeeds, false if it doesn't
     * @throws NullVerticesException
     */
    @Override
    public boolean addEdge(V vOrig, V vDest, E weight) throws NullVerticesException {

        if (vOrig == null || vDest == null) throw new NullVerticesException();
        if (edge(vOrig, vDest) != null)
            return false;

        if (!validVertex(vOrig))
            addVertex(vOrig);

        if (!validVertex(vDest))
            addVertex(vDest);

        MapVertex<V, E> mvo = mapVertices.get(vOrig);
        MapVertex<V, E> mvd = mapVertices.get(vDest);

        Edge<V, E> newEdge = new Edge<>(mvo.getElement(), mvd.getElement(), weight);
        mvo.addAdjVert(mvd.getElement(), newEdge);
        numEdges++;

        //if graph is not direct insert other edge in the opposite direction
        if (!isDirected)
            // if vDest different vOrig
            if (edge(vDest, vOrig) == null) {
                Edge<V, E> otherEdge = new Edge<>(mvd.getElement(), mvo.getElement(), weight);
                mvd.addAdjVert(mvo.getElement(), otherEdge);
                numEdges++;
            }

        return true;
    }

    /**
     * Removes a vertex.
     *
     * @param vert the vertex to remove
     * @return true if it succeeds, false if it doesn't
     * @throws NullVerticesException
     */
    @Override
    public boolean removeVertex(V vert) throws NullVerticesException {

        if (vert == null) throw new NullVerticesException();
        if (!validVertex(vert))
            return false;

        //remove all edges that point to vert
        for (Edge<V, E> edge : incomingEdges(vert)) {
            removeEdge(edge.getVOrig(), vert);
        }

        MapVertex<V, E> mv = mapVertices.get(vert);

        //The edges that live from vert are removed with the vertex
        numEdges -= mv.numAdjVerts();
        mapVertices.remove(vert);
        vertices.remove(vert);

        numVerts--;

        return true;
    }

    /**
     * Removes an edge.
     *
     * @param vOrig vertex origin of the edge
     * @param vDest vertex destination of the edge
     * @return true if it succeeds, false if it doesn't
     * @throws NullVerticesException
     */
    @Override
    public boolean removeEdge(V vOrig, V vDest) throws NullVerticesException {

        if (vOrig == null || vDest == null) throw new NullVerticesException();
        if (!validVertex(vOrig) || !validVertex(vDest))
            return false;

        Edge<V, E> edge = edge(vOrig, vDest);

        if (edge == null)
            return false;

        MapVertex<V, E> mvo = mapVertices.get(vOrig);

        mvo.remAdjVert(vDest);
        numEdges--;

        //if graph is not directed
        if (!isDirected) {
            edge = edge(vDest, vOrig);
            if (edge != null) {
                MapVertex<V, E> mvd = mapVertices.get(vDest);
                mvd.remAdjVert(vOrig);
                numEdges--;
            }
        }
        return true;
    }

    /**
     * Clones the graph.
     *
     * @return a clone of the graph
     */
    @Override
    public MapGraph<V, E> clone() {

        MapGraph<V, E> g = new MapGraph<>(this.isDirected);

        try {
            copy(this, g);
        } catch (NullVerticesException e) {
            e.printStackTrace();
        }

        return g;
    }

    /**
     * Returns the textual description of the map graph in the format: vertices, edges.
     *
     * @return the map graph's characteristics
     */
    //string representation
    @Override
    public String toString() {
        String s;
        if (numVerts == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + numVerts + " vertices, " + numEdges + " edges\n";
            for (MapVertex<V, E> mv : mapVertices.values())
                s += mv + "\n";
        }
        return s;
    }
}

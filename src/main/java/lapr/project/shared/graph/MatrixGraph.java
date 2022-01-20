package lapr.project.shared.graph;

import lapr.project.shared.exceptions.NullVerticesException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MatrixGraph<V, E> extends CommonGraph<V, E> {

    public static final int INITIAL_CAPACITY = 10;
    public static final float RESIZE_FACTOR = 1.5F;

    Edge<V, E>[][] edgeMatrix;

    /**
     * Constructor.
     *
     * @param directed        boolean that says if the matrix graph is directed or not
     * @param initialCapacity the matrix graph's initial capacity (constant)
     */
    @SuppressWarnings("unchecked")
    public MatrixGraph(boolean directed, int initialCapacity) {
        super(directed);
        edgeMatrix = (Edge<V, E>[][]) (new Edge<?, ?>[initialCapacity][initialCapacity]);
    }

    /**
     * Constructor.
     *
     * @param directed boolean that says if the matrix graph is directed or not
     */
    public MatrixGraph(boolean directed) {
        this(directed, INITIAL_CAPACITY);
    }

    /**
     * Constructor.
     *
     * @param g the graph
     * @throws NullVerticesException
     */
    public MatrixGraph(Graph<V, E> g) throws NullVerticesException {
        this(g.isDirected(), g.numVertices());
        copy(g, this);
    }

    /**
     * Constructor.
     *
     * @param directed boolean that says if the matrix graph is directed or not
     * @param vs       the list of vertices
     * @param m        the edge matrix
     */
    public MatrixGraph(boolean directed, ArrayList<V> vs, E[][] m) {
        this(directed, vs.size());
        numVerts = vs.size();
        vertices = new ArrayList<>(vs);
        for (int i = 0; i < numVerts; i++)
            for (int j = 0; j < numVerts; j++)
                if (j != i && m[i][j] != null)
                    addEdge(vertices.get(i), vertices.get(j), m[i][j]);
    }

    /**
     * Gets the adjacent vertices of a vertex.
     *
     * @param vert the vertex for which to find adjacent vertices
     * @return the adjacent vertices of a vertex
     */
    @Override
    public Collection<V> adjVertices(V vert) {
        int index = key(vert);
        if (index == -1)
            return null;

        ArrayList<V> outVertices = new ArrayList<>();
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[index][i] != null)
                outVertices.add(vertices.get(i));
        return outVertices;
    }

    /**
     * Gets a list of edges.
     *
     * @return a list of edges
     */
    @Override
    public Collection<Edge<V, E>> edges() {
        ArrayList<Edge<V, E>> edgesList = new ArrayList<>(numEdges);

        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                if (edgeMatrix[i][j] != null) {
                    edgesList.add(edgeMatrix[i][j]);
                }
            }
        }
        return edgesList;
    }

    /**
     * Creates an edge if both keys of the vertices are greater than 0.
     *
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @return the edge created
     */
    @Override
    public Edge<V, E> edge(V vOrig, V vDest) {
        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        if ((vOrigKey < 0) || (vDestKey < 0))
            return null;

        return edgeMatrix[vOrigKey][vDestKey];
    }

    /**
     * Creates an edge only if both keys of the vertices are lower than the number of vertices.
     *
     * @param vOrigKey the key of vertex vOrig
     * @param vDestKey the key of vertex vDist
     * @return the edge key created
     */
    @Override
    public Edge<V, E> edge(int vOrigKey, int vDestKey) {
        if (vOrigKey >= numVerts && vDestKey >= numVerts)
            return null;
        return edgeMatrix[vOrigKey][vDestKey];
    }

    /**
     * Gets the degree of the outgoing edges.
     *
     * @param vert the vertex of interest
     * @return the degree of the outgoing edges
     */
    @Override
    public int outDegree(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return -1;

        int edgeCount = 0;
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[vertKey][i] != null)
                edgeCount++;
        return edgeCount;
    }

    /**
     * Gets the degree of the incoming edges.
     *
     * @param vert the vertex of interest
     * @return the degree of the incoming edges
     */
    @Override
    public int inDegree(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return -1;

        int edgeCount = 0;
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[i][vertKey] != null)
                edgeCount++;
        return edgeCount;
    }

    /**
     * Gets the list of outgoing edges of a vertex.
     *
     * @param vert the vertex of interest
     * @return the list of outgoing edges of a vertex
     */
    @Override
    public Collection<Edge<V, E>> outgoingEdges(V vert) {
        Collection<Edge<V, E>> ce = new ArrayList<>();
        int vertKey = key(vert);
        if (vertKey == -1) {
            return ce;
        }

        for (int i = 0; i < numVerts; i++) {
            if (edgeMatrix[vertKey][i] != null) {
                ce.add(edgeMatrix[vertKey][i]);
            }
        }
        return ce;
    }

    /**
     * Gets the list of incoming edges of a vertex.
     *
     * @param vert the vertex of interest
     * @return the list of incoming edges of a vertex
     */
    @Override
    public Collection<Edge<V, E>> incomingEdges(V vert) {
        Collection<Edge<V, E>> ce = new ArrayList<>();
        int vertKey = key(vert);
        if (vertKey == -1)
            return ce;

        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[i][vertKey] != null)
                ce.add(edgeMatrix[i][vertKey]);
        return ce;
    }

    /**
     * Adds a vertex to the matrix.
     *
     * @param vert the vertex to add
     * @return true if it succeeds, false if it doesn't
     */
    @Override
    public boolean addVertex(V vert) {
        int vertKey = key(vert);
        if (vertKey != -1)
            return false;

        vertices.add(vert);
        numVerts++;
        resizeMatrix();
        return true;
    }

    /**
     * Resizes the matrix when a new vertex increases the length of ArrayList
     */
    private void resizeMatrix() {
        if (edgeMatrix.length < numVerts) {
            int newSize = (int) (edgeMatrix.length * RESIZE_FACTOR);

            @SuppressWarnings("unchecked")
            Edge<V, E>[][] temp = (Edge<V, E>[][]) new Edge<?, ?>[newSize][newSize];
            for (int i = 0; i < edgeMatrix.length; i++)
                temp[i] = Arrays.copyOf(edgeMatrix[i], newSize);
            edgeMatrix = temp;
        }
    }

    /**
     * Adds an edge to the matrix.
     *
     * @param vOrig  origin vertex
     * @param vDest  destination vertex
     * @param weight the weight of the edge
     * @return true if it succeeds, false if it doesn't
     */
    @Override
    public boolean addEdge(V vOrig, V vDest, E weight) {
        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");
        if (edge(vOrig, vDest) != null)
            return false;

        if (!validVertex(vOrig))
            addVertex(vOrig);

        if (!validVertex(vDest))
            addVertex(vDest);

        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        edgeMatrix[vOrigKey][vDestKey] = new Edge<>(vOrig, vDest, weight);
        numEdges++;
        if (!isDirected) {
            edgeMatrix[vDestKey][vOrigKey] = new Edge<>(vDest, vOrig, weight);
            numEdges++;
        }
        return true;
    }

    /**
     * Removes a vertex from the matrix.
     *
     * @param vert the vertex to remove
     * @return true if it succeeds, false if it doesn't
     */
    @Override
    public boolean removeVertex(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return false;

        // first let's remove edges from the vertex
        for (int i = 0; i < numVerts; i++)
            removeEdge(vertKey, i);
        if (isDirected) {
            // first let's remove edges to the vertex
            for (int i = 0; i < numVerts; i++)
                removeEdge(i, vertKey);
        }

        // remove shifts left all vertices after the one removed
        // It is necessary to collapse the edge matrix
        for (int i = vertKey; i < numVerts - 1; i++) {
            for (int j = 0; j < numVerts; j++) {
                edgeMatrix[i][j] = edgeMatrix[i + 1][j];
            }
        }
        for (int i = vertKey; i < numVerts - 1; i++) {
            for (int j = 0; j < numVerts; j++) {
                edgeMatrix[j][i] = edgeMatrix[j][i + 1];
            }
        }
        for (int j = 0; j < numVerts; j++) {
            edgeMatrix[j][numVerts - 1] = null;
            edgeMatrix[numVerts - 1][j] = null;
        }

        vertices.remove(vert);
        numVerts--;
        return true;
    }

    /**
     * Removes an edge from the matrix.
     *
     * @param vOrigKey the key of the origin vertex
     * @param vDestKey the key of the destination vertex
     */
    private void removeEdge(int vOrigKey, int vDestKey) {
        if (edgeMatrix[vOrigKey][vDestKey] != null) {
            edgeMatrix[vOrigKey][vDestKey] = null;
            numEdges--;
        }
        if (!isDirected && (edgeMatrix[vDestKey][vOrigKey] != null)) {
            edgeMatrix[vDestKey][vOrigKey] = null;
            numEdges--;
        }
    }

    /**
     * Removes an edge from the matrix.
     *
     * @param vOrig vertex origin of the edge
     * @param vDest vertex destination of the edge
     * @return true if it succeeds, false if it doesn't
     */
    @Override
    public boolean removeEdge(V vOrig, V vDest) {
        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        if ((vOrigKey < 0) || (vDestKey < 0) || (edgeMatrix[vOrigKey][vDestKey] == null))
            return false;

        removeEdge(vOrigKey, vDestKey);
        return true;
    }

    /**
     * Clones the matrix.
     *
     * @return a clone of the matrix
     */
    @Override
    public MatrixGraph<V, E> clone() {
        MatrixGraph<V, E> g = new MatrixGraph<>(this.isDirected, this.edgeMatrix.length);

        try {
            copy(this, g);
        } catch (NullVerticesException e) {
//
        }

        return g;
    }

    /**
     * Returns a string representation of the graph.
     * Matrix only represents existence of Edge
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Vertices:\n");
        for (int i = 0; i < numVerts; i++)
            sb.append(vertices.get(i) + "\n");

        sb.append("\nMatrix:\n");

        sb.append("  ");
        for (int i = 0; i < numVerts; i++) {
            sb.append(" |  " + i + " ");
        }
        sb.append("\n");

        // aligned only when vertices < 10
        for (int i = 0; i < numVerts; i++) {
            sb.append(" " + i + " ");
            for (int j = 0; j < numVerts; j++)
                if (edgeMatrix[i][j] != null)
                    sb.append("|  X  ");
                else
                    sb.append("|     ");
            sb.append("\n");
        }

        sb.append("\nEdges:\n");

        for (int i = 0; i < numVerts; i++)
            for (int j = 0; j < numVerts; j++)
                if (edgeMatrix[i][j] != null)
                    sb.append("From " + i + " to " + j + "-> " + edgeMatrix[i][j] + "\n");

        sb.append("\n");

        return sb.toString();
    }
}

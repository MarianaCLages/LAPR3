package lapr.project.shared.graph;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.model.City;
import lapr.project.model.Facility;
import lapr.project.model.Port;
import lapr.project.shared.exceptions.NullVerticesException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class FreightNetwork {
    private final Graph<Vertex, Double> graph;

    /**
     * Constructor.
     */
    public FreightNetwork() {
        this.graph = new MatrixGraph<>(false);
    }

    /**
     * Creates the graph.
     *
     * @param n          the N closest ports
     * @param connection the database connection
     * @return true if it succeeds, false if it doesn't
     * @throws NullVerticesException
     */
    public boolean createGraph(int n, DatabaseConnection connection) throws NullVerticesException {
        boolean ret = false;
        ret = (this.capitals(connection) && this.ports(n, connection));
        return ret;
    }

    /**
     * Gets the graph.
     *
     * @return the graph
     */
    public MatrixGraph<Vertex, Double> getGraph() {
        return (MatrixGraph<Vertex, Double>) graph;
    }

    /**
     * Adds all ports to the graph.
     *
     * @param n          the N closest ports
     * @param connection the database connection
     * @return true if it succeeds, false if it doesn't
     * @throws NullVerticesException
     */
    private boolean ports(int n, DatabaseConnection connection) throws NullVerticesException {
        Map<Port, Map<Port, Double>> map = DataBaseUtils.getSeaDist(connection);
        for (Map.Entry<Port, Map<Port, Double>> entry : map.entrySet()) {
            for (Map.Entry<Port, Double> dest : entry.getValue().entrySet()) {
                this.addEdgeWithWeight(entry.getKey(), dest.getKey(), dest.getValue());
            }
        }

        linkPortToCapital();
        linkPortToNClosestPorts(n);

        return true;
    }

    /**
     * Links the port to the N closest ports.
     *
     * @param n the N closest ports
     * @return true if it succeeds, false if it doesn't
     * @throws NullVerticesException
     */
    private boolean linkPortToNClosestPorts(int n) throws NullVerticesException {

        for (Vertex v : graph.vertices()) { //V
            if (v instanceof Facility) {
                boolean[] visited = new boolean[graph.numVertices()];
                ArrayList<Facility> list = new ArrayList<>();
                dfsPortsLink(v, 1, visited, list); //log(F)

                int finalI = graph.key(v);
                Comparator<Facility> comparator = (o1, o2) -> {
                    if (VertexDistanceCalculator.distanceCalculator(o1, graph.vertex(finalI)) > VertexDistanceCalculator.distanceCalculator(o2, graph.vertex(finalI))) {
                        return 1;
                    }
                    return -1;
                };

                list.sort(comparator); //FClog(FC)

                if (n - 1 <= list.size()) {
                    for (Vertex v1 : list.subList(n - 1, list.size())) { // FC-n
                        graph.removeEdge(v, v1);
                    }
                }
            }
        }
        return true;
    }

    /**
     * DFS to link the ports.
     *
     * @param vOriginal the original vertex
     * @param vKey      the vKey
     * @param visited   boolean that says if the vertex is visited or not
     * @param objects   the list of objects (Facility)
     */
    private void dfsPortsLink(Vertex vOriginal, int vKey, boolean[] visited, ArrayList<Facility> objects) {
        if (visited[vKey]) {
            return;
        }

        if (graph.vertex(vKey) instanceof Facility && !vOriginal.getCountry().equals(graph.vertex(vKey).getCountry())) {
            objects.add((Facility) graph.vertex(vKey));
        }
        visited[vKey] = true;

        for (Vertex vAdj : graph.adjVertices(graph.vertex(vKey))) {
            dfsPortsLink(vOriginal, graph.key(vAdj), visited, objects);
        }
    }

    /**
     * Links each port to its capital.
     *
     * @return true if it succeeds, false if it doesn't
     */
    private boolean linkPortToCapital() {
        boolean ret = false;
        for (Vertex c : graph.vertices()) {
            Vertex v = null;
            if (c instanceof City) {
                for (Vertex p : graph.vertices()) {
                    if (p instanceof Facility) {
                        if (p.getCountry().equals(c.getCountry())) {
                            if (VertexDistanceCalculator.distanceCalculator(c, p) <= VertexDistanceCalculator.distanceCalculator(c, v)) {
                                v = p;
                            }
                        }
                    }
                }
            }
            if ((v != null)) {
                ret = addEdgeAndCalculateWeight(c, v);
            }
        }
        return ret;
    }

    /**
     * Adds all capitals to the graph
     *
     * @param connection the database connection
     * @return true if it succeeds, false if it doesn't
     */
    private boolean capitals(DatabaseConnection connection) {
        try {
            Map<City, LinkedList<City>> map = DataBaseUtils.getBorders(connection);
            for (Map.Entry<City, LinkedList<City>> c : map.entrySet()) {
                LinkedList<City> list = c.getValue();
                while (!list.isEmpty()) {
                    this.addEdgeAndCalculateWeight(c.getKey(), list.removeFirst());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Adds an edge to the graph and calculates its weight.
     *
     * @param v1 the vertex 1
     * @param v2 the vertex2
     * @return true if it succeeds, false if it doesn't
     */
    protected boolean addEdgeAndCalculateWeight(Vertex v1, Vertex v2) {
        try {
            double weight = VertexDistanceCalculator.distanceCalculator(v1, v2);
            graph.addEdge(v1, v2, weight);
            return true;
        } catch (NullVerticesException e) {
            return false;
        }
    }

    /**
     * Adds a weighted edge to the graph.
     *
     * @param v1     the vertex 1
     * @param v2     the vertex 2
     * @param weight the edge's weight
     * @return true if it succeeds, false if it doesn't
     */
    protected boolean addEdgeWithWeight(Vertex v1, Vertex v2, double weight) {
        try {
            graph.addEdge(v1, v2, weight);
            return true;
        } catch (NullVerticesException e) {
            return false;
        }
    }

    /**
     * Gets the graph size.
     *
     * @return the graph size
     */
    public int size() {
        return graph.numVertices();
    }

    /**
     * Returns the textual description of the graph.
     *
     * @return the graph's characteristics
     */
    public String toString() {
        return graph.toString();
    }

    /**
     * Gets the graph's number of connections (edges).
     *
     * @return the graph's number of connections (edges)
     */
    public int connectionsSize() {
        return graph.numEdges();
    }
}

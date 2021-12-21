package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.shared.exceptions.NullVerticesException;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.MatrixGraph;
import lapr.project.shared.graph.Vertex;
import lapr.project.shared.graph.VertexDistanceCalculator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class FreightNetwork {
    private final Graph<Vertex, Double> graph;

    public FreightNetwork() {
        this.graph = new MatrixGraph<>(false);
    }

    public boolean createGraph(int n, DatabaseConnection connection) throws NullVerticesException {
        boolean ret = false;
        ret = (this.capitals(connection) && this.ports(n, connection));
        return ret;
    }

    private boolean ports(int n, DatabaseConnection connection) throws NullVerticesException {
        Map<Port, Map<Port, Double>> map = DataBaseUtils.getSeaDist(connection);
        for (Map.Entry<Port, Map<Port, Double>> entry : map.entrySet()) {
            for (Map.Entry<Port, Double> dest : entry.getValue().entrySet()) {
                this.addEdgeWithWeight(entry.getKey(), dest.getKey(), dest.getValue());
            }
        }

        linkPortToCapital();
        linkPortToNClosestPorts(n);

        return false;
    }

    private boolean linkPortToNClosestPorts(int n) throws NullVerticesException {

        for (Vertex v : graph.vertices()) {
            if (v instanceof Facility) {
                boolean[] visited = new boolean[graph.numVertices()];
                ArrayList<Facility> list = new ArrayList<>();
                dfsPortsLink(v, 1, visited, list);
                int finalI = graph.key(v);
                Comparator<Facility> comparator = (o1, o2) -> {
                    if (VertexDistanceCalculator.distanceCalculator(o1, graph.vertex(finalI)) > VertexDistanceCalculator.distanceCalculator(o2, graph.vertex(finalI))) {
                        return 1;
                    }
                    return -1;
                };

                list.sort(comparator);
                if (n - 1 <= list.size()) {
                    for (Vertex v1 : list.subList(n - 1, list.size())) {
                        graph.removeEdge(v, v1);
                    }
                }
            }
        }

        return false;
    }

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

    public boolean addVertex(Vertex v) {
        try {
            graph.addVertex(v);
            return true;
        } catch (NullVerticesException e) {
            return false;
        }
    }

    protected boolean addEdgeAndCalculateWeight(Vertex v1, Vertex v2) {
        try {
            double weight = VertexDistanceCalculator.distanceCalculator(v1, v2);
            graph.addEdge(v1, v2, weight);
            return true;
        } catch (NullVerticesException e) {
            return false;
        }
    }

    protected boolean addEdgeWithWeight(Vertex v1, Vertex v2, double weight) {
        try {
            graph.addEdge(v1, v2, weight);
            return true;
        } catch (NullVerticesException e) {
            return false;
        }
    }

    public int size() {
        return graph.numVertices();
    }

    public String toString() {
        return graph.toString();
    }


    public int connectionsSize() {
        return graph.numEdges();
    }
}

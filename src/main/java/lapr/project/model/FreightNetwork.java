package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.Utils.DataBaseUtils;
import lapr.project.shared.exceptions.NullVerticesException;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.MatrixGraph;
import lapr.project.shared.graph.Vertex;
import lapr.project.shared.graph.VertexDistanceCalculator;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

public class FreightNetwork {
    private final Graph<Vertex, Double> graph;

    public FreightNetwork() {
        this.graph = new MatrixGraph<>(false);
    }

    public boolean createGraph(int n, DatabaseConnection connection) {
        boolean ret = false;
        ret = this.capitals(connection);
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

    public String toString() {
        return graph.toString();
    }


}

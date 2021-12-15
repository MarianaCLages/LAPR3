package lapr.project.model;

import lapr.project.shared.exceptions.NullVerticesException;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.MatrixGraph;
import lapr.project.shared.graph.Vertex;

public class FreightNetwork {
    private final Graph<Vertex, Double> graph;

    public FreightNetwork() {
        this.graph = new MatrixGraph<>(false);
    }

    public boolean addVertex(Vertex v) {
        try {
            graph.addVertex(v);
            return true;
        } catch (NullVerticesException e) {
            return false;
        }
    }

    public boolean addEdge(Vertex v1, Vertex v2, double weight) {
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

/*
    public Vertex getVertexByName() {
       // graph.
    }
*/

}

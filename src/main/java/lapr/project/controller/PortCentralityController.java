package lapr.project.controller;

import lapr.project.shared.graph.MatrixGraph;
import lapr.project.shared.graph.PortCentrality;
import lapr.project.shared.graph.Vertex;

public class PortCentralityController {

    /**
     * Constructor.
     */
    public PortCentralityController() {
        // Empty constructor
    }

    /**
     * Gets the N more critical ports.
     *
     * @param graph the graph
     * @param n     the ´N´ number
     * @return
     */
    public String getCriticalPorts(MatrixGraph<Vertex, Double> graph, int n) {
        return PortCentrality.getCentralityOfNPorts(graph, n);
    }
}
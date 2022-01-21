package lapr.project.controller;

import lapr.project.shared.graph.MatrixGraph;
import lapr.project.shared.graph.PortCentrality;
import lapr.project.shared.graph.Vertex;

public class PortCentralityController {

    public PortCentralityController() {
        // Empty constructor
    }

    public String getCriticalPorts(MatrixGraph<Vertex, Double> graph, int n) {
        return PortCentrality.getCentralityOfNPorts(graph, n);
    }
}
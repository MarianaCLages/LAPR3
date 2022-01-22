package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.shared.graph.FreightNetwork;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.MostEfficientCircuit;
import lapr.project.shared.graph.Vertex;

import java.util.LinkedList;

public class MostEfficientCircuitController {

    private final FreightNetwork freightNetwork;

    /**
     * Constructor.
     */
    public MostEfficientCircuitController() {
        Company company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
    }

    /**
     * Gets the most efficient circuit.
     *
     * @param designation the vertex designation
     * @return the most efficient circuit
     */
    public LinkedList<Vertex> mostEfficientCircuit(String designation) {
        Graph<Vertex, Double> graph = freightNetwork.getGraph();
        Vertex vertice = null;

        for (Vertex v : graph.vertices()) {
            if (v.getDesignation().equals(designation))
                vertice = v;
        }

        MostEfficientCircuit mostEfficientCircuit = new MostEfficientCircuit();
        LinkedList<Vertex> path = new LinkedList<>();
        LinkedList<Vertex> ls;

        ls = mostEfficientCircuit.efficientCircuit(graph, vertice, path);

        return ls;
    }
}


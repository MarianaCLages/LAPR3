package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.FreightNetwork;
import lapr.project.shared.exceptions.NoPathFoundForSpecificVertexException;
import lapr.project.shared.graph.GraphNClosestPlaces;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.Vertex;

public class GraphNClosestPlacesController {

    private final FreightNetwork freightNetwork;

    /**
     * Constructor.
     */
    public GraphNClosestPlacesController() {
        Company company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
    }

    /**
     * Gets the N closest places (cities or ports) to all other places (closeness places).
     *
     * @param n the N
     * @return the N closest places (cities or ports) to all other places (closeness places)
     * @throws NoPathFoundForSpecificVertexException
     */
    public String getTheNClosestPlaces(int n) throws NoPathFoundForSpecificVertexException {
        Graph<Vertex, Double> graph = freightNetwork.getGraph();
        return GraphNClosestPlaces.getNClosestPlaces(graph, n);
    }
}

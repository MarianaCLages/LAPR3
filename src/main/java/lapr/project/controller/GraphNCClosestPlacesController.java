package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.FreightNetwork;
import lapr.project.shared.exceptions.NoPathFoundForSpecificVertexException;
import lapr.project.shared.graph.GraphNClosestPlaces;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.Vertex;

public class GraphNCClosestPlacesController {

    private final FreightNetwork freightNetwork;


    public GraphNCClosestPlacesController() {
        Company company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
    }

    public String getTheNClosestPlaces(int n) throws NoPathFoundForSpecificVertexException {
        Graph<Vertex, Double> graph = freightNetwork.getGraph();
        return GraphNClosestPlaces.getNClosestPlaces(graph, n);
    }
}

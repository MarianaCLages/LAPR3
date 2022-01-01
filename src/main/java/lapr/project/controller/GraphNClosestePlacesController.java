package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.FreightNetwork;
import lapr.project.model.GraphNClosestPlaces;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.Vertex;

public class GraphNClosestePlacesController {

    private final GraphNClosestPlaces graphNClosestPlaces;
    private final FreightNetwork freightNetwork;


    public GraphNClosestePlacesController(){
        Company company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
        graphNClosestPlaces = new GraphNClosestPlaces();
    }

    public String graphNClosestPlacesController(int n){
        Graph<Vertex, Double> graph = freightNetwork.getGraph();

        return graphNClosestPlaces.getNClosestPlaces(graph,n);
    }
}

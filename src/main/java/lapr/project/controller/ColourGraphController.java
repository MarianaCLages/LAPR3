package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.FreightNetwork;
import lapr.project.shared.exceptions.SetColoursException;
import lapr.project.shared.graph.ColourGraph;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.Vertex;

public class ColourGraphController {

    private final FreightNetwork freightNetwork;

    public ColourGraphController() {
        Company company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
    }

    public String colourGraph() throws SetColoursException {
        Graph<Vertex, Double> graph = freightNetwork.getGraph();
        return ColourGraph.setColours(graph);
    }
}
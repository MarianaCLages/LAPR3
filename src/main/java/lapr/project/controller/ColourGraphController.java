package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.FreightNetwork;
import lapr.project.shared.graph.ColourGraph;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.Vertex;

public class ColourGraphController {

    private final ColourGraph colourGraph;
    private final FreightNetwork freightNetwork;

    public ColourGraphController() {
        Company company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
        this.colourGraph = new ColourGraph();
    }

    public void colourGraph() {
        Graph<Vertex, Double> graph = freightNetwork.getGraph();
        colourGraph.setColours(graph);
    }
}
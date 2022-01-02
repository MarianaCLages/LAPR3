package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.shared.graph.FreightNetwork;
import lapr.project.shared.exceptions.SetColoursException;
import lapr.project.shared.graph.ColourGraph;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.Vertex;

public class ColourGraphController {

    private final FreightNetwork freightNetwork;

    /**
     * Constructor.
     */
    public ColourGraphController() {
        Company company = App.getInstance().getCompany();
        this.freightNetwork = company.getFreightNetwork();
    }

    /**
     * Colours the graph.
     *
     * @return the colours of each city of the graph
     * @throws SetColoursException
     */
    public String colourGraph() throws SetColoursException {
        Graph<Vertex, Double> graph = freightNetwork.getGraph();
        return ColourGraph.setColours(graph);
    }
}
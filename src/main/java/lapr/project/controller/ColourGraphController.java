package lapr.project.controller;

import lapr.project.shared.exceptions.SetColoursException;
import lapr.project.shared.graph.ColourGraph;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.Vertex;

public class ColourGraphController {

    /**
     * Constructor.
     */
    public ColourGraphController() {
        // Empty constructor
    }

    /**
     * Colours the graph.
     *
     * @param graph the graph
     * @return the colours of each city of the graph
     * @throws SetColoursException
     */
    public String colourGraph(Graph<Vertex, Double> graph) throws SetColoursException {
        return ColourGraph.setColours(graph);
    }
}
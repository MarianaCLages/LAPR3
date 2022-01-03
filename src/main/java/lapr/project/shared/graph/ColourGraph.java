package lapr.project.shared.graph;

import lapr.project.model.City;
import lapr.project.shared.exceptions.SetColoursException;

import java.util.*;

public class ColourGraph {

    /**
     * Constructor.
     */
    private ColourGraph() {
        // Empty constructor
    }

    /**
     * Sets the colours.
     *
     * @param graph the graph
     * @return the colours assigned to each city of the graph (String)
     * @throws SetColoursException
     */
    public static String setColours(Graph<Vertex, Double> graph) throws SetColoursException {

        StringBuilder sb = new StringBuilder();

        int[] colours = new int[graph.numVertices()];
        int colour;

        for (Vertex v : graph.vertices()) {
            if (v instanceof City) {

                // All available colours
                for (int i = 0; i < colours.length; i++) {
                    colours[i] = i;
                }

                // Banish colors from the adjacent vertices (make them -1)
                for (Vertex vCheck : graph.adjVertices(v)) {
                    if (vCheck.isColour()) {
                        colours[vCheck.getColour()] = -1;
                    }
                }

                // Choose the next lowest value color
                colour = -1;
                int aux = 0;
                do {
                    if (colours[aux] != -1) {
                        colour = colours[aux];
                    }
                    aux++;
                } while (colour == -1);

                v.setColour(colour);

                sb.append("City: ").append(v.getDesignation()).append(", Colour: ").append(v.getColour()).append("\n");
            }
        }
        return sb.toString();
    }
}
package lapr.project.shared.graph;

import lapr.project.model.City;
import lapr.project.shared.exceptions.SetColoursException;

import java.util.*;

public class ColourGraph {

    private ColourGraph() {
        // Empty constructor
    }

    public static String setColours(Graph<Vertex, Double> graph) throws SetColoursException {

        StringBuilder sb = new StringBuilder();

        int[] colours = new int[graph.numVertices()];
        int colour;
        ArrayList<Vertex> vertexArrayList = graph.vertices();

        for (Vertex v : vertexArrayList) {
            if (v instanceof City) {

                // All available colours
                for (int i = 0; i < colours.length; i++) {
                    colours[i] = i;
                }

                Collection<Vertex> vAdj = graph.adjVertices(v);

                // Banish colors from the adjacent vertices (make them -1)
                for (Vertex vCheck : vAdj) {
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
            }

            if (v instanceof City) {
                sb.append("City: ").append(v.getDesignation()).append(", Colour: ").append(v.getColour()).append("\n");
            }
        }
        return sb.toString();
    }
}
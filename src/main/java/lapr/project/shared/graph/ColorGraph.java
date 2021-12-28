package lapr.project.shared.graph;

import lapr.project.model.City;

import java.util.*;

public class ColorGraph {

    public ColorGraph() {
        // Empty constructor
    }

    public void setColors(MatrixGraph<Vertex, Double> g) {
        int[] colors = new int[g.numVertices()];
        int color;
        int n;
        ArrayList<Vertex> vertexArrayList = g.vertices();

        for (Vertex v : vertexArrayList) {
            if (v instanceof City) {

                //Cores disponíveis
                for (int i = 0; i < colors.length; i++) {
                    colors[i] = i;
                }

                Collection<Vertex> vAdj = g.adjVertices(v);

                //Banir cores dos adjacentes (torná-los -1)
                for (Vertex vCheck : vAdj) {
                    if (vCheck.isColor()) {
                        colors[vCheck.getColor()] = -1;
                    }
                }

                //Escolher cor da lista com menor valor
                color = -1;
                n = 0;
                do {
                    if (colors[n] != -1) {
                        color = colors[n];
                    }
                    n++;
                } while (color == -1);

                v.setColor(color);
            }
        }
    }
}
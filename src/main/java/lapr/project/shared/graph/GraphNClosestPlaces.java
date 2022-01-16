package lapr.project.shared.graph;

import lapr.project.model.City;
import lapr.project.model.Port;
import lapr.project.shared.exceptions.NoPathFoundForSpecificVertexException;
import java.util.*;

public class GraphNClosestPlaces {

    /**
     * Constructor.
     */
    private GraphNClosestPlaces() {
        // Empty constructor
    }

    /**
     * Gets the N closest places.
     *
     * @param graph the graph
     * @param n     the N
     * @return the N closest places
     * @throws NoPathFoundForSpecificVertexException
     */
    public static String getNClosestPlaces(Graph<Vertex, Double> graph, int n) throws NoPathFoundForSpecificVertexException {

        if (n < 1)
            return "No values returned!";

        StringBuilder sb = new StringBuilder();

        ArrayList<LinkedList<Vertex>> possiblePaths = new ArrayList<>();
        ArrayList<Double> distanceList = new ArrayList<>();

        double auxDistance;
        LinkedList<Vertex> auxVex;

        try {

            for (Vertex v : graph.vertices()) {

                Algorithms.shortestPaths(graph, v, Double::compare, Double::sum, 0.0, possiblePaths, distanceList);//vai pegar num vertice e pegar em todos os paths possiveis

                for (int i = 0; i < distanceList.size(); i++) {


                    for (int j = i + 1; j < distanceList.size(); j++) {


                        if ((possiblePaths.get(i) != null && possiblePaths.get(j) != null) && (distanceList.get(i) != null && distanceList.get(j) != null)) {

                            if (distanceList.get(i) > distanceList.get(j)) {

                                auxDistance = distanceList.get(i);
                                distanceList.set(i, distanceList.get(j));
                                distanceList.set(j, auxDistance);

                                auxVex = possiblePaths.get(i);
                                possiblePaths.set(i, possiblePaths.get(j));
                                possiblePaths.set(j, auxVex);

                            }
                        }
                    }

                }

                int auxCont = 0;

                if (v instanceof City) {
                    sb.append("\nOrigin Vertex: ").append(v.getName()).append(" , which is a : City").append("\n");
                }

                if (v instanceof Port) {
                    sb.append("\nOrigin Vertex: ").append(v.getName()).append(" , which is a : Port").append("\n");
                }

                for (int i = 0; i < n && i < distanceList.size(); i++) {

                    if (possiblePaths.get(i) != null) {

                        if (v instanceof City) {

                            if (possiblePaths.get(i).getLast().getContinent().equals(v.getContinent()) && !(possiblePaths.get(i).getLast().getName().equals(v.getName()))) {

                                sb.append("\t\t\t").append(possiblePaths.get(i).getLast().getName()).append(" which is a : City , with a distance of: ").append(distanceList.get(i));

                                sb.append("\n");
                                auxCont++;

                            }

                        } else if (v instanceof Port) {

                            if (possiblePaths.get(i).getLast().getContinent().equals(v.getContinent()) && !(possiblePaths.get(i).getLast().getName().equals(v.getName()))) {

                                sb.append("             ").append(possiblePaths.get(i).getLast().getName()).append(" which is a : Port , with a distance of: ").append(distanceList.get(i));

                                sb.append("\n");
                                auxCont++;

                            }
                        }
                    }
                }

                if (auxCont == 0) {
                    sb.append("\t\t\tThere are no connections for this place!");
                }
            }
            return sb.toString();

        } catch (NullPointerException e) {
            throw new NoPathFoundForSpecificVertexException();
        }
    }
}
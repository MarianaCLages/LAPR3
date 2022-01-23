package lapr.project.shared.graph;

import lapr.project.model.City;
import lapr.project.model.Port;
import lapr.project.shared.exceptions.NullVerticesException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ShortPaths {

    /**
     * Calculates the shortest path between two locals by sea path.
     *
     * @param g          teh graph
     * @param vertexList the vertex list
     * @param vInitial   the initial vertex
     * @param vFinal     the final vertex
     * @return the shortest path between two locals by sea path
     * @throws NullVerticesException
     */
    public static List<Vertex> seaPath(Graph<Vertex, Double> g, List<Vertex> vertexList, Vertex vInitial, Vertex vFinal) throws NullVerticesException {
        Graph<Vertex, Double> g1 = g.clone(); //v2

        for (Vertex v1 : g1.vertices()) { //v2
            for (Vertex v2 : g1.adjVertices(v1)) {
                if (v1 instanceof City && v2 instanceof City) {
                    g1.removeEdge(v1, v2);
                }
            }
        }
        return calculateBestPath(g1, vertexList, vInitial, vFinal);
    }

    /**
     * Calculates the shortest path between two locals by land path.
     *
     * @param g          the graph
     * @param vertexList the vertex list
     * @param vInitial   the initial vertex
     * @param vFinal     the final vertex
     * @return the shortest path between two locals by land path
     * @throws NullVerticesException
     */
    public static List<Vertex> landPath(Graph<Vertex, Double> g, List<Vertex> vertexList, Vertex vInitial, Vertex vFinal) throws NullVerticesException {
        Graph<Vertex, Double> g1 = g.clone();

        for (Vertex v1 : g1.vertices()) {
            for (Vertex v2 : g1.adjVertices(v1)) {
                if (v1 instanceof Port && v2 instanceof Port) {
                    g1.removeEdge(v1, v2);
                }
            }
        }
        return calculateBestPath(g1, vertexList, vInitial, vFinal);
    }

    /**
     * Calculates the best path.
     *
     * @param graph      the graph
     * @param vertexList the vertex list
     * @param vInitial   the initial vertex
     * @param vFinal     the final vertex
     * @return the best path
     */
    public static List<Vertex> calculateBestPath(Graph<Vertex, Double> graph, List<Vertex> vertexList, Vertex vInitial, Vertex vFinal) {

        List<LinkedList<Vertex>> permutations = new ArrayList<>();

        if (vertexList.size() == 1) {
            LinkedList<Vertex> shortestPath1 = new LinkedList<>();
            LinkedList<Vertex> shortestPath2 = new LinkedList<>();

            Algorithms.shortestPath(graph, vInitial, vertexList.get(0), Double::compare, Double::sum, 0.0, shortestPath1);
            Algorithms.shortestPath(graph, vertexList.get(0), vFinal, Double::compare, Double::sum, 0.0, shortestPath2);

            List<Vertex> returnList = new LinkedList<>();

            returnList.addAll(shortestPath1);
            returnList.addAll(shortestPath2.subList(1, shortestPath2.size()));

            return returnList;

        } else {
            permutations.add((new LinkedList<>(vertexList)));

            permutation(vertexList, 0, permutations::add);//n!


            List<Vertex> pathTotal = new ArrayList<>();
            List<Vertex> saveTotalPath = new ArrayList<>();

            double temp = 0;
            double min = Double.POSITIVE_INFINITY;

            for (LinkedList<Vertex> permutation : permutations) {
                pathTotal.clear();
                pathTotal.add(vInitial);
                permutation.add(0, vInitial);

                for (int i = 1; i < ((List<Vertex>) permutation).size(); i++) {
                    LinkedList<Vertex> shortestPath = new LinkedList<>();
                    Algorithms.shortestPath(graph, ((List<Vertex>) permutation).get(i - 1), ((List<Vertex>) permutation).get(i), Double::compare, Double::sum, 0.0, shortestPath);
                    shortestPath.removeFirst();
                    pathTotal.addAll(shortestPath);
                }

                for (int i = 1; i < pathTotal.size(); i++) {
                    if (graph.edge(pathTotal.get(i - 1), pathTotal.get(i)) == null) {
                        temp = 0;
                        break;
                    }
                    temp += graph.edge(pathTotal.get(i - 1), pathTotal.get(i)).getWeight();
                }

                pathTotal.add(vFinal);

                if (temp < min) {
                    saveTotalPath.clear();
                    saveTotalPath.addAll(pathTotal);
                }
            }
            return saveTotalPath;
        }
    }

    /**
     * Does permutations on the list.
     *
     * @param original the vertex list
     * @param start    the starting point
     * @param consumer the consumer
     */
    private static void permutation(List<Vertex> original, int start, Consumer<LinkedList<Vertex>> consumer) {
        Objects.requireNonNull(original);
        if (start == original.size()) {
            return;
        }
        if (start + 1 == original.size()) {
            return;
        }
        for (int i = start; i < original.size(); i++) {
            LinkedList<Vertex> temp = new LinkedList<>(original);
            Vertex tempVal = temp.get(start);
            temp.set(start, temp.get(start + 1));
            temp.set(start + 1, tempVal);
            consumer.accept(temp);
            permutation(temp, start + 1, consumer);
        }
    }
}

package lapr.project.shared.graph;

import lapr.project.model.City;
import lapr.project.model.Continent;
import lapr.project.model.Country;
import lapr.project.model.Port;
import lapr.project.shared.exceptions.NullVerticesException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ShortPaths {
    public static void main(String[] args) throws NullVerticesException {

        //   FreightNetwork f = new FreightNetwork();
        //    f.createGraph(1, App.getInstance().getDatabaseConnection());
        ArrayList<Vertex> list = new ArrayList<>();

        Graph<Vertex, Double> graph = new MatrixGraph<>(false);
        Vertex vertex1 = new City("Madrid", 40.4, -3.683333, new Country("Spain", "ES".toCharArray(), "ESP".toCharArray(), 46.53, Continent.EUROPE));
        Vertex vertex2 = new City("Lisbon", 38.71666667, -9.133333, new Country("Portugal", "PT".toCharArray(), "PTR".toCharArray(), 10.31, Continent.EUROPE));
        Vertex vertex3 = new City("Paris", 48.86666667, 2.333333, new Country("France", "FR".toCharArray(), "FRA".toCharArray(), 66.99, Continent.EUROPE));
        Vertex vertex4 = new City("Monaco", 40.4, -3.683333, new Country("Monaco", "MC".toCharArray(), "MCO".toCharArray(), 46.53, Continent.EUROPE));

        graph.addEdge(vertex1, vertex2, VertexDistanceCalculator.distanceCalculator(vertex1, vertex2));
        graph.addEdge(vertex1, vertex3, VertexDistanceCalculator.distanceCalculator(vertex1, vertex3));
        graph.addEdge(vertex4, vertex3, VertexDistanceCalculator.distanceCalculator(vertex4, vertex3));
        graph.addEdge(vertex4, vertex2, VertexDistanceCalculator.distanceCalculator(vertex4, vertex2));


        list.add(vertex1);
        list.add(vertex2);
        seaPath(graph, list, vertex3, vertex4);

    }

    public static List<Vertex> seaPath(Graph<Vertex, Double> g, List<Vertex> vertexList, Vertex vInitial, Vertex vFinal) throws NullVerticesException {
        Graph<Vertex, Double> g1 = g.clone();

        for (Vertex v1 : g1.vertices()) {
            for (Vertex v2 : g1.adjVertices(v1)) {
                if (v1 instanceof City && v2 instanceof City) {
                    g1.removeEdge(v1, v2);
                }
            }
        }

        return calculateBestPath(g, vertexList, vInitial, vFinal);

    }


    public static List<Vertex> landPath(Graph<Vertex, Double> g, List<Vertex> vertexList, Vertex vInitial, Vertex vFinal) throws NullVerticesException {
        Graph<Vertex, Double> g1 = g.clone();

        for (Vertex v1 : g1.vertices()) {
            for (Vertex v2 : g1.adjVertices(v1)) {
                if (v1 instanceof Port && v2 instanceof Port) {
                    g1.removeEdge(v1, v2);
                }
            }
        }

        return calculateBestPath(g, vertexList, vInitial, vFinal);

    }


    public static List<Vertex> calculateBestPath(Graph<Vertex, Double> graph, List<Vertex> vertexList, Vertex vInitial, Vertex vFinal) {

        List<LinkedList<Vertex>> permutations = new ArrayList<>();

        if (vertexList.size() == 1) {
            LinkedList<Vertex> shortestPath1 = new LinkedList<>();
            LinkedList<Vertex> shortestPath2 = new LinkedList<>();

            Algorithms.shortestPath(graph, vInitial, vertexList.get(0), Double::compare, Double::sum, 0.0, shortestPath1);
            Algorithms.shortestPath(graph, vertexList.get(0), vFinal, Double::compare, Double::sum, 0.0, shortestPath2);

            List<Vertex> returnList = new LinkedList<>();

            returnList.addAll(shortestPath1);
            returnList.addAll(shortestPath2);

            return returnList;

        } else {
            permutations.add((new LinkedList<>(vertexList)));

            permutation(vertexList, 0, permutations::add);


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

                (pathTotal).add(vFinal);

                if (temp < min) {
                    saveTotalPath.clear();
                    saveTotalPath.addAll(pathTotal);
                }
            }
            return saveTotalPath;
        }

    }

    private static void permutation(List<Vertex> original, int start, Consumer<LinkedList<Vertex>> consumer) {
        Objects.requireNonNull(original);
        if (start == original.size()) {
            return;
        }
        if (start + 1 == original.size()) {
            return;
        }
        LinkedList<Vertex> temp = new LinkedList<>(original);
        Vertex tempVal = temp.get(start);
        temp.set(start, temp.get(start + 1));
        temp.set(start + 1, tempVal);
        consumer.accept(temp);
        permutation(temp, start + 1, consumer);

    }
}

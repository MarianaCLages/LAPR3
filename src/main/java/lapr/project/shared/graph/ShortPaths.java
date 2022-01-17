package lapr.project.shared.graph;

import lapr.project.model.City;
import lapr.project.model.Port;
import lapr.project.shared.exceptions.NullVerticesException;

import java.util.*;

public class ShortPaths {

    public static Set<Vertex> seaPath(Graph<Vertex, Double> g, List<Vertex> vertexList, Vertex vInitial, Vertex vFinal) throws NullVerticesException {
        Graph<Vertex, Double> g1 = g.clone();

        for (Vertex v1 : g1.vertices()) {
            for (Vertex v2 : g1.adjVertices(v1)) {
                if (v1 instanceof City && v2 instanceof City) {
                    g1.removeEdge(v1, v2);
                }
            }
        }

        return getPath(g1, vertexList, vInitial, vFinal);

    }

    public static Set<Vertex> landPath(Graph<Vertex, Double> g, List<Vertex> vertexList, Vertex vInitial, Vertex vFinal) throws NullVerticesException {
        Graph<Vertex, Double> g1 = g.clone();

        for (Vertex v1 : g1.vertices()) {
            for (Vertex v2 : g1.adjVertices(v1)) {
                if (v1 instanceof Port && v2 instanceof Port) {
                    g1.removeEdge(v1, v2);
                }
            }
        }

        return getPath(g1, vertexList, vInitial, vFinal);

    }

    public static Set<Vertex> getPath(Graph<Vertex, Double> g, List<Vertex> vertexList, Vertex vInitial, Vertex vFinal) {
        List<LinkedList<Vertex>> listsOfPaths = new ArrayList();

        LinkedList<Vertex> vertexListOut = new LinkedList<>();
        Algorithms.shortestPath(g, vInitial, vertexList.get(0), Double::compare, Double::sum, 0.0, vertexListOut);
        listsOfPaths.add(vertexListOut);

        for (int i = 1; i < vertexList.size(); i++) {
            vertexListOut = new LinkedList<>();

            Algorithms.shortestPath(g, vertexList.get(i - 1), vertexList.get(i - 1), Double::compare, Double::sum, 0.0, vertexListOut);

            listsOfPaths.add(vertexListOut);
        }

        vertexListOut = new LinkedList<>();
        Algorithms.shortestPath(g, vertexList.get(vertexList.size() - 1), vFinal, Double::compare, Double::sum, 0.0, vertexListOut);
        listsOfPaths.add(vertexListOut);

        return linkAllLists(listsOfPaths);

    }

    private static Set<Vertex> linkAllLists(List<LinkedList<Vertex>> listsOfPaths) {
        Set<Vertex> result = new LinkedHashSet<>();
        for (LinkedList<Vertex> list : listsOfPaths) {
            result.addAll(list);
        }


        return result;
    }
}
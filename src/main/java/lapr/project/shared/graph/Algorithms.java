package lapr.project.shared.graph;

import java.util.LinkedList;

public class Algorithms {

    /**
     * Performs breadth-first search of a Graph starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex((vert))) {
            return null;
        }

        LinkedList<V> gbfs = new LinkedList<>();
        LinkedList<V> aux = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];

        gbfs.add(vert);
        aux.add(vert);
        int vKey = g.key(vert);
        visited[vKey] = true;

        while (!aux.isEmpty()) {
            vert = aux.remove();
            for (V vadjs : g.adjVertices(vert)) {
                vKey = g.key(vadjs);
                if (!visited[vKey]) {
                    gbfs.add(vadjs);
                    aux.add(vadjs);
                    visited[vKey] = true;
                }
            }
        }

        return gbfs;
    }
}

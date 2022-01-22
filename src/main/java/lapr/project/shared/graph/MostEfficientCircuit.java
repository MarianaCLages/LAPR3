package lapr.project.shared.graph;

import java.util.LinkedList;

public class MostEfficientCircuit {

    /**
     * Constructor.
     */
    public MostEfficientCircuit() {
        // Empty Constructor
    }

    /**
     * Calculates the most efficient circuit that starts from a source location and visits the greatest number of other locations once, returning to the starting location and with the shortest total distance.
     *
     * @param graph     the graph
     * @param location  the location (Vertex)
     * @param locations the list of locations
     * @return the most efficient circuit that starts from a source location and visits the greatest number of other locations once, returning to the starting location and with the shortest total distance
     */
    public LinkedList<Vertex> efficientCircuit(Graph<Vertex, Double> graph, Vertex location, LinkedList<Vertex> locations) {
        locations.add(location);

        if (!graph.validVertex(location)) {
            return locations;
        }

        if (graph.inDegree(location) <= 1) {
            return locations;
        }

        Vertex proximoLocation;
        double distanciaMaisCurta;

        if (!(locations.contains(graph.adjVertices(location).iterator().next()))) {
            proximoLocation = graph.adjVertices(location).iterator().next();
            distanciaMaisCurta = graph.edge(location, graph.adjVertices(location).iterator().next()).getWeight();
        } else {
            proximoLocation = location;
            distanciaMaisCurta = Double.MAX_VALUE;
        }

        for (Vertex p : graph.adjVertices(location)) {
            if (graph.edge(location, p).getWeight() < distanciaMaisCurta) {
                if (!(locations.contains(p))) {
                    distanciaMaisCurta = graph.edge(location, p).getWeight();
                    proximoLocation = p;
                }
            }
        }

        if (location != proximoLocation) {
            return efficientCircuit(graph, proximoLocation, locations);
        }

        LinkedList<Vertex> paisesUsados = new LinkedList<>();
        return backTrackCircuit(graph, location, locations.get(0), locations, paisesUsados);
    }

    /**
     * Backtrack algorithm to calculate the most efficient circuit.
     * @param graph the graph
     * @param location the location (Vertex)
     * @param locationOrigem the start location
     * @param locations the list of locations
     * @param locationUsed the list of locations used
     * @return a list of vertices (efficient circuit)
     */
    private LinkedList<Vertex> backTrackCircuit(Graph<Vertex, Double> graph, Vertex location, Vertex locationOrigem, LinkedList<Vertex> locations, LinkedList<Vertex> locationUsed) {
        Vertex proximoLocation;
        double distanciaMaisCurta;

        if (!(locations.contains(graph.adjVertices(location).iterator().next())) && !(locationUsed.contains(graph.adjVertices(location).iterator().next()))) {
            proximoLocation = graph.adjVertices(location).iterator().next();
            distanciaMaisCurta = graph.edge(location, graph.adjVertices(location).iterator().next()).getWeight();
        } else {
            proximoLocation = location;
            distanciaMaisCurta = Double.MAX_VALUE;
        }

        for (Vertex p : graph.adjVertices(location)) {
            if (graph.edge(location, p).getWeight() < distanciaMaisCurta) {
                if (!(locations.contains(p)) && !(locationUsed.contains(p))) {
                    distanciaMaisCurta = graph.edge(location, p).getWeight();
                    proximoLocation = p;
                } else if (p.equals(locationOrigem)) {
                    locations.add(locationOrigem);
                    return locations;
                }
            }
        }

        if (location != proximoLocation) {
            locations.add(proximoLocation);
            return backTrackCircuit(graph, proximoLocation, locationOrigem, locations, locationUsed);
        } else {
            locations.remove(location);
            locationUsed.add(location);
            return backTrackCircuit(graph, locations.get(locations.size() - 1), locationOrigem, locations, locationUsed);
        }
    }
}
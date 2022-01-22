package lapr.project.shared.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MostEfficientCircuit {


    public MostEfficientCircuit() {
        //Empty Constructor
    }




    public LinkedList<Vertex> efficientCircuit(Graph<Vertex, Double> graph,Vertex location, LinkedList<Vertex> locations) {

        locations.add(location);

        if (!graph.validVertex(location)) {
            return locations;
        }

        if (graph.inDegree(location) <= 1 ) {
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
            if (graph.edge(location,p).getWeight() < distanciaMaisCurta) {
                if (!(locations.contains(p))) {
                    distanciaMaisCurta = graph.edge(location, p).getWeight();
                    proximoLocation = p;
                }
            }
        }
        if (location != proximoLocation) {
            return efficientCircuit(graph,proximoLocation, locations);
        }

        LinkedList<Vertex> paisesUsados = new LinkedList<>();
        return backTrackCircuit(graph,location, locations.get(0), locations, paisesUsados);
    }


    private LinkedList<Vertex> backTrackCircuit(Graph<Vertex, Double> graph,Vertex location, Vertex locationOrigem, LinkedList<Vertex> locations, LinkedList<Vertex> locationUsed) {

        Vertex proximoLocation;
        double distanciaMaisCurta;

        if (!(locations.contains(graph.adjVertices(location).iterator().next())) && !(locationUsed.contains(graph.adjVertices(location).iterator().next()))) {
            proximoLocation = graph.adjVertices(location).iterator().next();
            distanciaMaisCurta = graph.edge(location, graph.adjVertices(location).iterator().next()).getWeight();
        }
        else {
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
            return backTrackCircuit(graph,proximoLocation, locationOrigem, locations, locationUsed);
        } else {
            locations.remove(location);
            locationUsed.add(location);
            return backTrackCircuit(graph,locations.get(locations.size() - 1), locationOrigem, locations, locationUsed);
        }
    }

}

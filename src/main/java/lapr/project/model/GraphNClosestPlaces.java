package lapr.project.model;

import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.Vertex;
import lapr.project.shared.graph.VertexDistanceCalculator;

import java.util.*;

public class GraphNClosestPlaces {

    public static String getTheNPlacesClosest(Graph<Vertex, Double> graph, int n) {

        if (n < 1) {
            return "No values returned!";
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<Double> distanceTemp = new ArrayList<>();

        Map<Vertex, ArrayList<Vertex>> nClosestPlaces = new HashMap<>();


            for (Vertex v : graph.vertices()) {

                Map<Vertex, Double> vertexW = new HashMap<>();

                for (Vertex vAdj : graph.vertices()) {

                    if (v != vAdj ) {
                        Double auxDistance = VertexDistanceCalculator.distanceCalculator(v, vAdj);
                        vertexW.put(vAdj, auxDistance);
                        distanceTemp.add(auxDistance);
                    }

                }

                Comparator<Double> comparator = (o1, o2) -> {
                    if (o1 > o2) {
                        return 1;
                    } else if (o1.equals(o2)) {
                        return 0;
                    } else {
                        return 0;
                    }
                };

                distanceTemp.sort(comparator);
                ArrayList<Vertex> nClosestPlacesToInitialVertex = new ArrayList<>();
                int i = 0;
                int y = 0;

                while( i != n){


                    for(Vertex vt : vertexW.keySet()){


                        if( Objects.equals(vertexW.get(vt), distanceTemp.get(y))){
                            nClosestPlacesToInitialVertex.add(vt);
                            break;
                        }
                        y++;
                    }
                    i++;
                }

                nClosestPlaces.put(v, nClosestPlacesToInitialVertex);

            }

        int i = 0;
        sb.append("The " + n + " closest places per continent are :\n");

        for (Continent c : Continent.values()) {

            sb.append(c + " :\n");

            for (Vertex vt : nClosestPlaces.keySet()) {
                if(i == 0 && vt.getContinent().equals(c.getName())){
                sb.append(vt + "n closest city/port:\n");
                i++;}
                if (vt.getContinent().equals(c.getName())) {
                    sb.append(nClosestPlaces.get(vt));

                }

            }
            i = 0;
            sb.append("\n");
        }

        return sb.toString();
    }


}

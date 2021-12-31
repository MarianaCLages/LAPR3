package lapr.project.model;

import lapr.project.shared.graph.Algorithms;
import lapr.project.shared.graph.Graph;
import lapr.project.shared.graph.Vertex;
import lapr.project.shared.graph.VertexDistanceCalculator;

import java.util.*;

public class GraphNClosestPlaces {

    public GraphNClosestPlaces() {
        //EMPTY
    }

    /*public static String getTheNPlacesClosest(Graph<Vertex, Double> graph, int n) {

        if (n < 1) {
            return "No values returned!";
        }

        StringBuilder sb = new StringBuilder();


        Map<Vertex, ArrayList<Vertex>> nClosestPlaces = new HashMap<>();


        for (Vertex v : graph.vertices()) {

            Map<Vertex, Double> vertexW = new HashMap<>();
            ArrayList<Double> distanceTemp = new ArrayList<>();

            for (Vertex vAdj : graph.vertices()) {

                if (!(v.equals(vAdj)) && !vertexW.containsKey(vAdj)) {
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

            while (i != n) {

                for (Vertex vt : vertexW.keySet()) {

                    for (int z = 0; z < vertexW.size(); z++) {

                        if (vertexW.get(vt).equals(distanceTemp.get(z))) {
                            nClosestPlacesToInitialVertex.add(vt);
                            break;
                        }
                    }
                }
                i++;
            }

            if (!nClosestPlaces.containsKey(v)) {
                nClosestPlaces.put(v, nClosestPlacesToInitialVertex);
            }


        }

        boolean flag = false;
        sb.append("The " + n + " closest places per continent are :\n");

        for (Continent c : Continent.values()) {

            sb.append(c + " :\n");

            for (Vertex vt : nClosestPlaces.keySet()) {

                System.out.println(vt);

                if (!flag && vt.getContinent().equals(c.getName())) {
                    sb.append(vt + "n closest city/port:\n");
                    flag = true;
                }
                if (vt.getContinent().equals(c.getName())) {
                    sb.append(nClosestPlaces.get(vt));
                }
            }
            flag = false;
            sb.append("\n");
        }

        return sb.toString();
    } */


    public static String getNClosestPlaces(Graph<Vertex, Double> graph, int n) {

        if (n < 1)
            return "No values returned!";

        StringBuilder sb = new StringBuilder();

        ArrayList<LinkedList<Vertex>> possiblePaths = new ArrayList<>();
        ArrayList<Double> distanceList = new ArrayList<>();

        double auxDistance;
        LinkedList<Vertex> auxVex;

        for (Vertex v : graph.vertices()) {

            Algorithms.shortestPaths(graph, v, Double::compare, Double::sum, 0.0, possiblePaths, distanceList);

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


            // quickSort(distanceList, possiblePaths);

            if (v instanceof City) {
                sb.append("Origin Vertex: ").append(v.getName()).append(" , which is a : City").append("\n");
            }

            if (v instanceof Port) {
                sb.append("Origin Vertex: ").append(v.getName()).append(" , which is a : Port").append("\n");
            }

            for (int i = 0; i < n && i < distanceList.size(); i++) {

                if (possiblePaths.get(i) != null) {

                    if (v instanceof City) {

                        if (possiblePaths.get(i).getLast().getContinent().equals(v.getContinent()) && !(possiblePaths.get(i).getLast().getName().equals(v.getName()))) {

                            sb.append("             ").append(possiblePaths.get(i).getLast().getName()).append(" which is a : City , with a distance of: ").append(distanceList.get(i));

                            sb.append("\n");

                        }

                    } else if (v instanceof Port) {

                        if (possiblePaths.get(i).getLast().getContinent().equals(v.getContinent()) && !(possiblePaths.get(i).getLast().getName().equals(v.getName()))) {

                            sb.append("             ").append(possiblePaths.get(i).getLast().getName()).append(" which is a : Port , with a distance of: ").append(distanceList.get(i));

                            sb.append("\n");

                        }

                    }


                }

            }

        }

        return sb.toString();

    }

    public   void quickSort(ArrayList<Double> array, ArrayList<LinkedList<Vertex>> paths) {
        quickSort(array, 0, array.size() - 1, paths);
    }

    private  void quickSort(ArrayList<Double> array, int lowIndex, int highIndex, ArrayList<LinkedList<Vertex>> paths) {


        if (lowIndex >= highIndex) {
            return;
        }
        double pivot = array.get(highIndex);

        int leftPointer = partition(array, lowIndex, highIndex, pivot, paths);

        quickSort(array, lowIndex, leftPointer - 1, paths);
        quickSort(array, leftPointer + 1, highIndex, paths);

    }

    public void swap(ArrayList<Double> array, int index1, int index2, ArrayList<LinkedList<Vertex>> paths) {

        double temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);

        LinkedList<Vertex> auxVex = paths.get(index1);
        paths.set(index1, paths.get(index2));
        paths.set(index2, auxVex);

    }

    public int partition(ArrayList<Double> array, int lowIndex, int highIndex, double pivot, ArrayList<LinkedList<Vertex>> paths) {

        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while (leftPointer < rightPointer) {

            while (array.get(leftPointer) <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }

            while (array.get(rightPointer) >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }

            swap(array, leftPointer, rightPointer, paths);


        }

        swap(array, leftPointer, highIndex, paths);
        return leftPointer;


    }


}



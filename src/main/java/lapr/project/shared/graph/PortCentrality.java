package lapr.project.shared.graph;

import java.util.*;

public class PortCentrality {

    static public String getCentralityOfNPorts(MatrixGraph<Vertex, Double> graph, int n) {
        ArrayList<LinkedList<Vertex>> pathList = new ArrayList<>();
        ArrayList<Double> distanceList = new ArrayList<>();

        LinkedHashMap<Vertex, Integer> map = new LinkedHashMap<>();

        // Percorrer todos os vértices do grafo e calcular todos os caminhos mais curtos de cada um
        for (Vertex vertex1 : graph.vertices()) {
            Algorithms.shortestPaths(graph, vertex1, Double::compare, Double::sum, 0.0, pathList, distanceList);

            // Percorrer todos os caminhos
            for (LinkedList<Vertex> vertexLL : pathList) {
                if (vertexLL != null) {
                    // Percorrer todos os vértices de cada caminho
                    for (Vertex vertex2 : vertexLL) {
                        // Se o vértice for um porto, colocar num map como key e incrementar o valor que representa o número de vezes em que ele aparece
                        if (map.containsKey(vertex2)) {
                            map.put(vertex2, map.get(vertex2) + 1);
                        } else {
                            map.put(vertex2, 1);
                        }
                    }
                }
            }
        }

        // Ordenar o mapa
        map = sortMap(map);

        int aux = 0;
        StringBuilder sb = new StringBuilder();

        // Dar print aos N maiores
        for (Vertex vertex : map.keySet()) {
            if (aux == n) {
                break;
            }

            sb.append("Port: ").append(vertex.getName()).append(", Centrality: ").append(map.get(vertex)).append("\n");
            aux++;
        }

        return sb.toString();
    }

    public static LinkedHashMap<Vertex, Integer> sortMap(LinkedHashMap<Vertex, Integer> map) {
        List<Map.Entry<Vertex, Integer>> capitalList = new LinkedList<>(map.entrySet());

        // Usa List.sort para colocar os valores dos elementos da lista por ordem decrescente
        capitalList.sort((l1, l2) -> l2.getValue().compareTo(l1.getValue()));

        LinkedHashMap<Vertex, Integer> result = new LinkedHashMap<>();

        for (Map.Entry<Vertex, Integer> entry : capitalList) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
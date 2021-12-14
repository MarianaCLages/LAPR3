package lapr.project.shared.graph;

import java.util.*;

public class ColorGraph {

    static class Node {
        int color = 1;
        Set<Integer> edges = new HashSet<>();
    }

    static class GFG {
        int canPaint(ArrayList<Node> nodes, int n, int m) {

            // Create a visited array of nodes, initialized to zero
            ArrayList<Integer> visited = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                visited.add(0);
            }

            // maxColors used until now are 1 as all nodes are painted color 1
            int maxColors = 1;

            // Do a full BFS traversal from all unvisited starting points
            for (int sp = 1; sp <= n; sp++) {
                if (visited.get(sp) > 0) {
                    continue;
                }

                // If the starting point is unvisited, mark it visited and push it in queue
                visited.set(sp, 1);
                Queue<Integer> queue = new LinkedList<>();
                queue.add(sp);

                // BFS Travel starts here
                while (queue.size() != 0) {
                    int top = queue.peek();
                    queue.remove();

                    // Checking all adjacent nodes to "top" edge in our queue
                    for (int it : nodes.get(top).edges) {
                        // If the color of the adjacent node is same, increase it by 1
                        if (nodes.get(top).color == nodes.get(it).color) {
                            nodes.get(it).color += 1;
                        }

                        // If number of colors used shoots m, return 0
                        maxColors = Math.max(maxColors, Math.max(nodes.get(top).color, nodes.get(it).color));
                        if (maxColors > m)
                            return 0;

                        // If the adjacent node is not visited, mark it visited and push it in queue
                        if (visited.get(it) == 0) {
                            visited.set(it, 1);
                            queue.remove(it);
                        }
                    }
                }
            }
            return 1;
        }
    }
}
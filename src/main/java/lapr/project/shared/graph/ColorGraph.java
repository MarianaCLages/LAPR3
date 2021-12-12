package lapr.project.shared.graph;

import java.util.*;

public class ColorGraph {

    static class Node {
        int color = 1;
        Set<Integer> edges = new HashSet<>();
    }

    static class GFG {
        static int canPaint(ArrayList<Node> nodes, int n, int m) {

            ArrayList<Integer> visited = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                visited.add(0);
            }

            int maxColors = 1;

            for (int sv = 1; sv <= n; sv++) {
                if (visited.get(sv) > 0) {
                    continue;
                }

                visited.set(sv, 1);
                Queue<Integer> queue = new LinkedList<>();
                queue.add(sv);

                while (queue.size() != 0) {
                    int top = queue.peek();
                    queue.remove();

                    for (int it : nodes.get(top).edges) {
                        if (nodes.get(top).color == nodes.get(it).color) {
                            nodes.get(it).color += 1;
                        }

                        maxColors = Math.max(maxColors, Math.max(nodes.get(top).color, nodes.get(it).color));
                        if (maxColors > m)
                            return 0;

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
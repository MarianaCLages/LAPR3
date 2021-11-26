package lapr.project.shared.tree;

import lapr.project.model.Port;

import java.util.Comparator;

public class TwoDTree {

    private final Comparator<Node<Port>> cmpX = new Comparator<Node<Port>>() {
        @Override
        public int compare(Node<Port> p1, Node<Port> p2) {
            return Double.compare(p1.getX(), p2.getX());
        }
    };
    private final Comparator<Node<Port>> cmpY = new Comparator<Node<Port>>() {
        @Override
        public int compare(Node<Port> p1, Node<Port> p2) {
            return Double.compare(p1.getY(), p2.getY());
        }
    };

    private Node<Port> root;

    private static class Node<Port> {
        lapr.project.model.Port port;

        public Node(lapr.project.model.Port port) {
            this.port = port;
        }

        public double getX() {
            return port.getLocation().getLongitude();
        }

        public double getY() {
            return port.getLocation().getLatitude();
        }
    }
}

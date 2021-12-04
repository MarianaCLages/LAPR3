package lapr.project.shared.tree;

import lapr.project.model.Facility;
import lapr.project.model.Port;
import lapr.project.model.Position;
import lapr.project.shared.QuickSelect;

import java.awt.geom.Point2D;
import java.util.Comparator;

public class TwoDTree {
    private final Comparator<Facility> cmpX = Comparator.comparingDouble(o -> o.getLocation().getLongitude());

    private final Comparator<Facility> cmpY = Comparator.comparingDouble(o -> o.getLocation().getLatitude());

    double closesDist;
    Node closestNode;

    Node root = null;


    public TwoDTree() {
        root = null;
    }

    private static double dist(Node n0, Port port) {
        double total;

        total = Math.sqrt(distSquared(n0, port));

        return total;
    }

    private static double distSquared(Node n0, Port port) {

        double total = Math.abs(Math.pow(n0.getX() - port.getLocation().getLatitude(), 2) + Math.pow(n0.getY() - port.getLocation().getLongitude(), 2));

        return total;
    }

    public void insert(Port[] port) {

        if (port == null) return;
        if (port.length == 0) return;


        root = insert(port, 0, port.length, true);
    }

    public Node insert(Port[] point, int beg, int end, boolean divX) {
        if (end <= beg) {
            return null;
        }
        int n = beg + (end - beg) / 2;

        Node nodeToInsert;
        if (divX) {
            nodeToInsert = new Node<Port>(QuickSelect.select(point, n, cmpX), null, null);
        } else {
            nodeToInsert = new Node<Port>(QuickSelect.select(point, n, cmpY), null, null);
        }
        nodeToInsert.setLeft(insert(point, beg, n, !divX));
        nodeToInsert.setRight(insert(point, n + 1, end, !divX));
        return nodeToInsert;
    }


    public Port nearestNeighborPort(Position target) {

        closesDist = Double.MAX_VALUE;
        closestNode = null;
        return nearestNeighborNode(root, target, true).getElement();
    }


    private Node nearestNeighborNode(Node root, Position target, boolean divX) {

        if (root == null) {
            return closestNode;
        }


        double d = Point2D.distanceSq(root.getX(), root.getY(), target.getLongitude(), target.getLatitude());

        if (closesDist > d) {
            closesDist = d;
            closestNode = root;
        }

        double delta = divX ? target.getLongitude() - root.getX() : target.getLatitude() - root.getY();
        double delta2 = delta * delta;

        Node node1 = delta < 0 ? root.left : root.right;
        Node node2 = delta2 < 0 ? root.right : root.left;


        nearestNeighborNode(node1, target,!divX);

        if (delta2 < closesDist) {
            closestNode = nearestNeighborNode(node2, target, !divX);
        }

        return closestNode;
    }

    protected static class Node<Port> {

        private lapr.project.model.Port port;


        private Node left;
        private Node right;

        public Node(lapr.project.model.Port port, Node leftChild, Node rightChild) {
            this.port = port;
            left = leftChild;
            right = rightChild;

        }

        public lapr.project.model.Port getElement() {
            return port;
        }

        // Update methods

        public void setElement(lapr.project.model.Port e) {
            port = e;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node leftChild) {
            left = leftChild;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node rightChild) {
            right = rightChild;
        }

        public double getX() {
            return port.getLocation().getLongitude();
        }

        public double getY() {
            return port.getLocation().getLatitude();
        }
    }
}

/*

import lapr.project.model.Facility;
import lapr.project.shared.MedianElement;

import java.util.*;

public class KdTree {
    private int dimesions;
    private Node root = null;
    private Node best = null;
    private double bestDistance = 0;
    private int visited = 0;

    public KdTree(int dimensions, List<Node> nodes) {
        this.dimesions = dimensions;
        this.root = makeTree(nodes, 0, nodes.size(), 0);
    }

    public Node findNearest(Node target) {
        if (this.root == null)
            throw new IllegalStateException("Tree is empty!");
        this.best = null;
        this.visited = 0;
        this.bestDistance = 0;
        nearest(this.root, target, 0);
        return this.best;
    }

    public int visited() {
        return this.visited;
    }

    public double distance() {
        return Math.sqrt(this.bestDistance);
    }

    private void nearest(Node root, Node target, int index) {
        if (root == null)
            return;
        ++this.visited;
        double d = root.distance(target);
        if (this.best == null || d < this.bestDistance) {
            this.bestDistance = d;
            this.best = root;
        }
        if (this.bestDistance == 0)
            return;
        double dx = root.get(index) - target.get(index);
        index = (index + 1) % this.dimesions;
        nearest(dx > 0 ? root.left_ : root.right_, target, index);
        if (dx * dx >= this.bestDistance)
            return;
        nearest(dx > 0 ? root.right_ : root.left_, target, index);
    }

    private Node makeTree(List<Node> nodes, int beg, int end, int index) {
        if (end <= beg) {
            return null;
        }
        MedianElement medianElement = new MedianElement(nodes.toArray(new Facility[0]),);
        int n = beg + (end - beg)/2;
        Node node = QuickSelect.select(nodes, beg, end - 1, n, new NodeComparator(index));
        index = (index + 1) % this.dimesions;
        node.left_ = makeTree(nodes, beg, n, index);
        node.right_ = makeTree(nodes, n + 1, end, index);
        return node;
    }

    private static class NodeComparator implements Comparator<Node> {
        private int index_;

        private NodeComparator(int index) {
            index_ = index;
        }
        public int compare(Node n1, Node n2) {
            return Double.compare(n1.get(index_), n2.get(index_));
        }
    }

    public static class Node {
        private double[] coords;
        private Node left_ = null;
        private Node right_ = null;

        public Node(double[] coords) {
           this.coords = coords;
        }
        public Node(double x, double y) {
            this(new double[]{x, y});
        }
        public Node(double x, double y, double z) {
            this(new double[]{x, y, z});
        }
        double get(int index) {
            return this.coords[index];
        }
        double distance(Node node) {
            double dist = 0;
            for (int i = 0; i < this.coords.length; ++i) {
                double d = this.coords[i] - node.coords[i];
                dist += d * d;
            }
            return dist;
        }
        public String toString() {
            StringBuilder s = new StringBuilder("(");
            for (int i = 0; i < this.coords.length; ++i) {
                if (i > 0)
                    s.append(", ");
                s.append(this.coords[i]);
            }
            s.append(')');
            return s.toString();
        }
    }
}*/

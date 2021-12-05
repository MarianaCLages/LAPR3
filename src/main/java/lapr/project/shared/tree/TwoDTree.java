package lapr.project.shared.tree;

import lapr.project.model.Facility;
import lapr.project.model.Port;
import lapr.project.model.Position;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class TwoDTree {
    private final Comparator<Facility> cmpX = Comparator.comparingDouble(o -> o.getLocation().getLongitude());

    private final Comparator<Facility> cmpY = Comparator.comparingDouble(o -> o.getLocation().getLatitude());

    double closesDist;
    Node closestNode;
    Node root = null;

    /**
     * Constructor
     */

    public TwoDTree() {
        root = null;
    }

    /**
     *
     * @param n0 node inputted by the user
     * @param port port inputted by the user
     * @return the distance between the node and the port
     */
    private static double dist(Node n0, Port port) {
        double total;

        total = Math.sqrt(distSquared(n0, port));

        return total;
    }


    /**
     *
     * @param n0
     * @param port
     * @return
     */
    private static double distSquared(Node n0, Port port) {

        double total = Math.abs(Math.pow(n0.getX() - port.getLocation().getLatitude(), 2) + Math.pow(n0.getY() - port.getLocation().getLongitude(), 2));

        return total;
    }

    /**
     *
     * @param port an array of Ports
     *
     * Inserts the group of Ports into the 2D-Tree
     */
    public void insert(Port[] port) {

        if (port == null) return;
        if (port.length == 0) return;


        root = insert(port, true);
    }

    /**
     *
     * @param point an array of Ports
     * @param divX boolean to check if it's going to be calculated by X or Y
     * @return Node
     */
    public Node insert(Port[] point, boolean divX) {
        if (point.length == 0) {
            return null;
        }
        int n = point.length / 2;


        if (divX) {
            Arrays.sort(point, cmpX);
        } else {
            Arrays.sort(point, cmpY);
        }
        Node nodeToInsert = new Node<>(point[n], null, null);
        nodeToInsert.setLeft(insert(Arrays.copyOfRange(point, 0, n), !divX));
        nodeToInsert.setRight(insert(Arrays.copyOfRange(point, n + 1, point.length), !divX));
        return nodeToInsert;
    }


    /**
     *
     * @param target the last position of a ship
     * @return the nearest Port
     */
    public Port nearestNeighborPort(Position target) {

        closesDist = Double.MAX_VALUE;
        closestNode = null;
        return nearestNeighborNode(root, target, true).getElement();
    }


    /**
     *
     * @param root the root of the 2D-Tree
     * @param target the last position of a ship
     * @param divX the boolean that decides if the algorithm calculates with X or Y
     * @return the closest Node given a certain Position
     */
    private Node nearestNeighborNode(Node root, Position target, boolean divX) {

        if (root == null) {
            return closestNode;
        }


        double d = Point2D.distanceSq(root.getX(), root.getY(), target.getLongitude(), target.getLatitude());

        if (closesDist > d) {
            closesDist = d;
            closestNode = root;
        }

        double disCoor = divX ? target.getLongitude() - root.getX() : target.getLatitude() - root.getY();
        double disCoor2 = disCoor * disCoor;

        Node node1 = disCoor < 0 ? root.left : root.right;
        Node node2 = disCoor2 < 0 ? root.right : root.left;


        nearestNeighborNode(node1, target, !divX);

        if (disCoor2 < closesDist) {
            closestNode = nearestNeighborNode(node2, target, !divX);
        }

        return closestNode;
    }

    /**
     *
     * @return The-2D Tree as a String
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Queue<Node> q = new LinkedList<>();

        q.add(this.root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node n = q.poll();
                if (n != null) {
                    sb.append(" --").append(n.getElement().getName()).append("-- ");
                    q.add(n.left);
                    q.add(n.right);
                } else {
                    sb.append(" --null-- ");
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    protected static class Node<Port> {

        private lapr.project.model.Port port;


        private Node left;
        private Node right;

        /**
         * Constructor.
         *
         * @param port port imported by the program
         * @param leftChild the left port of the current node
         * @param rightChild the right port of the current node
         */
        public Node(lapr.project.model.Port port, Node leftChild, Node rightChild) {
            this.port = port;
            left = leftChild;
            right = rightChild;

        }

        /**
         *
         * @return gets the current port
         */

        public lapr.project.model.Port getElement() {
            return port;
        }

        // Update methods

        /**
         *
         * @param e the Port that will be introduced to the tree
         */
        public void setElement(lapr.project.model.Port e) {
            port = e;
        }

        /**
         *
         * @return gets the left Node of the current node
         */
        public Node getLeft() {
            return left;
        }

        /**
         *
         * @param leftChild sets the left node of the current node
         */
        public void setLeft(Node leftChild) {
            left = leftChild;
        }

        /**
         *
         * @return gets the right node of the current node
         */
        public Node getRight() {
            return right;
        }

        /**
         *
         * @param rightChild sets the right node of the current node
         */
        public void setRight(Node rightChild) {
            right = rightChild;
        }

        /**
         *
         * @return gets the x value of the current port
         */
        public double getX() {
            return port.getLocation().getLongitude();
        }

        /**
         *
         * @return gets the y value of the current port
         */
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

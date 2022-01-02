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

    double closestDist;
    Node closestNode;
    Node root = null;

    /**
     * Constructor
     */
    public TwoDTree() {
        root = null;
    }

    /**
     * Calculates the distance between the node and the port.
     *
     * @param n0   the node
     * @param port the port
     * @return the distance between the node and the port
     */
    private static double dist(Node n0, Port port) {
        double total;

        total = Math.sqrt(distSquared(n0, port));

        return total;
    }


    /**
     * Calculates the distance squared between the node and the port.
     *
     * @param n0   the node
     * @param port the port
     * @return the distance squared between the node and the port
     */
    private static double distSquared(Node n0, Port port) {

        double total = Math.abs(Math.pow(n0.getX() - port.getLocation().getLatitude(), 2) + Math.pow(n0.getY() - port.getLocation().getLongitude(), 2));

        return total;
    }

    /**
     * Inserts a group of ports into the 2D-Tree.
     *
     * @param port the array of ports
     */
    public void insert(Port[] port) {

        if (port == null) return;
        if (port.length == 0) return;


        root = insert(port, true);
    }

    /**
     * Inserts a group of ports into the 2D-Tree by its X or Y coordinate.
     *
     * @param point the array of Ports
     * @param divX  the boolean that checks if it is going to be calculated by X or Y
     * @return the node inserted
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
     * Gets the nearest port.
     *
     * @param target the last position of a ship
     * @return the nearest port
     */
    public Port nearestNeighborPort(Position target) {

        closestDist = Double.MAX_VALUE;
        closestNode = null;
        return nearestNeighborNode(root, target, true).getElement();
    }


    /**
     * Gets the nearest node in the 2D-Tree.
     *
     * @param root   the root of the 2D-Tree
     * @param target the last position of a ship
     * @param divX   the boolean that checks if it is going to be calculated by X or Y
     * @return the closest Node given a certain Position
     */
    private Node nearestNeighborNode(Node root, Position target, boolean divX) {

        if (root == null) {
            return closestNode;
        }


        double d = Point2D.distanceSq(root.getX(), root.getY(), target.getLongitude(), target.getLatitude());

        if (closestDist > d) {
            closestDist = d;
            closestNode = root;
        }

        double disCoor = divX ? target.getLongitude() - root.getX() : target.getLatitude() - root.getY();
        double disCoor2 = disCoor * disCoor;

        Node node1 = disCoor < 0 ? root.left : root.right;
        Node node2 = disCoor2 < 0 ? root.right : root.left;


        nearestNeighborNode(node1, target, !divX);

        if (disCoor2 < closestDist) {
            closestNode = nearestNeighborNode(node2, target, !divX);
        }

        return closestNode;
    }

    /**
     * Returns the 2D-Tree as a string.
     *
     * @return the 2D-Tree as a string
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
         * @param port       the port imported by the program
         * @param leftChild  the left port of the current node
         * @param rightChild the right port of the current node
         */
        public Node(lapr.project.model.Port port, Node leftChild, Node rightChild) {
            this.port = port;
            left = leftChild;
            right = rightChild;

        }

        /**
         * Gets the current port.
         *
         * @return the current port
         */

        public lapr.project.model.Port getElement() {
            return port;
        }

        // Update methods

        /**
         * Sets the current port.
         *
         * @param port2 the port
         */
        public void setElement(lapr.project.model.Port port2) {
            port = port2;
        }

        /**
         * Gets the left node.
         *
         * @return the left node
         */
        public Node getLeft() {
            return left;
        }

        /**
         * Sets the left node.
         *
         * @param leftChild the left node
         */
        public void setLeft(Node leftChild) {
            left = leftChild;
        }

        /**
         * Gets the right node.
         *
         * @return the right node
         */
        public Node getRight() {
            return right;
        }

        /**
         * Sets the right node.
         *
         * @param rightChild the right node
         */
        public void setRight(Node rightChild) {
            right = rightChild;
        }

        /**
         * Gets the x coordinate (longitude).
         *
         * @return the x coordinate (longitude)
         */
        public double getX() {
            return port.getLocation().getLongitude();
        }

        /**
         * Gets the x coordinate (longitude).
         *
         * @return the x coordinate (longitude)
         */
        public double getY() {
            return port.getLocation().getLatitude();
        }
    }
}
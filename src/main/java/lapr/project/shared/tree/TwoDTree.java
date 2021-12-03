package lapr.project.shared.tree;

import lapr.project.model.Port;
import lapr.project.model.Position;

import java.awt.geom.Point2D;
import java.util.*;

public class TwoDTree {


    Node root;

    /**
     * Constructor.
     */
    public TwoDTree() {
        this.root = null;
    }

    /**
     *
     * @param n0 node of the 2D tree
     * @param port port inputted by the user
     * @return the distance between that node and the port
     */
    private static double dist(Node n0, Port port) {
        double total;

        total = Math.sqrt(distsquared(n0, port));

        return total;
    }

    /**
     *
     * @param n0 node of the 2D tree
     * @param port port inputted by the user
     * @return the squared between that node and the port
     */
    private static double distsquared(Node n0, Port port) {

        double total = Math.abs(Math.pow(n0.getX() - port.getLocation().getLongitude(), 2) + Math.pow(n0.getY() - port.getLocation().getLatitude(), 2));

        return total;
    }

    /**
     *
     * @param port inputted by the user
     * Inserts a Port into the 2D Tree
     */
    public void insert(Port port) {
        root = insert(new Node(port, null, null), root, true);
    }

    public Node insert(Node point, Node root, boolean divX) {

        if (root == null) {
            return point;
        }


        if (root.getX() == point.getElement().getLocation().getLongitude() && root.getY() == point.getElement().getLocation().getLatitude()) {
            return root;
        }

        int cmpResult;
        if (divX) cmpResult = root.cmpX.compare(point, root);
        else cmpResult = root.cmpY.compare(point, root);

        if (cmpResult == -1) {
            if (root.left == null) {
                root.left = point;
            } else {
                insert(point, root.left, !divX);
            }

        } else {

            if (root.right == null)
                root.right = point;
            else
                insert(point, root.right, !divX);
        }

        return root;
    }

    /**
     *
     * @return returns the Port with the smallest coordinates
     */
    public Port smallestElement() {
        return smallestElement(root);
    }

    protected Port smallestElement(Node node) {
        if (node == null) return null;
        if (node.getLeft() == null) return node.getElement();
        return smallestElement(node.getLeft());
    }

    /**
     *
     * @param n0 the first node the user wishes to compare
     * @param n1 the second node the user wishes to compare
     * @param target the Port that will be compared by the two nodes
     * @return whichever of the two nodes is the closest to the Port
     */

    public Node closest(Node n0, Node n1, Port target) {
        if (n0 == null) return n1;

        if (n1 == null) return n0;

        double d1 = dist(n0, target);
        double d2 = dist(n1, target);

        if (d1 < d2)
            return n0;
        else
            return n1;
    }

    /**
     *
     * @param target is the latest position of the ship
     * @return the closest Port of that Position.
     */

    public Port nearestNeighborPort(Position target) {
        return nearestNeighborNode(root, target, true).getElement();
    }


    private Node nearestNeighborNode(Node root, Position target, boolean divX) {

        if (root == null) {
            return null;
        }

        Node closestNode = null;

        double closestDist = Double.POSITIVE_INFINITY; //distancia mais proxima
        double d = Point2D.distanceSq(root.getX(), root.getY(), target.getLongitude(), target.getLatitude());

        if (closestDist > d) {
            closestDist = d;
            closestNode = root;
        }

        double delta = divX ? target.getLongitude() - root.getX() : target.getLatitude() - root.getY(); //se divX = true, subtrai as posições x se não as posições y
        double delta2 = delta * delta;

        Node node1 = delta < 0 ? root.left : root.right; //se delta é negativo vai para a root esquerda else root direita
        Node node2 = delta2 < 0 ? root.right : root.left;

        nearestNeighborNode(node1, target, !divX);

        if (delta2 < closestDist) { //se, durante a recursão houver um delta2 maior que o atual closesDist, utiliza-se o node2
            nearestNeighborNode(node2, target, !divX);
        }
        return closestNode;
    }

    /**
     * Returns the textual description of the ship in the format: (x,y), x being the longitude of the port
     * and the y being the latitude of the Port.
     *
     * @return the ship's characteristics
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

        /**
         * Sets the left node.
         *
         * @param leftChild the left node
         */
        public void setLeft(Node leftChild) {
            left = leftChild;
        }

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

        public double getX() {
            return port.getLocation().getLongitude();
        }

        public double getY() {
            return port.getLocation().getLatitude();
        }
    }
}
package lapr.project.shared.tree;

import lapr.project.model.Port;
import lapr.project.model.Position;
import lapr.project.shared.MedianElement;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class TwoDTree {


    Node root = null;


    public TwoDTree() {
        root = null;
    }

    private static double dist(Node n0, Port port) {
        double total;

        total = Math.sqrt(distsquared(n0, port));

        return total;
    }

    private static double distsquared(Node n0, Port port) {

        double total = Math.abs(Math.pow(n0.getX() - port.getLocation().getLatitude(), 2) + Math.pow(n0.getY() - port.getLocation().getLongitude(), 2));

        return total;
    }

    public void insert(Port[] port) {

        if (port == null) return;
        if (port.length == 1) return;
        root = insert(port, root, true);
    }

    public Node insert(Port[] point, Node root, boolean divX) {
        MedianElement medianElement;
        if (divX) {
            medianElement = new MedianElement(point, root.cmpX);
        } else {
            medianElement = new MedianElement(point, root.cmpY);
        }

        Port elementToInsert = (Port) medianElement.median();
        Node nodeToInsert = new Node(elementToInsert, null, null);

        if (root == null) {
            return nodeToInsert;
        }


        if (root.getX() == nodeToInsert.getElement().getLocation().getLongitude() && root.getY() == nodeToInsert.getElement().getLocation().getLatitude()) {
            return root;
        }


        int cmpResult;
        if (divX) cmpResult = root.cmpX.compare(nodeToInsert, root);
        else cmpResult = root.cmpY.compare(nodeToInsert, root);


        if (cmpResult == -1) {
            if (root.left == null) {
                root.left = nodeToInsert;
            } else {
                insert(Arrays.copyOfRange(point, 0, point.length / 2), root.getLeft(), !divX);
            }

        } else {

            if (root.right == null)
                root.right = nodeToInsert;
            else
                insert(Arrays.copyOfRange(point, 0, point.length / 2), root.getRight(), !divX);
        }

        return root;
    }

    public Port smallestElement() {
        return smallestElement(root);
    }

    protected Port smallestElement(Node node) {
        if (node == null) return null;
        if (node.getLeft() == null) return node.getElement();
        return smallestElement(node.getLeft());
    }

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


  /*  public double distanceOfAPoint(double x, double y) {

        double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        return distance;
    }*/


    public Port nearestNeighborPort(Position target) {
        return nearestNeighborNode(root, target, true).getElement();
    }


    private Node nearestNeighborNode(Node root, Position target, boolean divX) {

        if (root == null) {
            return null;
        }

        Node closestNode = null;

        double closestDist = Double.POSITIVE_INFINITY;
        double d = Point2D.distanceSq(root.getX(), root.getY(), target.getLongitude(), target.getLongitude());

        if (closestDist > d) {
            closestDist = d;
            closestNode = root;
        }

        double delta = divX ? target.getLongitude() - root.getX() : target.getLatitude() - root.getY();
        double delta2 = delta * delta;

        Node node1 = delta < 0 ? root.left : root.right;
        Node node2 = delta2 < 0 ? root.right : root.left;

        nearestNeighborNode(node1, target, !divX);

        if (delta2 < closestDist) {
            nearestNeighborNode(node2, target, !divX);
        }
        return closestNode;
    }

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

        private final Comparator<Node<Port>> cmpX = Comparator.comparingDouble(Node::getX);
        private final Comparator<Node<Port>> cmpY = Comparator.comparingDouble(Node::getY);
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
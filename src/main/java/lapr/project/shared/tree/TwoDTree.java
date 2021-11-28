package lapr.project.shared.tree;

import lapr.project.model.Port;

import java.awt.geom.Point2D;
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

        int cmpResult = (divX ? root.cmpX : root.cmpY).compare(point, root);

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

    public Port nearesNeighbor(Port target) {
        Port n = nearestNeighbor(root, target, true).getElement();
        return n;
    }

    private Node nearestNeighbor(Node root, Port target, boolean divX) {


        if (root == null) return null;

        Node closestNode = null;


        double closestDist = Double.POSITIVE_INFINITY;
        double d = Point2D.distanceSq(root.getX(), root.getY(), target.getLocation().getLongitude(), target.getLocation().getLongitude());

        if (closestDist > d) {
            closestDist = d;
            closestNode = root;
        }

        double delta = divX ? target.getLocation().getLongitude() - root.getX() : target.getLocation().getLatitude() - root.getY();
        double delta2 = delta * delta;

        Node node1 = delta < 0 ? root.left : root.right;
        Node node2 = delta2 < 0 ? root.right : root.left;

        nearestNeighbor(node1, target, !divX);

        if (delta2 < closestDist) {
            nearestNeighbor(node2, target, !divX);
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


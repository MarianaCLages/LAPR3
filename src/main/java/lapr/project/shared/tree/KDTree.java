/*package lapr.project.shared.tree;


import lapr.project.model.Port;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class KDTree {






    KDNode root = null;
    final int numDims;

    public KDTree() {
        this.numDims = 2;
    }

    public KDTree(KDNode root) {
        this.root = root;
        this.numDims = root.point.size();
    }


    public KDTree(List<List<Port>> points) {
        numDims = points.get(0).size();
        root = new KDNode((List<Port>) points.get(0));

        for (int i = 1, numPoints = points.size(); i < numPoints; i++) {
            List<Port> point = points.get(i);
            KDNode n = new KDNode((List<Port>) point);
            root.add(n);
        }
    }

    //Node


    protected static class KDNode<E> {
        KDNode left = null;
        KDNode right = null;
        final int numDims;


        public final KDPoint point;

        public KDNode(List<Port> props) {
            this.point = new KDPoint((List<Port>) props);
            this.numDims = props.size();
        }

        public KDNode(KDPoint point) {
            this.point = point;
            this.numDims = point.props.size();
        }

        public void add(KDNode n) {
            this.add(n, 0);
        }

        public void add(KDNode n, int k) {
            if (n.point.get(k) < point.get(k)) {
                if (left == null) {
                    left = n;
                } else {
                    left.add(n, k+1);
                }
            } else {
                if (right == null) {
                    right = n;
                } else {
                    right.add(n, k+1);
                }
            }
        }

        @Override
        public String toString() {
            return "(point: " + this.point.toString() + ")";
        }
    }


    //Point
    protected static class KDPoint<E extends Comparable <E>>  {

        final List<E> props;

        public KDPoint(List<E> props) {
            this.props = props;
        }


        double get(double depth) {
            return   depth % props.size();
        }

        public int size() {
            return props.size();
        }

        @Override
        public String toString() {
            return props.toString();
        }
    }


    public void add(KDNode point) {
        if (root == null) {
            root = point;
        } else {
            root.add(point);
        }
    }



    public KDNode nearestNeighbor(KDPoint target) {
        return nearestNeighbor(root, target, 0);
    }


    private KDNode nearestNeighbor(KDNode root, KDPoint target, int depth) {

        if (root == null) return null;

        KDNode nextBranch = null;
        KDNode otherBranch = null;


        if (target.get(depth) < root.point.get(depth)) {
            nextBranch = root.left;
            otherBranch = root.right;
        } else {
            nextBranch = root.right;
            otherBranch = root.left;
        }


        KDNode temp = nearestNeighbor(nextBranch, target, depth + 1);
        KDNode best = closest(temp, root, target);

        long radiusSquared = distSquared(target, best.point);
        double dist = target.get(depth) - root.point.get(depth);

        if (radiusSquared >= dist * dist) {
            temp = nearestNeighbor(otherBranch, target, depth + 1);
            best = closest(temp, best, target);
        }

        return best;
    }

    public KDNode closest(KDNode n0, KDNode n1, KDPoint target) {
        if (n0 == null) return n1;

        if (n1 == null) return n0;

        long d1 = distSquared(n0.point, target);
        long d2 = distSquared(n1.point, target);

        if (d1 < d2)
            return n0;
        else
            return n1;
    }


    public static long dist(KDPoint p0, KDPoint p1) {
        return (long) Math.sqrt(distSquared(p0, p1));
    }


    private static long distSquared(KDPoint p0, KDPoint p1) {
        long total = 0;
        int numDims = p0.props.size();

        for (int i = 0; i < numDims; i++) {
            double diff = Math.abs(p0.get(i) - p1.get(i));
            total += Math.pow(diff, 2);
        }
        return total;
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Queue<KDNode> q = new LinkedList<>();
        q.add(this.root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                KDNode n = q.poll();
                if (n != null) {
                    sb.append(n.point).append(", ");
                    q.add(n.left);
                    q.add(n.right);
                } else {
                    sb.append("null, ");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}*/
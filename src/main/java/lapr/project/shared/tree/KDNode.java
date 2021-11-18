package lapr.project.shared.tree;

import java.util.List;

public class KDNode {
    KDNode left = null;
    KDNode right = null;
    final int numDims;


    public final KDPoint point;

    public KDNode(List<Integer> props) {
        this.point = new KDPoint(props);
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
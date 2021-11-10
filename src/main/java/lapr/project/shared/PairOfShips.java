package lapr.project.shared;

import lapr.project.model.Ship;

public class PairOfShips implements Comparable {
    private Ship left;
    private Ship right;

    public PairOfShips(Ship left, Ship right) {
        this.left = left;
        this.right = right;
        checkShips();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PairOfShips)) return false;
        if (!super.equals(o)) return false;

        PairOfShips that = (PairOfShips) o;

        if (!getLeft().equals(that.getLeft())) return false;
        return getRight().equals(that.getRight());
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + getLeft().hashCode();
        result = 31 * result + getRight().hashCode();
        return result;
    }

    public Ship getLeft() {
        return this.left;

    }

    public void checkShips() {
        Ship aux;
        if (this.left.getMmsi() < this.right.getMmsi()) {
            aux = left;
            left = right;
            right = aux;
        }
    }

    public void checkShips(PairOfShips pairOfShips) {
        Ship aux;
        if (pairOfShips.left.getMmsi() < pairOfShips.right.getMmsi()) {
            aux = pairOfShips.left;
            pairOfShips.left = pairOfShips.right;
            pairOfShips.right = aux;
        }
    }

    public Ship getRight() {
        return this.right;
    }

    @Override
    public int compareTo(Object o) {

        PairOfShips pairsOfShips2nd = (PairOfShips) o;
        checkShips(pairsOfShips2nd);

        if (Math.abs(this.right.getTravelledDistance() - this.left.getTravelledDistance()) < Math.abs(pairsOfShips2nd.right.getTravelledDistance() - pairsOfShips2nd.left.getTravelledDistance())) {
            return 1;
        } else if (Math.abs(this.right.getTravelledDistance() - this.left.getTravelledDistance()) > Math.abs(pairsOfShips2nd.right.getTravelledDistance() - pairsOfShips2nd.left.getTravelledDistance())) {
            return -1;
        } else return 0;
    }
}
package lapr.project.shared;

import lapr.project.model.Ship;

public class PairOfShips implements Comparable {
    private Ship left;
    private Ship right;

    public PairOfShips(Ship left, Ship right) {
        this.left = left;
        this.right = right;
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

    public Ship getRight() {
        return this.right;
    }

    @Override
    public int compareTo(Object o) {
        Ship aux = null;

        PairOfShips pairsOfShips2nd = (PairOfShips) o;

        if (this.left.getMmsi() < this.right.getMmsi()) {
            aux = left;
            left = right;
            right = aux;
        } else if (pairsOfShips2nd.left.getMmsi() < pairsOfShips2nd.right.getMmsi()) {
            aux = pairsOfShips2nd.left;
            pairsOfShips2nd.left = pairsOfShips2nd.right;
            pairsOfShips2nd.right = aux;
        }

        if (this.left.getTravelledDistance() < pairsOfShips2nd.getLeft().getTravelledDistance()) {
            return -1;
        } else if (this.left.getTravelledDistance() > pairsOfShips2nd.getLeft().getTravelledDistance()) {
            return 1;
        } else return 0;

    }
}
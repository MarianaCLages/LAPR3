package lapr.project.shared;

import lapr.project.model.Ship;

public class PairOfShips implements Comparable<PairOfShips> {
    private Ship left;
    private Ship right;

    public PairOfShips(Ship left, Ship right) {
        this.left = left;
        this.right = right;
        checkShips();
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


    public Ship getRight() {
        return this.right;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PairOfShips)) return false;

        PairOfShips that = (PairOfShips) o;

        if (!getLeft().equals(that.getLeft())) return false;
        return getRight().equals(that.getRight());
    }

    @Override
    public int compareTo(PairOfShips o) {
        PairOfShips pairsOfShips2nd = (PairOfShips) o;

        if (Math.abs(this.right.getTravelledDistance() - this.left.getTravelledDistance()) < Math.abs(pairsOfShips2nd.right.getTravelledDistance() - pairsOfShips2nd.left.getTravelledDistance())) {
            return 1;
        } else if (Math.abs(this.right.getTravelledDistance() - this.left.getTravelledDistance()) > Math.abs(pairsOfShips2nd.right.getTravelledDistance() - pairsOfShips2nd.left.getTravelledDistance())) {
            return -1;
        } else return 0;
    }
}
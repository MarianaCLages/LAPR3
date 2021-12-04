package lapr.project.shared;

import lapr.project.model.Ship;

public class PairOfShips implements Comparable<PairOfShips> {
    private Ship left;
    private Ship right;

    /**
     * Constructor.
     *
     * @param left  the ship on the left
     * @param right the ship on the right
     */
    public PairOfShips(Ship left, Ship right) {
        this.left = left;
        this.right = right;

        checkShips();
    }

    /**
     * Gets the ship on the left.
     *
     * @return the ship on the left
     */
    public Ship getLeft() {
        return this.left;
    }

    /**
     * Gets the ship on the right.
     *
     * @return the ship on the right
     */
    public Ship getRight() {
        return this.right;
    }

    /**
     * Checks if the ships' MMSIs are in order.
     */
    public void checkShips() {
        Ship aux;
        if (this.left.getMmsi() < this.right.getMmsi()) {
            aux = left;
            left = right;
            right = aux;
        }
    }

    /**
     * Checks if two objects (PairOfShips) are equal.
     *
     * @param o the object
     * @return true if objects are equal, false if they aren't
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PairOfShips)) return false;

        PairOfShips that = (PairOfShips) o;

        if (!getLeft().equals(that.getLeft())) return false;
        return getRight().equals(that.getRight());
    }

    /**
     * Compares the travelled distance of two different objects (PairOfShips).
     *
     * @param o the object
     * @return 0 if they're equal, -1 or 1 if they're different
     */
    @Override
    public int compareTo(PairOfShips o) {
        PairOfShips pairsOfShips2nd = o;

        if (Math.abs(this.right.getTravelledDistance() - this.left.getTravelledDistance()) < Math.abs(pairsOfShips2nd.right.getTravelledDistance() - pairsOfShips2nd.left.getTravelledDistance())) {
            return 1;
        } else if (Math.abs(this.right.getTravelledDistance() - this.left.getTravelledDistance()) > Math.abs(pairsOfShips2nd.right.getTravelledDistance() - pairsOfShips2nd.left.getTravelledDistance())) {
            return -1;
        } else {
            return 0;
        }
    }
}
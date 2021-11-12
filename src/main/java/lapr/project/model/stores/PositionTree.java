package lapr.project.model.stores;

import lapr.project.model.Position;
import lapr.project.shared.tree.AVL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PositionTree {
    AVL<Position> positionBinarySearchTree;

    /**
     * Constructor.
     */
    public PositionTree() {
        this.positionBinarySearchTree = new AVL<>();
    }

    /**
     * Gets the smallest element of the AVL.
     *
     * @return the smallest element of the AVL
     */
    public Position getSmallestPosition() {
        return positionBinarySearchTree.smallestElement();
    }

    /**
     * Gets the biggest element of the AVL.
     *
     * @return the biggest element of the AVL
     */
    public Position getBiggestPosition() {
        return positionBinarySearchTree.biggestElement();
    }

    /**
     * Gets the size of the AVL.
     *
     * @return the size of the AVL
     */
    public int getSize() {
        return positionBinarySearchTree.size();
    }

    /**
     * Gets the AVL in order.
     *
     * @return the AVL in order
     */
    public Iterable<Position> getOrderList() {
        return positionBinarySearchTree.inOrder();
    }

    /**
     * Gets a position from the AVL.
     *
     * @param date the position's date
     * @return the position from the AVL
     */
    public Position getPosition(LocalDateTime date) {
        return positionBinarySearchTree.find(new Position(0, 0, 0, 0, 0, date));
    }

    /**
     * Converts the AVL in a list.
     *
     * @return the AVL as a list
     */
    public List<Position> getInOrderList() {
        Iterable<Position> dateIterable = getOrderList();
        List<Position> positionList = new ArrayList<>();
        dateIterable.iterator().forEachRemaining(positionList::add);

        return positionList;
    }

    /**
     * Adds a position in the AVL.
     *
     * @param position the position to be added
     * @return true if it adds, false if it doesn't
     */
    public boolean addPosition(Position position) {
        try {
            positionBinarySearchTree.insert(position);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

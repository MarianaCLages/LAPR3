package lapr.project.model.stores;

import lapr.project.shared.BinarySearchTree;
import lapr.project.model.Position;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PositionTree {
    BinarySearchTree<Position> positionBinarySearchTree;

    public PositionTree() {
        this.positionBinarySearchTree = new BinarySearchTree<Position>();
    }

    public boolean addPosition(Position position) {
        try {
            positionBinarySearchTree.insert(position);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Position getSmallestPosition() {
        return positionBinarySearchTree.smallestElement();
    }

    public Position getBiggestPosition() {
        return positionBinarySearchTree.biggestElement();
    }

    public int getSize() {
        return positionBinarySearchTree.size();
    }

    public Iterable<Position> getOrderList() {
        return positionBinarySearchTree.inOrder();
    }

    public Position getPosition(LocalDateTime date) {
       return positionBinarySearchTree.find(new Position(0, 0, 0, 0, 0, date));
    }

    public List<Position> getInOrderList() {

        Iterable<Position> dateIterable = getOrderList();
        List<Position> positionList = new ArrayList<>();
        dateIterable.iterator().forEachRemaining(positionList::add);

        return positionList;

    }
}

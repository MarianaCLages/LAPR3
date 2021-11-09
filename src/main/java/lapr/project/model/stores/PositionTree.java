package lapr.project.model.stores;

import lapr.project.model.BinarySearchTree;
import lapr.project.model.Position;

import java.time.LocalDateTime;

public class PositionTree {
    BinarySearchTree<Position> positionBinarySearchTree;

    public PositionTree() {
        this.positionBinarySearchTree = new BinarySearchTree<Position>();
    }

    public boolean addPosition(Position position) {
        try {
            positionBinarySearchTree.insert(position);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Position getSmallestPosition() {
        return positionBinarySearchTree.smallestElement();
    }

    public Position getBiggestPosition() {
        return positionBinarySearchTree.biggestElement();
    }

    public int getSize(){
        return positionBinarySearchTree.size();
    }
    public Iterable<Position> getOrderList(){
        return positionBinarySearchTree.inOrder();
    }

    public void getPosition(LocalDateTime date) {
        positionBinarySearchTree.find(new Position(date, 0, 0, 0, 0, 0));
    }
}

package lapr.project.shared.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AVLTest {

    @Test
    void testBalanceFactorNull() {
        AVL<Integer> avl = new AVL<>();
        assertEquals(0, avl.balanceFactor(avl.root()));
    }

    @Test
    void rightRotationNull() {
        AVL<Integer> avl = new AVL<>();
        assertEquals(null, avl.rightRotation(null));
    }

    @Test
    void leftRotationNull() {
        AVL<Integer> avl = new AVL<>();
        assertEquals(null, avl.leftRotation(null));
    }

    @Test
    void balanceNodeNull() {
        AVL<Integer> avl = new AVL<>();
        assertNull(avl.balanceNode(null));

    }

    @Test
    void removeNodeRoot() {
        AVL<Integer> avl = new AVL<>();
        avl.insert(1);


        avl.remove(1);

        assertEquals(avl.toString(), "");
    }

    @Test
    void removeNullNode() {
        AVL<Integer> avl = new AVL<>();


        avl.remove(1);

        assertEquals(avl.root(), null);
    }

    @Test
    void removeLeftNode() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(1);
        avl.insert(2);
        avl.insert(3);
        avl.insert(4);
        avl.insert(5);
        avl.insert(6);
        avl.insert(7);
        avl.insert(8);
        avl.insert(9);
        avl.insert(10);

        avl.remove(2);
    }

    @Test
    void removeRightNode() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(1);
        avl.insert(2);
        avl.insert(3);
        avl.insert(4);
        avl.insert(5);
        avl.insert(6);
        avl.insert(7);
        avl.insert(8);
        avl.insert(9);
        avl.insert(10);

        avl.remove(8);
    }

    @Test
    void removeRightLeftNode() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(1);
        avl.insert(2);
        avl.insert(3);
        avl.insert(4);
        avl.insert(5);
        avl.insert(6);
        avl.insert(7);
        avl.insert(8);
        avl.insert(9);
        avl.insert(10);

        avl.remove(6);
    }
    @Test
    void removeRightNodeNull() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(1);
        avl.insert(2);
        avl.insert(3);
        avl.insert(4);
        avl.insert(5);
        avl.insert(6);
        avl.insert(7);
        avl.insert(8);
        avl.insert(9);
        avl.insert(10);

        avl.remove(3);
        avl.remove(2);
    }
}
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

        assertEquals(avl.toString(),"");
    }

    @Test
    void removeNullNode() {
        AVL<Integer> avl = new AVL<>();


        avl.remove(1);

        assertEquals(avl.root(),null);
    }

}
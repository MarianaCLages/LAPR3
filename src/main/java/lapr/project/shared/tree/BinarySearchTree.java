package lapr.project.shared.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinarySearchTree<E extends Comparable<E>> implements BSTInterface<E> {


    protected Node<E> root = null;     // root of the tree

    //----------- end of nested Node class -----------

    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * @return root Node of the tree (or null if tree is empty)
     */
    public Node<E> root() {
        return root;
    }

    /**
     * Verifies if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Finds an element in the tree.
     *
     * @param element the element to be found
     * @return the element
     */
    public E find(E element) {
        return find(root, element).getElement();
    }

    public Node<E> find(Node<E> node, E element) {
        if (node == null) {
            return null;
        }

        if (node.getElement().compareTo(element) > 0) {
            return find(node.getLeft(), element);
        }
        if (node.getElement().compareTo(element) == 0) {
            return node;
        }
        return find(node.getRight(), element);
    }

    /**
     * Inserts an element in the tree.
     */
    public void insert(E element) {
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {
            return new Node<>(element, null, null);
        }

        if (node.getElement().compareTo(element) == 0) {
            node.setElement(element);
        } else {
            if (element.compareTo(node.getElement()) < 0) {
                node.setLeft(insert(element, node.getLeft()));
            } else {
                node.setRight(insert(element, node.getRight()));
            }
        }
        return node;
    }

    /**
     * Removes an element from the tree maintaining its consistency as a Binary Search Tree.
     */
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, Node<E> node) {
        if (node == null) {
            return null;
        }
        if (element.compareTo(node.getElement()) == 0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        } else if (element.compareTo(node.getElement()) < 0)
            node.setLeft(remove(element, node.getLeft()));
        else
            node.setRight(remove(element, node.getRight()));

        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    private int size(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.getLeft()) + size(node.getRight());
        }
    }

    /**
     * Returns the height of the tree
     *
     * @return height
     */
    public int height() {
        return height(root);
    }

    /**
     * Returns the height of the subtree rooted at Node node.
     *
     * @param node A valid Node within the tree
     * @return height
     */
    protected int height(Node<E> node) {
        if (node == null) {
            return -1;
        } else {
            int hLeft = height(node.getLeft());
            int hRight = height(node.getRight());

            if (hLeft < hRight) {
                return 1 + hRight;
            } else {
                return 1 + hLeft;
            }
        }
    }

    /**
     * Gets the smallest element in the tree.
     *
     * @return the smallest element in the tree
     */
    public E smallestElement() {
        return smallestElement(root);
    }

    protected E smallestElement(Node<E> node) {
        if (node == null) return null;
        if (node.getLeft() == null) return node.getElement();
        return smallestElement(node.getLeft());
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in in-order.
     *
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<E> inOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null)
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an in-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in pre-order.
     *
     * @return iterable collection of the tree's elements reported in pre-order
     */
    public Iterable<E> preOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null)
            preOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an pre-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void preOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        snapshot.add(node.getElement());
        preOrderSubtree(node.getLeft(), snapshot);
        preOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in post-order.
     *
     * @return iterable collection of the tree's elements reported in post-order
     */
    public Iterable<E> posOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null)
            posOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds positions of the subtree rooted at Node node to the given
     * snapshot using an post-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void posOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        posOrderSubtree(node.getLeft(), snapshot);
        posOrderSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
    }

    /**
     * Returns a map with a list of nodes by each tree level.
     *
     * @return a map with a list of nodes by each tree level
     */
    public Map<Integer, List<E>> nodesByLevel() {
        Map<Integer, List<E>> result = new HashMap<>();
        processBstByLevel(root, result, 0);
        return result;
    }

    /**
     * Processes the BST by levels.
     *
     * @param node   the node
     * @param result the result (map)
     * @param level  the level
     */
    private void processBstByLevel(Node<E> node, Map<Integer, List<E>> result, int level) {
        if (node == null) {
            return;
        }
        result.computeIfAbsent(level, k -> new ArrayList<>());
        result.get(level).add(node.getElement());

        processBstByLevel(node.getLeft(), result, level + 1);
        processBstByLevel(node.getRight(), result, level + 1);
    }

    /**
     * Returns a string representation of the tree.
     * Draw the tree horizontally
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

//#########################################################################

    /**
     * Returns a string representation of the tree.
     *
     * @param root  the root
     * @param level the level
     * @param sb    the string builder
     */
    private void toStringRec(Node<E> root, int level, StringBuilder sb) {
        if (root == null)
            return;
        toStringRec(root.getRight(), level + 1, sb);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                sb.append("|\t");
            sb.append("|-------").append(root.getElement()).append("\n");
        } else
            sb.append(root.getElement()).append("\n");
        toStringRec(root.getLeft(), level + 1, sb);
    }

    /**
     * Gets the biggest element.
     *
     * @return the biggest element
     */
    public E biggestElement() {
        return biggestElement(root);
    }

    /**
     * Gets the biggest element.
     *
     * @param node the node
     * @return the biggest element
     */
    protected E biggestElement(Node<E> node) {
        if (node == null) return null;
        if (node.getRight() == null) return node.getElement();
        return biggestElement(node.getRight());
    }


    /**
     * Nested static class for a binary search tree node.
     */
    protected static class Node<E> {
        private E element;          // an element stored at this node
        private Node<E> left;       // a reference to the left child (if any)
        private Node<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e          the element to be stored
         * @param leftChild  reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(E e, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
        }

        // Accessor methods

        /**
         * Gets an element.
         *
         * @return the element
         */
        public E getElement() {
            return element;
        }

        /**
         * Gets the left node.
         *
         * @return the left node
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * Gets the right node.
         *
         * @return the right node
         */
        public Node<E> getRight() {
            return right;
        }

        // Update methods

        /**
         * Sets an element.
         *
         * @param e the element
         */
        public void setElement(E e) {
            element = e;
        }

        /**
         * Sets the left node.
         *
         * @param leftChild the left node
         */
        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        /**
         * Sets the right node.
         *
         * @param rightChild the right node
         */
        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    }
}




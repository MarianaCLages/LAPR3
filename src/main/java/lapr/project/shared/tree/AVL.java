package lapr.project.shared.tree;


public class AVL<E extends Comparable<E>> extends BinarySearchTree<E> {


    public int balanceFactor(Node<E> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());

        return rightHeight - leftHeight;

    }

    public Node<E> rightRotation(Node<E> node) {
        if (node == null) {
            return null;
        }

        Node<E> leftChild = node.getLeft();
        node.setLeft(leftChild.getRight());
        leftChild.setRight(node);
        node = leftChild;

        return node;
    }

    public Node<E> leftRotation(Node<E> node) {
        if (node == null) {
            return null;
        }

        Node<E> rightChild = node.getRight();
        node.setRight(rightChild.getLeft());
        rightChild.setLeft(node);
        node = rightChild;

        return node;
    }

    private Node<E> twoRotations(Node<E> node) {
        int balance = balanceFactor(node);

        if (balance < 0) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        } else if (balance > 0) {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }

        return node;
    }

    public Node<E> balanceNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        int balance = balanceFactor(node);

        if (balance < -1) {
            if (balanceFactor(node.getLeft()) <= 0) {
                node = rightRotation(node);
            } else {
                node = twoRotations(node);
            }
        } else if (balance > 1) {
            if (balanceFactor(node.getRight()) >= 0) {
                node = leftRotation(node);
            } else {
                node = twoRotations(node);
            }
        }

        return node;
    }

    @Override
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
            if (node.getElement().compareTo(element) < 0) {
                node.setRight(insert(element, node.getRight()));
            } else {
                node.setLeft(insert(element, node.getLeft()));
            }
            node = balanceNode(node);
        }
        return node;
    }

    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.getElement().compareTo(element) == 0) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            E smallest = smallestElement(node.getRight());
            node.setElement(smallest);
            node.setRight(remove(smallest, node.getRight()));
            node = balanceNode(node);
        } else if (node.getElement().compareTo(element) > 0) {
            node.setLeft(remove(element, node.getLeft()));
            node = balanceNode(node);
        } else {
            node.setRight(remove(element, node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }


}
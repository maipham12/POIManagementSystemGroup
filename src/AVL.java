public class AVL<T extends Comparable<T>> {
    protected Node<T> root;

    protected static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;
        int height;

        Node(T data) {
            this.data = data;
            this.height = 1; // height is initially set to 1 for new nodes
        }
    }

    public Node<T> add(T value) {
        root = insert(root, value);
        return root; // returning the root is useful for chaining and unit tests
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }
        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        } else {
            // Duplicate values are not allowed in this AVL tree
            return node;
        }

        return rebalance(node);
    }

    public Node<T> remove(T value) {
        root = removeNode(root, value);
        return root;
    }

    private Node<T> removeNode(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = removeNode(node.left, value);
        } else if (cmp > 0) {
            node.right = removeNode(node.right, value);
        } else {
            // Node with only one child or no child
            if ((node.left == null) || (node.right == null)) {
                Node<T> temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    node = null; // No children
                } else {
                    node = temp; // One child
                }
            } else {
                // Node with two children: Get the inorder successor
                Node<T> temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = removeNode(node.right, temp.data);
            }
        }

        if (node == null) {
            return node;
        }

        return rebalance(node);
    }

    private Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node<T> rebalance(Node<T> node) {
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1) {
            if (getBalance(node.right) < 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        if (balance < -1) {
            if (getBalance(node.left) > 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        return node;
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private void updateHeight(Node<T> n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(Node<T> n) {
        return (n == null) ? 0 : n.height;
    }

    private int getBalance(Node<T> n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    public boolean contains(T value) {
        return search(root, value) != null;
    }

    private Node<T> search(Node<T> node, T value) {
        while (node != null) {
            int cmp = value.compareTo(node.data);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }
}

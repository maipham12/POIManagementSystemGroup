public class POIAVLTree {
    private Node root;

    private class Node {
        PointOfInterest data;
        Node left, right, parent;
        int height;

        public Node(PointOfInterest data) {
            this.data = data;
            this.height = 1;
        }
    }

    public void add(PointOfInterest poi) {
        root = insert(root, poi);
    }

    private Node insert(Node node, PointOfInterest poi) {
        if (node == null) {
            return new Node(poi);
        }
        int cmp = poi.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert(node.left, poi);
            node.left.parent = node;
        } else if (cmp > 0) {
            node.right = insert(node.right, poi);
            node.right.parent = node;
        } else {
            // This node already exists, no action needed
            return node;
        }
        updateHeight(node);
        return rebalance(node);
    }

    public boolean remove(PointOfInterest poi) {
        if (root == null || !contains(poi)) {
            return false;
        }
        root = removeNode(root, poi);
        return true;
    }

    private Node removeNode(Node node, PointOfInterest poi) {
        if (node == null) return null;

        int cmp = poi.compareTo(node.data);
        if (cmp < 0) {
            node.left = removeNode(node.left, poi);
        } else if (cmp > 0) {
            node.right = removeNode(node.right, poi);
        } else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // Node with two children, get the inorder successor (smallest in the right subtree)
            node.data = findMin(node.right).data;
            node.right = removeNode(node.right, node.data);
        }

        updateHeight(node);
        return rebalance(node);
    }

    private Node findMin(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.right) - height(node.left);
    }

    private Node rebalance(Node node) {
        int balance = getBalance(node);
        if (balance > 1) {
            if (getBalance(node.right) < 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        } else if (balance < -1) {
            if (getBalance(node.left) > 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node z = y.left;
        y.left = x;
        x.right = z;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // Search for a node (existing from previous part)
    public boolean contains(PointOfInterest poi) {
        return search(root, poi) != null;
    }

    private Node search(Node node, PointOfInterest poi) {
        while (node != null) {
            int cmp = poi.compareTo(node.data);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node;
        }
        return null;
    }
}

class AVLDeleteExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int k) { key = k; height = 1; }
    }
    Node root;

    int height(Node n) { return n == null ? 0 : n.height; }
    int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }

    Node rightRotate(Node y) {
        Node x = y.left, T = x.right;
        x.right = y; y.left = T;
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right, T = y.left;
        y.left = x; x.right = T;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int bf = balance(node);
        if (bf > 1 && key < node.left.key) return rightRotate(node);
        if (bf < -1 && key > node.right.key) return leftRotate(node);
        if (bf > 1 && key > node.left.key) { node.left = leftRotate(node.left); return rightRotate(node); }
        if (bf < -1 && key < node.right.key) { node.right = rightRotate(node.right); return leftRotate(node); }
        return node;
    }

    Node minValueNode(Node node) {
        Node cur = node;
        while (cur.left != null) cur = cur.left;
        return cur;
    }

    Node delete(Node root, int key) {
        if (root == null) return root;
        if (key < root.key) root.left = delete(root.left, key);
        else if (key > root.key) root.right = delete(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = root.left != null ? root.left : root.right;
                if (temp == null) { root = null; }
                else root = temp;
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }
        if (root == null) return root;
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int bf = balance(root);
        if (bf > 1 && balance(root.left) >= 0) return rightRotate(root);
        if (bf > 1 && balance(root.left) < 0) { root.left = leftRotate(root.left); return rightRotate(root); }
        if (bf < -1 && balance(root.right) <= 0) return leftRotate(root);
        if (bf < -1 && balance(root.right) > 0) { root.right = rightRotate(root.right); return leftRotate(root); }
        return root;
    }

    public static void main(String[] args) {
        AVLDeleteExercise t = new AVLDeleteExercise();
        t.root = t.insert(t.root, 10);
        t.root = t.insert(t.root, 20);
        t.root = t.insert(t.root, 30);
        t.root = t.delete(t.root, 20);
        System.out.println(t.root.key);
    }
}

import java.util.*;

class PersistentAVLExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int k, Node l, Node r, int h) { key = k; left = l; right = r; height = h; }
    }
    List<Node> versions = new ArrayList<>();

    int height(Node n) { return n == null ? 0 : n.height; }
    int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }
    Node newNode(int k, Node l, Node r) { return new Node(k, l, r, 1 + Math.max(height(l), height(r))); }

    Node rightRotate(Node y) { Node x = y.left, T = x.right; return newNode(x.key, x.left, newNode(y.key, T, y.right)); }
    Node leftRotate(Node x) { Node y = x.right, T = y.left; return newNode(y.key, newNode(x.key, x.left, T), y.right); }

    Node insert(Node node, int key) {
        if (node == null) return newNode(key, null, null);
        if (key < node.key) node = newNode(node.key, insert(node.left, key), node.right);
        else if (key > node.key) node = newNode(node.key, node.left, insert(node.right, key));
        int bf = balance(node);
        if (bf > 1 && key < node.left.key) return rightRotate(node);
        if (bf < -1 && key > node.right.key) return leftRotate(node);
        if (bf > 1 && key > node.left.key) return rightRotate(newNode(node.key, leftRotate(node.left), node.right));
        if (bf < -1 && key < node.right.key) return leftRotate(newNode(node.key, node.left, rightRotate(node.right)));
        return node;
    }

    void addVersion(int key) {
        Node newRoot = insert(versions.isEmpty() ? null : versions.get(versions.size() - 1), key);
        versions.add(newRoot);
    }

    void inorder(Node n) {
        if (n == null) return;
        inorder(n.left);
        System.out.print(n.key + " ");
        inorder(n.right);
    }

    public static void main(String[] args) {
        PersistentAVLExercise t = new PersistentAVLExercise();
        t.addVersion(10);
        t.addVersion(20);
        t.addVersion(5);
        t.inorder(t.versions.get(0));
        System.out.println();
        t.inorder(t.versions.get(2));
    }
}

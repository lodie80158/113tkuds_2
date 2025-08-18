import java.util.*;

class AVLRangeQueryExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int k) { key = k; height = 1; }
    }
    Node root;

    Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        return node;
    }

    void rangeQuery(Node node, int min, int max, List<Integer> res) {
        if (node == null) return;
        if (node.key > min) rangeQuery(node.left, min, max, res);
        if (node.key >= min && node.key <= max) res.add(node.key);
        if (node.key < max) rangeQuery(node.right, min, max, res);
    }

    List<Integer> rangeQuery(int min, int max) {
        List<Integer> res = new ArrayList<>();
        rangeQuery(root, min, max, res);
        return res;
    }

    public static void main(String[] args) {
        AVLRangeQueryExercise t = new AVLRangeQueryExercise();
        t.root = t.insert(t.root, 10);
        t.root = t.insert(t.root, 5);
        t.root = t.insert(t.root, 20);
        System.out.println(t.rangeQuery(5, 15));
    }
}

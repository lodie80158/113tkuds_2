import java.util.*;

class AVLLeaderboardSystem {
    static class Node {
        int score, height, size;
        Node left, right;
        Node(int s) { score = s; height = 1; size = 1; }
    }
    Node root;

    int height(Node n) { return n == null ? 0 : n.height; }
    int size(Node n) { return n == null ? 0 : n.size; }
    int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }

    void update(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
        n.size = 1 + size(n.left) + size(n.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left, T = x.right;
        x.right = y; y.left = T;
        update(y); update(x);
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right, T = y.left;
        y.left = x; x.right = T;
        update(x); update(y);
        return y;
    }

    Node insert(Node node, int score) {
        if (node == null) return new Node(score);
        if (score < node.score) node.left = insert(node.left, score);
        else node.right = insert(node.right, score);
        update(node);
        int bf = balance(node);
        if (bf > 1 && score < node.left.score) return rightRotate(node);
        if (bf < -1 && score > node.right.score) return leftRotate(node);
        if (bf > 1 && score > node.left.score) { node.left = leftRotate(node.left); return rightRotate(node); }
        if (bf < -1 && score < node.right.score) { node.right = rightRotate(node.right); return leftRotate(node); }
        return node;
    }

    int rank(Node node, int score) {
        if (node == null) return 0;
        if (score < node.score) return rank(node.left, score);
        if (score > node.score) return size(node.left) + 1 + rank(node.right, score);
        return size(node.left) + 1;
    }

    void topK(Node node, int k, List<Integer> res) {
        if (node == null || res.size() >= k) return;
        topK(node.right, k, res);
        if (res.size() < k) res.add(node.score);
        topK(node.left, k, res);
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem t = new AVLLeaderboardSystem();
        t.root = t.insert(t.root, 100);
        t.root = t.insert(t.root, 200);
        t.root = t.insert(t.root, 150);
        System.out.println(t.rank(t.root, 150));
        List<Integer> res = new ArrayList<>();
        t.topK(t.root, 2, res);
        System.out.println(res);
    }
}

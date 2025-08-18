
class AVLBasicExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int k) { key = k; height = 1; }
    }
    Node root;

    int height(Node n) { return n == null ? 0 : n.height; }

    Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }

    boolean search(Node node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        if (key < node.key) return search(node.left, key);
        return search(node.right, key);
    }

    boolean isAVL(Node node) {
        if (node == null) return true;
        int bf = height(node.left) - height(node.right);
        if (bf < -1 || bf > 1) return false;
        return isAVL(node.left) && isAVL(node.right);
    }

    public static void main(String[] args) {
        AVLBasicExercise t = new AVLBasicExercise();
        t.root = t.insert(t.root, 10);
        t.root = t.insert(t.root, 20);
        t.root = t.insert(t.root, 5);
        System.out.println(t.search(t.root, 20));
        System.out.println(t.height(t.root));
        System.out.println(t.isAVL(t.root));
    }
}

class AVLRotationExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int k) { key = k; height = 1; }
    }

    int height(Node n) { return n == null ? 0 : n.height; }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T = x.right;
        x.right = y;
        y.left = T;
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T = y.left;
        y.left = x;
        x.right = T;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    public static void main(String[] args) {
        AVLRotationExercise t = new AVLRotationExercise();
        Node root = new Node(30);
        root.left = new Node(20);
        root.left.left = new Node(10);
        root = t.rightRotate(root);
        System.out.println(root.key);
    }
}

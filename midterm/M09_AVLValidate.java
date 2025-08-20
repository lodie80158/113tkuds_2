package midterm;

import java.util.*;

public class M09_AVLValidate {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        Node root = buildTree(arr);
        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (!isAVL(root)) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    // 層序建樹
    private static Node buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node cur = q.poll();
            if (i < arr.length && arr[i] != -1) {
                cur.left = new Node(arr[i]);
                q.add(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                cur.right = new Node(arr[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    // 檢查 BST
    private static boolean isBST(Node node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    // 檢查 AVL
    private static boolean isAVL(Node node) {
        return height(node) != -1;
    }

    // 回傳高度；若不平衡回傳 -1
    private static int height(Node node) {
        if (node == null) return 0;
        int lh = height(node.left);
        if (lh == -1) return -1;
        int rh = height(node.right);
        if (rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * - 建樹 O(n)
 * - 檢查 BST O(n)
 * - 檢查 AVL O(n)
 * 總體時間複雜度 O(n)，n 為節點數。
 * Space Complexity: O(h) 遞迴棧深度。
 */

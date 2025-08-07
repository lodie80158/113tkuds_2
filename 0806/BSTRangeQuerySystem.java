import java.util.*;

public class BSTRangeQuerySystem {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    static class BST {
        TreeNode root;

        void insert(int val) {
            root = insert(root, val);
        }

        TreeNode insert(TreeNode node, int val) {
            if (node == null) return new TreeNode(val);
            if (val < node.val) node.left = insert(node.left, val);
            else if (val > node.val) node.right = insert(node.right, val);
            return node;
        }

        List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQuery(root, min, max, result);
            return result;
        }

        void rangeQuery(TreeNode node, int min, int max, List<Integer> result) {
            if (node == null) return;
            if (node.val > min) rangeQuery(node.left, min, max, result);
            if (node.val >= min && node.val <= max) result.add(node.val);
            if (node.val < max) rangeQuery(node.right, min, max, result);
        }

        int rangeCount(int min, int max) {
            return rangeCount(root, min, max);
        }

        int rangeCount(TreeNode node, int min, int max) {
            if (node == null) return 0;
            if (node.val < min) return rangeCount(node.right, min, max);
            if (node.val > max) return rangeCount(node.left, min, max);
            return 1 + rangeCount(node.left, min, max) + rangeCount(node.right, min, max);
        }

        int rangeSum(int min, int max) {
            return rangeSum(root, min, max);
        }

        int rangeSum(TreeNode node, int min, int max) {
            if (node == null) return 0;
            if (node.val < min) return rangeSum(node.right, min, max);
            if (node.val > max) return rangeSum(node.left, min, max);
            return node.val + rangeSum(node.left, min, max) + rangeSum(node.right, min, max);
        }

        int closestValue(int target) {
            return closestValue(root, target, root.val);
        }

        int closestValue(TreeNode node, int target, int closest) {
            if (node == null) return closest;
            if (Math.abs(node.val - target) < Math.abs(closest - target)) closest = node.val;
            if (target < node.val) return closestValue(node.left, target, closest);
            else return closestValue(node.right, target, closest);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] values = {10, 5, 15, 3, 7, 12, 18};
        for (int v : values) bst.insert(v);

        List<Integer> range = bst.rangeQuery(6, 15);
        System.out.println("範圍查詢 [6,15]：" + range);

        System.out.println("範圍計數 [6,15]：" + bst.rangeCount(6, 15));
        System.out.println("範圍總和 [6,15]：" + bst.rangeSum(6, 15));
        System.out.println("最接近 11 的節點：" + bst.closestValue(11));
    }
}

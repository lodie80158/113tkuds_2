import java.util.*;

public class BSTValidationAndRepair {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        findInvalidNodesHelper(root, Long.MIN_VALUE, Long.MAX_VALUE, result);
        return result;
    }

    static void findInvalidNodesHelper(TreeNode node, long min, long max, List<TreeNode> result) {
        if (node == null) return;
        if (node.val <= min || node.val >= max) result.add(node);
        findInvalidNodesHelper(node.left, min, node.val, result);
        findInvalidNodesHelper(node.right, node.val, max, result);
    }

    static void recoverBST(TreeNode root) {
        TreeNode[] nodes = new TreeNode[2];
        TreeNode[] prev = new TreeNode[1];
        inOrderRecover(root, prev, nodes);
        if (nodes[0] != null && nodes[1] != null) {
            int tmp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = tmp;
        }
    }

    static void inOrderRecover(TreeNode node, TreeNode[] prev, TreeNode[] nodes) {
        if (node == null) return;
        inOrderRecover(node.left, prev, nodes);
        if (prev[0] != null && node.val < prev[0].val) {
            if (nodes[0] == null) nodes[0] = prev[0];
            nodes[1] = node;
        }
        prev[0] = node;
        inOrderRecover(node.right, prev, nodes);
    }

    static int minRemovalsToBST(TreeNode root) {
        int[] result = dfsMinRemovals(root);
        return result[1];
    }

    static int[] dfsMinRemovals(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] left = dfsMinRemovals(node.left);
        int[] right = dfsMinRemovals(node.right);
        boolean valid = (node.left == null || node.left.val < node.val) && (node.right == null || node.right.val > node.val);
        if (valid) return new int[]{1 + left[0] + right[0], left[1] + right[1]};
        int keepLeft = left[0], keepRight = right[0];
        int removeCount = 1 + Math.min(left[1] + keepRight, right[1] + keepLeft);
        return new int[]{0, removeCount};
    }

    static void inorderPrint(TreeNode node) {
        if (node == null) return;
        inorderPrint(node.left);
        System.out.print(node.val + " ");
        inorderPrint(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(7);

        boolean valid = isValidBST(root);
        System.out.println("是否為合法 BST: " + valid);

        List<TreeNode> invalidNodes = findInvalidNodes(root);
        System.out.print("不符合 BST 規則的節點: ");
        for (TreeNode node : invalidNodes) System.out.print(node.val + " ");
        System.out.println();

        recoverBST(root);
        System.out.print("修復後的中序: ");
        inorderPrint(root);
        System.out.println();

        TreeNode badTree = new TreeNode(10);
        badTree.left = new TreeNode(20);
        badTree.right = new TreeNode(5);
        int removals = minRemovalsToBST(badTree);
        System.out.println("最少移除節點數使其為 BST: " + removals);
    }
}

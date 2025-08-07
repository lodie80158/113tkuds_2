import java.util.*;

public class BinaryTreeBasicOperations {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);

        int sum = treeSum(root);
        int count = treeCount(root);
        double avg = count == 0 ? 0 : (double) sum / count;

        System.out.println("總和: " + sum);
        System.out.println("平均: " + avg);
        System.out.println("最大值: " + findMax(root));
        System.out.println("最小值: " + findMin(root));
        System.out.println("樹的最大寬度: " + treeWidth(root));
        System.out.println("是否為完全二元樹: " + isCompleteBinaryTree(root));
    }

    static int treeSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + treeSum(node.left) + treeSum(node.right);
    }

    static int treeCount(TreeNode node) {
        if (node == null) return 0;
        return 1 + treeCount(node.left) + treeCount(node.right);
    }

    static int findMax(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;
        return Math.max(node.val, Math.max(findMax(node.left), findMax(node.right)));
    }

    static int findMin(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE;
        return Math.min(node.val, Math.min(findMin(node.left), findMin(node.right)));
    }

    static int treeWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            if (levelSize > maxWidth) maxWidth = levelSize;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return maxWidth;
    }

    static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                end = true;
            } else {
                if (end) return false;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }
}

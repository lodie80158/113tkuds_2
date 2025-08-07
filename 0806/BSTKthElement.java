import java.util.*;

public class BSTKthElement {
    static class TreeNode {
        int val;
        int size;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    static class AugmentedBST {
        TreeNode root;

        void insert(int val) {
            root = insert(root, val);
        }

        TreeNode insert(TreeNode node, int val) {
            if (node == null) return new TreeNode(val);
            if (val < node.val) node.left = insert(node.left, val);
            else node.right = insert(node.right, val);
            node.size = 1 + getSize(node.left) + getSize(node.right);
            return node;
        }

        void delete(int val) {
            root = delete(root, val);
        }

        TreeNode delete(TreeNode node, int val) {
            if (node == null) return null;
            if (val < node.val) node.left = delete(node.left, val);
            else if (val > node.val) node.right = delete(node.right, val);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                TreeNode successor = findMin(node.right);
                node.val = successor.val;
                node.right = delete(node.right, successor.val);
            }
            node.size = 1 + getSize(node.left) + getSize(node.right);
            return node;
        }

        TreeNode findMin(TreeNode node) {
            while (node.left != null) node = node.left;
            return node;
        }

        int getSize(TreeNode node) {
            return node == null ? 0 : node.size;
        }

        int kthSmallest(int k) {
            return kthSmallest(root, k);
        }

        int kthSmallest(TreeNode node, int k) {
            int leftSize = getSize(node.left);
            if (k <= leftSize) return kthSmallest(node.left, k);
            else if (k == leftSize + 1) return node.val;
            else return kthSmallest(node.right, k - leftSize - 1);
        }

        int kthLargest(int k) {
            int total = getSize(root);
            return kthSmallest(root, total - k + 1);
        }

        List<Integer> rangeKth(int k, int j) {
            List<Integer> result = new ArrayList<>();
            inorderKth(root, k, j, new int[]{1}, result);
            return result;
        }

        void inorderKth(TreeNode node, int k, int j, int[] count, List<Integer> result) {
            if (node == null) return;
            inorderKth(node.left, k, j, count, result);
            if (count[0] >= k && count[0] <= j) result.add(node.val);
            count[0]++;
            inorderKth(node.right, k, j, count, result);
        }
    }

    public static void main(String[] args) {
        AugmentedBST bst = new AugmentedBST();
        int[] values = {20, 8, 22, 4, 12, 10, 14};
        for (int v : values) bst.insert(v);

        System.out.println("第3小元素: " + bst.kthSmallest(3));
        System.out.println("第2大元素: " + bst.kthLargest(2));
        System.out.println("第2小到第5小元素: " + bst.rangeKth(2, 5));

        bst.delete(10);
        System.out.println("刪除10後第3小元素: " + bst.kthSmallest(3));
    }
}

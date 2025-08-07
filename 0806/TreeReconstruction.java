import java.util.*;

public class TreeReconstruction {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    static TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(pre[ps]);
        int ri = inMap.get(pre[ps]);
        int leftSize = ri - is;
        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, ri - 1, inMap);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, ri + 1, ie, inMap);
        return root;
    }

    static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    static TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(post[pe]);
        int ri = inMap.get(post[pe]);
        int leftSize = ri - is;
        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, ri - 1, inMap);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, ri + 1, ie, inMap);
        return root;
    }

    static TreeNode buildFromLevelOrder(int[] levelOrder) {
        if (levelOrder.length == 0) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode current = queue.poll();
            if (i < levelOrder.length) {
                current.left = new TreeNode(levelOrder[i++]);
                queue.offer(current.left);
            }
            if (i < levelOrder.length) {
                current.right = new TreeNode(levelOrder[i++]);
                queue.offer(current.right);
            }
        }
        return root;
    }

    static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelOrder = {1, 2, 3, 4, 5, 6, 7};

        TreeNode preInTree = buildFromPreIn(preorder, inorder);
        TreeNode postInTree = buildFromPostIn(postorder, inorder);
        TreeNode levelTree = buildFromLevelOrder(levelOrder);

        System.out.print("前序 + 中序 -> 中序驗證: ");
        printInOrder(preInTree);
        System.out.println();

        System.out.print("後序 + 中序 -> 中序驗證: ");
        printInOrder(postInTree);
        System.out.println();

        System.out.print("層序建樹 -> 中序驗證: ");
        printInOrder(levelTree);
        System.out.println();
    }
}

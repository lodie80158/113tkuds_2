public class TreeMirrorAndSymmetry {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val);
        node.left = mirror(root.right);
        node.right = mirror(root.left);
        return node;
    }

    static boolean areMirrors(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return t1.val == t2.val && areMirrors(t1.left, t2.right) && areMirrors(t1.right, t2.left);
    }

    static boolean isSubtree(TreeNode root, TreeNode sub) {
        if (root == null) return false;
        if (isSame(root, sub)) return true;
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    static boolean isSame(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val && isSame(a.left, b.left) && isSame(a.right, b.right);
    }

    static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val && isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(root));

        TreeNode mirrored = mirror(root);
        System.out.println("是否為鏡像: " + areMirrors(root, mirrored));

        TreeNode sub = new TreeNode(2);
        sub.left = new TreeNode(3);
        sub.right = new TreeNode(4);
        System.out.println("是否為子樹: " + isSubtree(root, sub));
    }
}

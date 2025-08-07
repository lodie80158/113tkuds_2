public class BSTConversionAndBalance {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    static class DoublyListNode {
        int val;
        DoublyListNode prev, next;
        DoublyListNode(int val) {
            this.val = val;
        }
    }

    static DoublyListNode bstToDoublyList(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        DoublyListNode[] head = new DoublyListNode[1];
        inorderToDoublyList(root, prev, head);
        return head[0];
    }

    static void inorderToDoublyList(TreeNode curr, TreeNode[] prev, DoublyListNode[] head) {
        if (curr == null) return;
        inorderToDoublyList(curr.left, prev, head);
        DoublyListNode node = new DoublyListNode(curr.val);
        if (prev[0] == null) {
            head[0] = node;
        } else {
            DoublyListNode p = new DoublyListNode(prev[0].val);
            while (p.next != null) p = p.next;
            p.next = node;
            node.prev = p;
        }
        prev[0] = curr;
        inorderToDoublyList(curr.right, prev, head);
    }

    static TreeNode sortedArrayToBST(int[] nums) {
        return arrayToBST(nums, 0, nums.length - 1);
    }

    static TreeNode arrayToBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = arrayToBST(nums, left, mid - 1);
        node.right = arrayToBST(nums, mid + 1, right);
        return node;
    }

    static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    static int checkBalance(TreeNode node) {
        if (node == null) return 0;
        int left = checkBalance(node.left);
        if (left == -1) return -1;
        int right = checkBalance(node.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    static int convertToGreaterSumTree(TreeNode root) {
        return convertHelper(root, 0);
    }

    static int convertHelper(TreeNode node, int sum) {
        if (node == null) return sum;
        sum = convertHelper(node.right, sum);
        node.val += sum;
        sum = node.val;
        return convertHelper(node.left, sum);
    }

    static void inOrderPrint(TreeNode node) {
        if (node == null) return;
        inOrderPrint(node.left);
        System.out.print(node.val + " ");
        inOrderPrint(node.right);
    }

    public static void main(String[] args) {
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode bst = sortedArrayToBST(sorted);

        System.out.print("原始 BST 中序：");
        inOrderPrint(bst);
        System.out.println();

        System.out.println("是否平衡: " + isBalanced(bst));

        convertToGreaterSumTree(bst);
        System.out.print("轉換為 Greater Sum Tree 後中序：");
        inOrderPrint(bst);
        System.out.println();

        DoublyListNode list = bstToDoublyList(bst);
        System.out.print("轉換為排序雙向鏈結串列：");
        DoublyListNode cur = list;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}
